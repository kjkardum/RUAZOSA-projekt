using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Reflection;
using AssembleIt.Application.Validation;
using Annotations = System.ComponentModel.DataAnnotations;

namespace API.Extensions
{
     public static class ValidationExtensions
    {
        public static List<ValidationResult> Validate(this object obj)
        {
            var validationResults = new List<ValidationResult>();

            if (!TryValidateObjectRecursive(obj, validationResults))
            {
                return validationResults
                    .Where(_ => !string.IsNullOrWhiteSpace(_.ErrorMessage))
                    .ToList();
            }

            return new List<ValidationResult>();
        }

        public static void RejectInvalid(this object obj)
        {
            if (obj == null)
            {
                throw new InvalidModelException();
            }

            var errors = obj.Validate();
            if (errors.Any())
                throw new InvalidModelException(errors);
        }

        public static void RejectExisting(this object obj)
        {
            if (obj != null)
            {
                throw new InvalidModelException();
            }
        }

        public static void RejectNotFound(this object obj)
        {
            if (obj == null) throw new NotFoundException();
        }

        public static void RejectForbidden<T>(this T obj, Func<T, bool> reason)
        {
            if (reason(obj))
                throw new ForbiddenException();
        }

        public static void RejectForbidden<T>(this T obj, Func<T, bool> reason, string message)
        {
            if (reason(obj))
                throw new ForbiddenException(message);
        }

        public static void RejectUnauthorized<T>(this T obj, Func<T, bool> reason)
        {
            if (reason(obj))
                throw new UnauthorizedException();
        }

        public static object GetColumnName<T>(this T obj, string columnName)
        {
            var properties = obj.GetType().GetProperties(BindingFlags.Instance | BindingFlags.Public).Where(prop => prop.CanRead).ToList();
            var property = properties.Single(p => p.Name == columnName);
            return obj.GetPropertyValue(property.Name);
        }

        private static bool TryValidateObjectRecursive<T>(T obj, List<ValidationResult> results)
        {
            bool result = Validator.TryValidateObject(obj, new Annotations.ValidationContext(obj, null, null), results, true);

            var properties = obj.GetType().GetProperties(BindingFlags.Instance | BindingFlags.Public).Where(prop => prop.CanRead).ToList();

            foreach (var property in properties)
            {
                if (property.PropertyType == typeof(string) || property.PropertyType.GetTypeInfo().IsValueType) continue;

                var value = obj.GetPropertyValue(property.Name);

                if (value == null) continue;

                var asEnumerable = value as IEnumerable;
                if (asEnumerable != null)
                {
                    foreach (var enumObj in asEnumerable)
                    {
                        var nestedResults = new List<ValidationResult>();
                        if (!TryValidateObjectRecursive(enumObj, nestedResults))
                        {
                            result = false;
                            foreach (var validationResult in nestedResults)
                            {
                                PropertyInfo property1 = property;
                                results.Add(new ValidationResult(validationResult.ErrorMessage, validationResult.MemberNames.Select(x => property1.Name + '.' + x)));
                            }
                        };
                    }
                }
                else
                {
                    var nestedResults = new List<ValidationResult>();
                    if (!TryValidateObjectRecursive(value, nestedResults))
                    {
                        result = false;
                        foreach (var validationResult in nestedResults)
                        {
                            PropertyInfo property1 = property;
                            results.Add(new ValidationResult(validationResult.ErrorMessage, validationResult.MemberNames.Select(x => property1.Name + '.' + x)));
                        }
                    };
                }
            }

            return result;
        }

        private static object GetPropertyValue(this object o, string propertyName)
        {
            object objValue = string.Empty;

            var propertyInfo = o.GetType().GetProperty(propertyName);
            if (propertyInfo != null)
            {
                objValue = propertyInfo.GetValue(o, null);
            }

            return objValue;
        }
    }
}
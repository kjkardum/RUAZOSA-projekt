using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace AssembleIt.Application.Validation
{
    /// <summary>
    /// Validates a special password format
    /// </summary>
    public class PasswordRegexAttribute : RegularExpressionAttribute
    {
        private const string RegularExpressionAttribute = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[^a-zA-Z0-9]).{8,}$";

        public PasswordRegexAttribute() : base(RegularExpressionAttribute)
        {
            ErrorMessage = "Password must contain at least one of each of the following: " +
                "upper case (A-Z), lower case (a-z) and special character (e.g.  !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~)";
        }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            return value is null ? ValidationResult.Success : base.IsValid(value, validationContext);
        }
    }

    /// <summary>
    /// Validates a required password insertion on Create. On Update it is not required.
    /// </summary>
    public class RequiredOnCreateAttribute : RequiredAttribute
    {
        public static bool DoValidate { get; set; }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            if (DoValidate)
            {
                return !string.IsNullOrEmpty((string)value) ? ValidationResult.Success : new ValidationResult("PASSWORD_PARAMS_INVALID", new List<string>() { "Password" });
            }
            else
            {
                return null;
            }
        }
    }

    /// <summary>
    /// Validates a special email format
    /// </summary>
    public class EmailAddressRegex : RegularExpressionAttribute
    {
        private const string RegularExpressionAttribute =
            @"^[\w!#$%&'*+\-/=?\^_`{|}~]+(\.[\w!#$%&'*+\-/=?\^_`{|}~]+)*@((([\-\w]+\.)+[a-zA-Z]{1,10})|(([0-9]{1,3}\.){3}[0-9]{1,3}))$";

        public EmailAddressRegex() : base(RegularExpressionAttribute)
        {
            ErrorMessage = "Invalid Email address";
        }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            return value is null ? ValidationResult.Success : base.IsValid(value, validationContext);
        }
    }

    /// <summary>
    /// Validates a special phone number format
    /// </summary>
    public class PhoneNumberRegex : RegularExpressionAttribute
    {
        private const string RegularExpressionAttribute = "^[0-9()+/ ]*$";

        public PhoneNumberRegex() : base(RegularExpressionAttribute)
        {
            ErrorMessage = "Invalid Phone number";
        }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            return value is null ? ValidationResult.Success : base.IsValid(value, validationContext);
        }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;

namespace AssembleIt.Application.Validation
{
    public class InvalidModelException : Exception
    {
        public InvalidModelException(IEnumerable<ValidationResult> errors = null)
            : base("Invalid data")
        {
            Errors = errors ?? Enumerable.Empty<ValidationResult>();
        }

        public IEnumerable<ValidationResult> Errors { get; }
    }
}

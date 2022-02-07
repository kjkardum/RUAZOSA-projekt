using System;
using System.Collections.Generic;
using System.Linq;

namespace AssembleIt.Application.Validation
{
    public class ValidationException : Exception
    {
        public ValidationException(string message)
            : base(message)
        {

        }


    }
}

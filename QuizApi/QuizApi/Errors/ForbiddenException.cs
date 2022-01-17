using System;

namespace AssembleIt.Application.Validation
{
    public class ForbiddenException : Exception
    {
        public object Content { get; set; }

        public ForbiddenException()
            : base("Forbidden")
        {
        }

        public ForbiddenException(string message)
            : base(message)
        {
        }
    }
}

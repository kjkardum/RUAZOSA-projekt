using System;

namespace AssembleIt.Application.Validation
{
    public class NotFoundException : Exception
    {
        public NotFoundException()
            : base("Object not found")
        {
        }

        public NotFoundException(string message)
           : base(message)
        {
        }
    }
}

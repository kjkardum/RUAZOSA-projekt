using System;

namespace AssembleIt.Application.Validation
{
    public class UnauthorizedException : Exception
    {
        public UnauthorizedException()
            : base("Unauthorized")
        {
        }
    }
}

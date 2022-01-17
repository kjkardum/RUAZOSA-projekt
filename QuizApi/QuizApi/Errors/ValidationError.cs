using System.Collections.Generic;

namespace AssembleIt.Application.Validation
{
    public class ValidationError
    {
        public string ErrorMessage { get; set; }
        public List<string> PropertyNames { get; set; }
    }
}

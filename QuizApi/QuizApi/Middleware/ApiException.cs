using System;
using System.Globalization;

namespace QuizApi.Middleware
{
    
    public class ApiException : Exception
    {
        public ApiException(int statusCode, string message = null, string details = null)
        {
            StatusCode = statusCode;
            Message = message;
            Details = details;
        }
        public ApiException(string message, params object[] args)
        {
            Message = String.Format(CultureInfo.CurrentCulture, message, args);
        }

        public int StatusCode { get; set; }
        public string Message { get; set; }
        public string Details { get; set; }
    }
}
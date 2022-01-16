using System;
using QuizApi.Interfaces;

namespace QuizApi.Services
{
    public class DateTimeService : IDateTimeService
    {
        public DateTime NowUtc => DateTime.UtcNow;
    }
}
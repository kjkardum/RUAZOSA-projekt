using System;

namespace QuizApi.Interfaces
{
    public interface IDateTimeService
    {
        DateTime NowUtc { get; }
    }
}
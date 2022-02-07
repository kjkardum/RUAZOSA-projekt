using System.Collections.Generic;
using System.Threading.Tasks;
using QuizApi.DTOs;
using QuizApi.Entities;

namespace QuizApi.Interfaces
{
    public interface IJRepository
    {
        Task<List<QuestionModel>> GetRandomQuestions(int count);
    }
}
using System;
using System.Collections.Generic;

namespace QuizApi.DTOs.Game
{
    public class GameModel
    {
        public int Id { get; set; }
        public DateTime StartTime { get; set; }
        public List<QuestionModel> Questions { get; set; }
    }
}
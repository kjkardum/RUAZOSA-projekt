using System;
using QuizApi.Entities.Common;

namespace QuizApi.Entities
{
    public class UserGame : AuditableBaseEntity
    {
        public int UserId { get; set; }
        public AppUser User { get; set; }
        public int GameId { get; set; }
        public Game Game { get; set; }
        
        public int Points { get; set; }
        public DateTime LastQuestionTime { get; set; }
        public int QuestionsAnswered { get; set; }
    }
}
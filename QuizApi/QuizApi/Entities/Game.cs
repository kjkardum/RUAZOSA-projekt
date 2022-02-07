using System;
using System.Collections.Generic;
using QuizApi.Entities.Common;

namespace QuizApi.Entities
{
    public class Game : AuditableBaseEntity
    {
        public DateTime StartTime { get; set; }
        public List<Question> Questions { get; set; }
        public List<UserGame> Users { get; set; }
    }
}
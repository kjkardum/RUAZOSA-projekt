using QuizApi.Entities.Common;

namespace QuizApi.Entities
{
    public class Question : AuditableBaseEntity
    {
        public string Category { get; set; }
        public int Difficulty { get; set; }
        public string QuestionText { get; set; }
        public string Answer { get; set; }
        public int JServiceId { get; set; }
        public int GameId { get; set; }
        public Game Game { get; set; }
    }
}
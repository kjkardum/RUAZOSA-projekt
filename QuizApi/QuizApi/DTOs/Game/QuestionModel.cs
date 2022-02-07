namespace QuizApi.DTOs
{
    public class QuestionModel
    {
        public int Id { get; set; }
        public string Category { get; set; }
        public int Difficulty { get; set; }
        public string QuestionText { get; set; }
        public string Answer { get; set; }
        public int JServiceId { get; set; }
        public int GameId { get; set; }
    }
}
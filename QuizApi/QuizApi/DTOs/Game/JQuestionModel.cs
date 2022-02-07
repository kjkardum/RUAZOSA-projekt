using System;

namespace QuizApi.DTOs.Game
{
    public class JQuestionModel
    {
        public DateTime AirDate { get; set; }
        public string Answer { get; set; }
        public JCategoryModel Category { get; set; }
        public int CategoryId { get; set; }
        public DateTime CreatedAt { get; set; }
        public int? GameId { get; set; }
        public int Id { get; set; }
        public int? InvalidCount { get; set; }
        public string Question { get; set; }
        public DateTime UpdatedAt { get; set; }
        public int Value { get; set; }
    }
}
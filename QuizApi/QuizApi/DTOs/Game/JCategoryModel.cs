using System;

namespace QuizApi.DTOs.Game
{
    public class JCategoryModel
    {
        public int CluesCount { get; set; }
        public int Id { get; set; }
        public DateTime CreatedAt { get; set; }
        public DateTime UpdatedAt { get; set; }
        public string Title { get; set; }
    }
}
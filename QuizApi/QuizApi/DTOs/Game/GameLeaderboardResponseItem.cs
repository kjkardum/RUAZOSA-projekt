namespace QuizApi.DTOs.Game
{
    public class GameLeaderboardResponseItem
    {
        public int UserId { get; set; }
        public string UserName { get; set; }
        public int NumberOfPoints { get; set; }
    }
}
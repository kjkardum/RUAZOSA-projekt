using System.Collections.Generic;

namespace QuizApi.DTOs.Game
{
    public class GameLeaderboardResponse
    {
        public int Id { get; set; }
        public List<GameLeaderboardResponseItem> Leaderboard { get; set; }
    }
}
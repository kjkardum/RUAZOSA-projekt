using AssembleIt.Application.DTOs.Account;

namespace QuizApi.DTOs.Followers
{
    public class FollowersModel
    {
        public int Id { get; set; }
        public int FollowerId { get; set; }
        public UserModel Follower { get; set; }
        public int FollowedId { get; set; }
        public UserModel Followed { get; set; }
    }
}
using QuizApi.Entities.Common;

namespace QuizApi.Entities
{
    public class Followers : AuditableBaseEntity
    {
        public int FollowerId { get; set; }
        public AppUser Follower { get; set; }
        public int FollowedId { get; set; }
        public AppUser Followed { get; set; }
    }
}
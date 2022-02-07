using System.Collections.Generic;
using Microsoft.AspNetCore.Identity;

namespace QuizApi.Entities
{
    public class AppUser: IdentityUser<int>
    {
        public int TotalGamesPoints { get; set; }
        public List<Followers> Following { get; set; }
        public List<Followers> FollowedBy { get; set; }
        public List<UserGame> Games { get; set; }
    }
}
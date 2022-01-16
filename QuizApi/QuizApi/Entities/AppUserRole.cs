using Microsoft.AspNetCore.Identity;

namespace QuizApi.Entities
{
    public class AppUserRole :IdentityUserRole<int>
    {
        public AppUser User { get; set; }
        public AppRole Role { get; set; }
    }
}
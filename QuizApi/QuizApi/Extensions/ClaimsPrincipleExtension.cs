using System.Security.Claims;

namespace QuizApi.Extensions
{
    public static class ClaimsPrincipleExtensions
    {
        public static int GetUserId(this ClaimsPrincipal user)
        {
            return int.Parse(user.FindFirstValue("uid"));
        }
        public static string GetUsername(this ClaimsPrincipal user)
        {
            return user.FindFirst(ClaimTypes.Name)?.Value;
        }
    }
}
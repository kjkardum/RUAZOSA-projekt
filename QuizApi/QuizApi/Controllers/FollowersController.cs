using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using QuizApi.Extensions;
using QuizApi.Services;

namespace QuizApi.Controllers
{
    public class FollowersController : BaseApiController
    {
        private readonly FollowersService _service;

        public FollowersController(FollowersService service)
        {
            _service = service;
        }

        [HttpPost, Route("AddFollower/{followerId}")]
        [Authorize]
        public async Task<IActionResult> SearchAction(int followerId)
        {
            return Ok(await _service.FollowUserByIdAsync(User.GetUserId(), followerId));
        }
    }
}
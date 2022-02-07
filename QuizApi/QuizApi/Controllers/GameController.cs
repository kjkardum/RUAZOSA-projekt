using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using QuizApi.DTOs.Game;
using QuizApi.Extensions;
using QuizApi.Services;

namespace QuizApi.Controllers
{
    public class GameController : BaseApiController
    {
        private readonly GameService _service;

        public GameController(GameService service)
        {
            _service = service;
        }
        
        [HttpGet, Route("ActiveGames")]
        [Authorize]
        public async Task<IActionResult> GetCurrentUserActiveGames()
        {
            return Ok(await _service.GetCurrentUserActiveGames(User.GetUserId()));
        }
        
        [HttpPost, Route("Start")]
        [Authorize]
        public async Task<IActionResult> CreateNewGame(NewGameModel model)
        {
            return Ok(await _service.CreateAsync(model, User.GetUserId()));
        }
        
        [HttpGet, Route("NextQuestion/{gameId}")]
        [Authorize]
        public async Task<IActionResult> GetNextQuestion(int gameId)
        {
            return Ok(await _service.GetNextGameQuestion(User.GetUserId(), gameId));
        }
        
        [HttpPost, Route("AnswerQuestion/{gameId}")]
        [Authorize]
        public async Task<IActionResult> AnswerLastQuestion(int gameId, [FromBody] string answer)
        {
            return Ok(await _service.AnswerQuestion(User.GetUserId(), gameId, answer));
        }
        
        [HttpGet, Route("Leaderboard/{gameId}")]
        [Authorize]
        public async Task<IActionResult> GetGameLeaderboard(int gameId)
        {
            return Ok(await _service.GetGameLeaderboard(gameId));
        }
    }
}
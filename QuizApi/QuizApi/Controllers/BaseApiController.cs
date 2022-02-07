using Microsoft.AspNetCore.Mvc;

namespace QuizApi.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public abstract class BaseApiController : ControllerBase
    {
    }
}
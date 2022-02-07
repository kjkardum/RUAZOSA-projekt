using System.Threading.Tasks;
using QuizApi.DTOs.Email;

namespace QuizApi.Interfaces
{
    public interface IEmailService
    {
        Task SendAsync(EmailRequest request);
    }
}
using System.ComponentModel.DataAnnotations;

namespace AssembleIt.Application.DTOs.Account
{
    public class ChangePasswordRequest
    {
        [Required]
        [EmailAddress]
        public string Email { get; set; }
        
        [Required]
        public string CurrentPassword { get; set; }
        
        [Required]
        [MinLength(6)]
        public string Password { get; set; }

        [Required]
        [Compare("Password")]
        public string ConfirmPassword { get; set; }
    }
}
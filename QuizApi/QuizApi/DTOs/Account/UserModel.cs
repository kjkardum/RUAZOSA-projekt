using System.Collections.Generic;
using QuizApi.DTOs.Followers;

namespace AssembleIt.Application.DTOs.Account
{
    public class UserModel
    {
        public int Id { get; set; }
        public List<FollowersModel> Following { get; set; }
        public List<FollowersModel> FollowedBy { get; set; }
        //public List<UserGame> Games { get; set; }
    }
}
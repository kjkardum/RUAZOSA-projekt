using AssembleIt.Application.DTOs.Account;
using AutoMapper;
using QuizApi.DTOs.Followers;
using QuizApi.Entities;

namespace QuizApi.Helpers
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<UserModel, AppUser>().ReverseMap();
            CreateMap<FollowersModel, Followers>().ReverseMap();
        }
    }
}
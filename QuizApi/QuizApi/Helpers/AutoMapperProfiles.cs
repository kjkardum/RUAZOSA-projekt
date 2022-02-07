using AssembleIt.Application.DTOs.Account;
using AutoMapper;
using QuizApi.DTOs;
using QuizApi.DTOs.Followers;
using QuizApi.DTOs.Game;
using QuizApi.Entities;

namespace QuizApi.Helpers
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<UserModel, AppUser>().ReverseMap();
            CreateMap<FollowersModel, Followers>().ReverseMap();
            CreateMap<QuestionModel, Question>().ReverseMap();
            CreateMap<GameModel, Game>().ReverseMap();
            CreateMap<QuestionModel, JQuestionModel>().ReverseMap()
                .ForMember(qm=>qm.Id, opt=>opt.MapFrom(jqm=>0))
                .ForMember(qm=>qm.GameId, opt=>opt.MapFrom(jqm=>0))
                .ForMember(qm=>qm.JServiceId, opt=>opt.MapFrom(jqm=>jqm.Id))
                .ForMember(qm=>qm.Category, opt=>opt.MapFrom(jqm=>jqm.Category.Title))
                .ForMember(qm=>qm.QuestionText, opt=>opt.MapFrom(jqm=>jqm.Question))
                .ForMember(qm=>qm.Answer, opt=>opt.MapFrom(jqm=>jqm.Answer))
                .ForMember(qm=>qm.Difficulty, opt=>opt.MapFrom(jqm=>jqm.Value));
        }
    }
}
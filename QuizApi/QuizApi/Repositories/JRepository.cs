using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using QuizApi.DTOs;
using QuizApi.DTOs.Game;
using QuizApi.Entities;
using QuizApi.Interfaces;
using RestSharp;

namespace QuizApi.Repositories
{
    public class JRepository : IJRepository
    {
        private readonly IMapper _mapper;

        public JRepository(IMapper mapper)
        {
            _mapper = mapper;
        }

        public async Task<List<QuestionModel>> GetRandomQuestions(int count)
        {
            var options = new RestClientOptions("https://jservice.io/api/random") {
                ThrowOnAnyError = true,
                Timeout = 5000
            };
            var client = new RestClient(options);
            var request = new RestRequest()
                .AddQueryParameter("count", count.ToString());
            var jQuestionModels = await client.GetAsync<List<JQuestionModel>>(request);
            return jQuestionModels?.Select(jqm => _mapper.Map<QuestionModel>(jqm)).ToList();
        }
    }
}
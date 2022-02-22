using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Extensions;
using AutoMapper;
using Microsoft.EntityFrameworkCore;
using QuizApi.DTOs;
using QuizApi.DTOs.Game;
using QuizApi.Entities;
using QuizApi.Interfaces;
using QuizApi.Services.Base;

namespace QuizApi.Services
{
    public class GameService : EntityService<Game, GameModel>
    {
        private readonly IGenericRepositoryAsync<Game> _gameRepository;
        private readonly IGenericRepositoryAsync<UserGame> _userGameRepository;
        private readonly IGenericRepositoryAsync<Question> _questionRepository;
        private readonly IJRepository _jRepository;

        public GameService(
            IGenericRepositoryAsync<Game> gameRepository,
            IJRepository jRepository,
            IMapper mapper, IGenericRepositoryAsync<Question> questionRepository, IGenericRepositoryAsync<UserGame> userGameRepository) : base(gameRepository, mapper)
        {
            _gameRepository = gameRepository;
            _jRepository = jRepository;
            _questionRepository = questionRepository;
            _userGameRepository = userGameRepository;
        }
        
        public async Task<List<GameModel>> GetCurrentUserActiveGames(int currentUserId)
        {
            var games = await _userGameRepository.AsReadOnly().Where(g => g.UserId == currentUserId && g.QuestionsAnswered < 5).Select(g=>g.Game).ToListAsync();
            return _mapper.Map<List<GameModel>>(games);
        }

        public async Task<GameLeaderboardResponse> GetGameLeaderboard(int gameId)
        {
            var game = await _gameRepository.AsReadOnly()
                .Where(g => g.Id == gameId)
                .Include(g => g.Users)
                .ThenInclude(ug => ug.User)
                .FirstOrDefaultAsync();
            game.Users.Sort((u1,u2)=>u2.Points-u1.Points);
            return new GameLeaderboardResponse
            {
                Id = game.Id,
                Leaderboard = game.Users.Select(ug => new GameLeaderboardResponseItem
                {
                    UserId = ug.UserId,
                    UserName = ug.User.UserName,
                    NumberOfPoints = ug.Points
                }).ToList()
            };
        }

        public async Task<List<GameLeaderboardResponseItem>> GetTotalLeaderboard()
        {
            var users = (await _userGameRepository.AsReadOnly()
                .Include(ug => ug.User)
                .ToListAsync())
                .GroupBy(ug => ug.UserId)
                .Select(g => new GameLeaderboardResponseItem
                {
                    UserId = g.Key,
                    UserName = g.First().User.UserName,
                    NumberOfPoints = g.Sum(ug => ug.Points)
                })
                .OrderByDescending(g => g.NumberOfPoints)
                .ToList();
            return users;
        }
        
        public async Task<QuestionModel> GetNextGameQuestion(int currentUserId, int gameId)
        {
            var userGame = await _userGameRepository.AsReadOnly()
                .Where(ug => ug.UserId == currentUserId && ug.GameId == gameId)
                .Include(ug => ug.Game)
                .ThenInclude(g => g.Questions)
                .FirstOrDefaultAsync();
            userGame.RejectNotFound();
            var question = userGame.Game.Questions.ElementAt(userGame.QuestionsAnswered);
            var questionModel = _mapper.Map<QuestionModel>(question);
            questionModel.Answer = null;
            return questionModel;
        }

        public async Task<int> AnswerQuestion(int currentUserId, int gameId, string answer)
        {
            var userGame = await _userGameRepository.AsReadOnly()
                .Where(ug => ug.UserId == currentUserId && ug.GameId == gameId)
                .Include(ug => ug.Game)
                .ThenInclude(g => g.Questions)
                .FirstOrDefaultAsync();
            userGame.RejectNotFound();
            if (userGame.QuestionsAnswered == userGame.Game.Questions.Count)
            {
                throw new Exception("Game is over");
            }
            var points = 0;
            var question = userGame.Game.Questions.ElementAt(userGame.QuestionsAnswered);
            const int allowedDelay = 1;
            var isCorrect = question.Answer.ToLower().Replace(" ", "").Equals(answer.ToLower().Replace(" ", ""));
            if (isCorrect && DateTime.Now.Subtract(userGame.LastQuestionTime).TotalSeconds < 20 + allowedDelay)
            {
                userGame.Points += question.Difficulty;
                points = question.Difficulty;

            }
            userGame.QuestionsAnswered++;
            userGame.LastQuestionTime = DateTime.Now.Add(TimeSpan.FromSeconds(allowedDelay));
            await _userGameRepository.UpdateAsync(userGame);
            return points;
        }

        public async Task<GameModel> CreateAsync(NewGameModel newGame, int currentUserId)
        {
            if (!newGame.UserIds.Contains(currentUserId)) return null;
            var game = new Game
            {
                StartTime = DateTime.Now
            };
            game = await _gameRepository.AddAsync(game);
            foreach (var userId in newGame.UserIds)
            {
                var userGame = new UserGame
                {
                    GameId = game.Id,
                    UserId = userId,
                    LastQuestionTime = DateTime.Now
                };
                await _userGameRepository.AddAsync(userGame);
            }

            var questions = await _jRepository.GetRandomQuestions(5);
            foreach (var questionModel in questions)
            {
                questionModel.GameId = game.Id;
                var question = _mapper.Map<Question>(questionModel);
                await _questionRepository.AddAsync(question);
            }

            game.Questions = null;
            return _mapper.Map<GameModel>(game);
        }
        
    }
}
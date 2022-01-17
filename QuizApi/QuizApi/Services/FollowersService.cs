using System;
using System.Linq;
using System.Threading.Tasks;
using AssembleIt.Application.DTOs.Account;
using AutoMapper;
using Microsoft.EntityFrameworkCore;
using QuizApi.DTOs.Followers;
using QuizApi.Entities;
using QuizApi.Interfaces;
using QuizApi.Services.Base;

namespace QuizApi.Services
{
    public class FollowersService : EntityService<Followers, FollowersModel>
    {
        private readonly IGenericRepositoryAsync<Followers> _followersRepository;
        private readonly IMapper _mapper;

        public FollowersService(
            IGenericRepositoryAsync<Followers> followersRepository,
            IMapper mapper
            ) : base(followersRepository, mapper)
        {
            _followersRepository = followersRepository;
            _mapper = mapper;
        }

        public async Task<FollowersModel> FollowUserByIdAsync(int currentUserId, int followedUserId)
        {
            if (currentUserId == followedUserId)
            {
                throw new ArgumentException("You can't follow yourself");
            }

            var existingFollower = await _followersRepository.AsReadOnly()
                .Where(f => f.FollowerId == currentUserId && f.FollowedId == followedUserId)
                .FirstOrDefaultAsync();
            if (existingFollower != null)
            {
                throw new ArgumentException("You already follow this user");
            }
            var follower = await _followersRepository.AddAsync(new Followers
            {
                FollowerId = currentUserId,
                FollowedId = followedUserId
            });
            return _mapper.Map<FollowersModel>(follower);
        }
    }
}
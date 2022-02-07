using System;
using System.Collections.Generic;
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
        private readonly IGenericRepositoryAsync<AppUser> _userRepository;
        private readonly IMapper _mapper;

        public FollowersService(
            IGenericRepositoryAsync<Followers> followersRepository,
            IGenericRepositoryAsync<AppUser> userRepository,
            IMapper mapper
            ) : base(followersRepository, mapper)
        {
            _followersRepository = followersRepository;
            _userRepository = userRepository;
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

        public async Task<List<UserModel>> GetFollowedUsersAsync(int currentUserId)
        {
            var followedUsers = await _followersRepository.AsReadOnly()
                .Where(f => f.FollowerId == currentUserId)
                .Select(f=>f.Followed)
                .ToListAsync();
            return _mapper.Map<List<UserModel>>(followedUsers);
        }

        public async Task<List<UserModel>> SuggestUsersToFollowAsync(int currentUserId)
        {
            var userFollowers = await _followersRepository.AsReadOnly()
                .Where(f => f.FollowedId == currentUserId)
                .Select(f=>f.Follower)
                .Where(u=>!u.FollowedBy.Select(f=>f.FollowerId).Contains(currentUserId))
                .ToListAsync();
            var otherRandomUsers = await _userRepository.AsReadOnly()
                .Include(u=>u.FollowedBy)
                .Where(u => u.Id != currentUserId &&
                            !userFollowers.Select(uf => uf.Id).Contains(u.Id) &&
                            !u.FollowedBy.Select(uf=>uf.FollowerId).Contains(currentUserId))
                .OrderBy(o => Guid.NewGuid())
                .Take(10)
                .ToListAsync();
            var allRecommendations = userFollowers.Concat(otherRandomUsers).ToList();
            return _mapper.Map<List<UserModel>>(allRecommendations);
        }
    }
}
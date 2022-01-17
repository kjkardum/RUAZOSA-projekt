using System;
using System.Text;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;
using QuizApi.Data;
using QuizApi.Entities;
using QuizApi.Helpers;
using QuizApi.Interfaces;
using QuizApi.Repositories;
using QuizApi.Services;
using QuizApi.Settings;
using QuizApi.Wrappers;

namespace QuizApi.Extensions
{
    public static class ApplicationServiceExtensions
    {
        public static IServiceCollection AddApplicationServices(this IServiceCollection services, IConfiguration config)
        {
            services.Configure<MailSettings>(config.GetSection("MailSettings"));
            services.Configure<JWTSettings>(config.GetSection("JWTSettings"));
            services.AddTransient<IDateTimeService, DateTimeService>();
            services.AddTransient<IEmailService, EmailService>();
            services.AddAutoMapper(typeof(AutoMapperProfiles).Assembly);
            services.AddDbContext<DataContext>(options =>
            {
                options.UseMySQL(config.GetConnectionString("DefaultConnection"));
            });
            services.AddTransient(typeof(IGenericRepositoryAsync<>), typeof(GenericRepositoryAsync<>));
            services.AddTransient<IAccountService, AccountService>();
            services.AddScoped<FollowersService>();
            return services;
        }
    }
}
using System.Collections.Generic;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using QuizApi.Entities;

namespace QuizApi.Data
{
    public class Seed
    {
        public static async Task SeedUsers(UserManager<AppUser> userManager, RoleManager<AppRole> roleManager)
        {
            if (await userManager.Users.AnyAsync()) return;

            var roles = new List<AppRole>
            {
                new AppRole{Name = "Member"},
                new AppRole{Name = "Admin"},
            };
            foreach (var role in roles)
            {
                await roleManager.CreateAsync(role);
            }

            var users = new List<AppUser>
            {
                new AppUser
                {
                    UserName = "basicuser",
                    Email = "basicuser@quizapp.com",
                },
                new AppUser
                {
                    UserName = "otheruser",
                    Email = "otheruser@quizapp.com",
                },
            };
            foreach (var user in users)
            {
                user.UserName = user.UserName.ToLower();

                await userManager.CreateAsync(user, "Pa$$w0rd123");
                await userManager.AddToRoleAsync(user, "Member");
            }

            var admin = new AppUser
            {
                UserName = "admin",
                Email = "admin@quizapp.com"
            };
            await userManager.CreateAsync(admin, "Pa$$w0rd");
            await userManager.AddToRoleAsync(admin, "Admin");
        }
    }
}
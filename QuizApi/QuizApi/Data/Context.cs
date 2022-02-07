using System.Linq;
using System.Security.Claims;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using QuizApi.Entities;
using QuizApi.Entities.Common;
using QuizApi.Interfaces;

namespace QuizApi.Data
{
    public class DataContext : IdentityDbContext<AppUser, AppRole, int,
        IdentityUserClaim<int>, AppUserRole, IdentityUserLogin<int>,
        IdentityRoleClaim<int>, IdentityUserToken<int>>
    {
        private readonly IDateTimeService _dateTime;
        private readonly IHttpContextAccessor _httpContextAccessor;

        public DataContext(DbContextOptions<DataContext> options, IDateTimeService dateTime, IHttpContextAccessor httpContextAccessor) : base(options)
        {
            _dateTime = dateTime;
            _httpContextAccessor = httpContextAccessor;
        }
        public DbSet<Followers> Followers { get; set; }
        public DbSet<Game> Games { get; set; }
        public DbSet<UserGame> UserGames { get; set; }
        public DbSet<Question> Questions { get; set; }
        
        public override Task<int> SaveChangesAsync(CancellationToken cancellationToken = new CancellationToken())
        {
            foreach (var entry in ChangeTracker.Entries<AuditableBaseEntity>())
            {
                switch (entry.State)
                {
                    case EntityState.Added:
                        entry.Entity.Created = _dateTime.NowUtc;
                        entry.Entity.CreatedBy = _httpContextAccessor.HttpContext?.User?.FindFirst(ClaimTypes.Name)?.Value ?? "";
                        break;
                    case EntityState.Modified:
                        entry.Entity.LastModified = _dateTime.NowUtc;
                        entry.Entity.LastModifiedBy =  _httpContextAccessor.HttpContext?.User?.FindFirst(ClaimTypes.Name)?.Value ?? "";
                        break;
                }
            }
            return base.SaveChangesAsync(cancellationToken);
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            //All Decimals will have 18,6 Range
            foreach (var property in builder.Model.GetEntityTypes()
                .SelectMany(t => t.GetProperties())
                .Where(p => p.ClrType == typeof(decimal) || p.ClrType == typeof(decimal?)))
            {
                property.SetColumnType("decimal(18,6)");
            }

            builder.Entity<AppUser>()
                .HasMany(u => u.Following)
                .WithOne(f => f.Follower)
                .HasForeignKey(f => f.FollowerId);
            builder.Entity<AppUser>()
                .HasMany(u=>u.FollowedBy)
                .WithOne(f => f.Followed)
                .HasForeignKey(f => f.FollowedId);

            builder.Entity<AppUser>(entity => entity.Property(m => m.Id).HasMaxLength(85));
            builder.Entity<AppRole>(entity => entity.Property(m => m.Id).HasMaxLength(85));

            builder.Entity<IdentityUserClaim<int>>(entity => entity.Property(m => m.Id).HasMaxLength(85));
            builder.Entity<IdentityRoleClaim<int>>(entity => entity.Property(m => m.Id).HasMaxLength(85));

            builder.Entity<IdentityUserLogin<int>>(entity => entity.Property(m => m.LoginProvider).HasMaxLength(85));
            builder.Entity<IdentityUserLogin<int>>(entity => entity.Property(m => m.ProviderKey).HasMaxLength(85));

            builder.Entity<IdentityUserToken<int>>(entity => entity.Property(m => m.LoginProvider).HasMaxLength(85));
            builder.Entity<IdentityUserToken<int>>(entity => entity.Property(m => m.Name).HasMaxLength(85));
            
            base.OnModelCreating(builder);
        }
    }
}
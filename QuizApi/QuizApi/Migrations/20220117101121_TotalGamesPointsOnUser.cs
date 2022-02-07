using Microsoft.EntityFrameworkCore.Migrations;

namespace QuizApi.Migrations
{
    public partial class TotalGamesPointsOnUser : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "TotalGamesPoints",
                table: "AspNetUsers",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "TotalGamesPoints",
                table: "AspNetUsers");
        }
    }
}

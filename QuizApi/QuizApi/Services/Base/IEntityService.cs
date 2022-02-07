using System.Collections.Generic;
using System.Threading.Tasks;

namespace QuizApi.Services.Base
{
    public interface IEntityService<TModel>
        where TModel : class
    {
        Task<List<TModel>> GetAllAsync();
        Task<TModel> GetAsync(int id);
        Task<TModel> CreateAsync(TModel model);
        Task<TModel> UpdateAsync(int id, TModel model);
        Task DeleteAsync(int id);
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Extensions;
using AutoMapper;
using Microsoft.EntityFrameworkCore;
using QuizApi.Entities.Common;
using QuizApi.Interfaces;

namespace QuizApi.Services.Base
{
    public class EntityService<TEntity, TModel> : IEntityService<TModel>
        where TEntity : AuditableBaseEntity, new()
        where TModel : class
    {
        protected readonly IGenericRepositoryAsync<TEntity> _entityRepository;
        protected readonly IMapper _mapper;
        public IGenericRepositoryAsync<TEntity> EntityRepository
        {
            get
            {
                return _entityRepository;
            }
        }

        public string SourceAPI { get; set; }

        protected EntityService(IGenericRepositoryAsync<TEntity> entityRepository, IMapper mapper)
        {
            _entityRepository = entityRepository;
            _mapper = mapper;
        }

        #region IEntityService
        public async virtual Task<List<TModel>> GetAllAsync()
        {
            var list = new List<TModel>();

            await _entityRepository.AsReadOnly()
                .ForEachAsync(e => list.Add(_mapper.Map<TModel>(e)));

            return list;
        }

        public async virtual Task<TModel> GetAsync(int id)
        {
            var entity = await _entityRepository.GetByIdAsync(id);
            entity.RejectNotFound();

            var model = _mapper.Map<TModel>(entity);
            return model;
        }

        public async virtual Task<TModel> CreateAsync(TModel model)
        {
            var entity = new TEntity();
            UpdateEntity(entity, model);

            entity = await _entityRepository.AddAsync(entity);

            return _mapper.Map<TModel>(entity);
        }

        public async virtual Task<TModel> UpdateAsync(int id, TModel model)
        {
            var entity = await _entityRepository.GetByIdAsync(id);
            entity.RejectNotFound();

            UpdateEntity(entity, model);
            await _entityRepository.UpdateAsync(entity);
            _entityRepository.DetachEntity(entity);

            return _mapper.Map<TModel>(entity);
        }

        public async virtual Task DeleteAsync(int id)
        {
            var entity = await _entityRepository.GetByIdAsync(id);
            entity.RejectNotFound();

            await _entityRepository.DeleteAsync(entity);

        }

        protected virtual void UpdateEntity(TEntity entity, TModel model)
        {
            _mapper.Map(model, entity);
        }

        #endregion
    }
}
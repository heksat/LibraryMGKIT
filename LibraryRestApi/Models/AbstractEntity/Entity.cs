using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public abstract class Entity
    {
        /// <summary>
        /// Идентификатор сущности
        /// </summary>
        public Guid ID { get; set; } = Guid.NewGuid();
    }
}

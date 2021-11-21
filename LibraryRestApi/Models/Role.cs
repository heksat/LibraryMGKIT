using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class Role:Entity
    {
        /// <summary>
        /// Описание роли
        /// </summary>
        public string Description { get; set; }
        /// <summary>
        /// Код роли 
        /// </summary>
        public string Code { get; set; }
        public virtual HashSet<User> Users { get; set; }

    }
}

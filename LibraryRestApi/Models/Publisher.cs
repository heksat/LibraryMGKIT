using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class Publisher: Entity
    {
        /// <summary>
        /// Название издательства
        /// </summary>
        public string Name { get; set; }
        public virtual HashSet<Book> Books { get; set; }
    }
}

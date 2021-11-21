using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    /// <summary>
    /// Жанр
    /// </summary>
    public class Genre:Entity
    {
        /// <summary>
        /// Название жанра
        /// </summary>
        public string Name { get; set; }
        public virtual HashSet<Book> Books { get; set; }
    }
}

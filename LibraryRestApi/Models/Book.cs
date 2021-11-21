using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    /// <summary>
    /// Книга
    /// </summary>
    public class Book:Entity
    {
        /// <summary>
        /// Название книги
        /// </summary>
        public string Title { get; set; }
        /// <summary>
        /// Год издания
        /// </summary>
        public short YearEdition { get; set; }
        //Количество взятых книш
        public short UsedCount { get; set; }

        //Количество книг
        public short Count { get; set; }
        /// <summary>
        /// Возрастное ограничение
        /// </summary>
        public byte AgeLimit { get; set; }
        public virtual HashSet<Lending> Lendings  { get; set; }


    }
}

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
        
        public Guid PublisherID { get; set; }
        public virtual Publisher Publisher { get; set; }
        public Guid GenreID { get; set; }
        public virtual Genre Genre { get; set; }
        public Guid AuthorID { get; set; }
        public virtual Author Author { get; set; }



    }
}

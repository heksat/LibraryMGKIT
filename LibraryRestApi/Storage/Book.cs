using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
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
        [JsonIgnore()]
        public virtual HashSet<Lending> Lendings  { get; set; }
        public Guid PublisherID { get; set; }
        [JsonIgnore()]
        public virtual Publisher Publisher { get; set; }
        public Guid GenreID { get; set; }
        [JsonIgnore()]
        public virtual Genre Genre { get; set; }
        public Guid AuthorID { get; set; }
        [JsonIgnore()]
        public virtual Author Author { get; set; }

        public BookModel ToBookModel()
        {
            return new BookModel()
            {
                ID = ID,
                Name = Title,
                Author = $"{Author.LName} {Author.FName[0]}.",
                Count = Count - UsedCount,
                YearEdition = YearEdition
            };
        }


    }
}

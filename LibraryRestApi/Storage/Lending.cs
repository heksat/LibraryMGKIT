using LibraryRestApi.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class Lending:Entity
    {
        public DateTime DateStart { get; set; }
        public DateTime? DateFinish { get; set; }
        public LendingStatus Status { get; set; }  
        public Guid BookID { get; set; }
        [JsonIgnore()]
        public virtual Book Book { get; set; }
        public Guid UserID { get; set; }
        [JsonIgnore()]
        public virtual User User { get; set; }
        public BookLendingsModel ToBookLendingsModel()
        {
            return new BookLendingsModel()
            {
                ID = ID,
                Name = Book.Title,
                Author = $"{Book.Author.LName} {Book.Author.FName[0]}.",
                YearEdition = Book.YearEdition
            };
        }

    }
}

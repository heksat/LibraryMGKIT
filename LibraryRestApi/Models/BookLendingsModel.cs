using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class BookLendingsModel: Entity
    {      
      public string Author { get; set; }
      public string Name { get; set; }
      public int YearEdition { get; set; }

    }
}

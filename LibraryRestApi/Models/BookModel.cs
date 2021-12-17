using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class BookModel: Entity
    {
      public string Author { get; set; }
      public string Name { get; set; }
      public int Count { get; set; }
      public int YearEdition { get; set; }
      public string Image { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    /// <summary>
    /// Авторы
    /// </summary>
    public class Author : HumanEntity
    { 
        [JsonIgnore()]
        public virtual HashSet<Book> Books { get; set; }
    }
}

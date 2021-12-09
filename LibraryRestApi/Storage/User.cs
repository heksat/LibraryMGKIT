using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class User:HumanEntity
    {
        public string Password { get; set; }
        public string Email { get; set; }
        public Guid RoleID { get; set; }
        public int MaxBooks { get; set; }
        public virtual Role Role { get; set; }
        [JsonIgnore()]
        public virtual HashSet<Lending> Lendings { get; set; }

    }
}

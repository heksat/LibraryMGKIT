using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class User:HumanEntity
    {
        public Guid RoleID { get; set; }
        public virtual Role Role { get; set; }
        public virtual HashSet<Lending> Lendings { get; set; }

    }
}

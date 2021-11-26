using LibraryRestApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Interfaces
{
    public interface IModel
    {
        public Entity AsEntity();
        
    }
}

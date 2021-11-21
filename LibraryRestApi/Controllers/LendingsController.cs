using LibraryRestApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class LendingsController:Controller
    {
        public LendingsController(DB context) : base(context)
        {
        }
    }
}

using LibraryRestApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class PublishersController : Controller
    {
        public PublishersController(DB context) : base(context)
        {
        }
    }
}

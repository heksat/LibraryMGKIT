using LibraryRestApi.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    [ApiController()]
    [Route("api/[controller]")]
    public class AuthorController : ControllerBase
    {
        [HttpGet()]
        public List<Author> Get()
        {
            using (var db = new DB())
            {
               return db.Authors.ToList();
            }
        }
    }
}

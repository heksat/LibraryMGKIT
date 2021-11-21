using LibraryRestApi.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class AuthorsController : Controller    
    {
        public AuthorsController(DB context) : base(context)
        {
        }
        [HttpGet()]
        [Authorize(Roles = "0")]
        public List<Author> Get()
        {
               return db.Authors.ToList();
        }
    }
}

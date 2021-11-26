using LibraryRestApi.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
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
        [Route("authors")]
        [Authorize(Roles = "0")]
        public List<Author> Get()
        {
            return db.Authors.ToList();
        }
        [HttpPost()]
        [Route("author")]
        [Authorize(Roles = "0")]
        public async Task<IActionResult> Post(AuthorModel model)
        {
            var entity = model.AsEntity() as Author;
            if (entity != null)
            {
                var results = new List<ValidationResult>();
                var context = new ValidationContext(model);
                if (!Validator.TryValidateObject(model, context, results, true))
                {
                    return BadRequest();
                }
                else
                {
                    db.Authors.Add(entity);
                    await db.SaveChangesAsync();
                    return Ok();
                }
            }
            return BadRequest();
        }
        [HttpPut()]
        [Route("author")]
        [Authorize(Roles = "0")]
        public async Task<IActionResult> Edit()
        {
            return Ok();
        }
    }
}

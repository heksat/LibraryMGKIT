using LibraryRestApi.Enums;
using LibraryRestApi.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class LendingsController : Controller
    {
        public LendingsController(DB context) : base(context)
        {
        }
        [HttpPost()]
        [Route("lending")]
        public async Task<ActionResult> Lending(IDModel model)
        {
            var book = db.Books.Find(model.ID);
            if (book != null)
            {
                var email = HttpContext.User.Identity.Name;
                var user = db.Users.Where(x => x.Email == email).FirstOrDefault();
                if (user != null)
                {
                    db.Lendings.Add(new Models.Lending()
                    {
                        UserID = user.ID,
                        BookID = book.ID,
                        DateStart = DateTime.Now,
                        Status = LendingStatus.Reserved
                    });
                    book.UsedCount++;
                    db.SaveChanges();
                    return Ok();
                }
            }
            return BadRequest();
        }
    }
}

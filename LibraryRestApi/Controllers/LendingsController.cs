using LibraryRestApi.Enums;
using LibraryRestApi.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
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
        public async Task<ActionResult<string>> Lending(IDModel model)
        {
            var book = db.Books.Find(model.ID);
            if (book != null)
            {
                var email = HttpContext.User.Identity.Name;
                var user = db.Users.Where(x => x.Email == email).FirstOrDefault();
                if (user.Lendings.Count() < user.MaxBooks)
                {
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
                        await db.SaveChangesAsync();
                        return Ok(null);
                    }
                }
                else {
                    return Ok("\"Превышено кол-во брони\"");
                }
            }
            return BadRequest();
        }
        [HttpGet()]
        public async Task<List<BookLendingsModel>> GetLendingsBooks()
        {
            var name = User.Identity.Name;
            var user = db.Users.Where(x=>x.Email==name).FirstOrDefault();
            if (user != null)
            {
                var list = await db.Lendings.Where(x=>x.UserID==user.ID).ToListAsync();

                return list.Select(x => x.ToBookLendingsModel()).ToList();
            }
            return null;
        }

    }
}

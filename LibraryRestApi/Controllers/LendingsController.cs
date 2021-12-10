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
                var id = Guid.Parse(HttpContext.User.Identity.Name);
                var user = db.Users.Find(id);
                if (user.Lendings.Where(x=>x.Status!= LendingStatus.Returned).Count() < user.MaxBooks)
                {
                    if (book.Count - book.UsedCount > 0)
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
                    else
                    {
                    return Ok("\"Нет больше книг\"");
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
            var id = Guid.Parse(User.Identity.Name);
            var user = db.Users.Find(id);
            if (user != null)
            {
                var list = await db.Lendings.Where(x=>x.UserID==user.ID && x.Status != Enums.LendingStatus.Returned).ToListAsync();

                return list.Select(x => x.ToBookLendingsModel()).ToList();
            }
            return null;
        }
        [HttpGet()]
        [Route("Admin")]
        public async Task<List<BookAdminLendingsModel>> GetLendingsAdminBooks()
        {
            var id = Guid.Parse(User.Identity.Name);
            var user = db.Users.Find(id);
            if (user != null)
            {
                var list = await db.Lendings.Where(x=>x.Status == Enums.LendingStatus.ReturnRequest || x.Status == Enums.LendingStatus.Reserved).ToListAsync();

                return list.Select(x => x.ToBookAdminLendingsModeyl()).ToList();
            }
            return null;
        }

        [HttpPost()]
        [Route("Back")]
        public async Task<ActionResult<string>> BackBook(IDModel id)
        {
            var selectedBook = db.Lendings.Find(id.ID);
            if(selectedBook != null)
            {
                switch (selectedBook.Status) {
                    case Enums.LendingStatus.OnHands:
                        selectedBook.Status = LendingStatus.ReturnRequest; break;
                    case LendingStatus.Reserved:
                        {
                            selectedBook.Status = LendingStatus.Returned;
                            selectedBook.Book.UsedCount--;
                        } break;
                    default:
                        return Ok("\"Неопознанный статус ошибки\"");
                }
                await db.SaveChangesAsync();
                return Ok(null);
            }
            return BadRequest();
        }
        [HttpPost()]
        [Route("Return")]
        public async Task<ActionResult<string>> ReturnBook(IDModel id)
        {
            var selectedBook = db.Lendings.Find(id.ID);
            if(selectedBook != null)
            {
                switch (selectedBook.Status) {
                    case Enums.LendingStatus.ReturnRequest:
                        {
                            selectedBook.Status = LendingStatus.Returned; break;
                            selectedBook.Book.UsedCount--;
                        }
                    case LendingStatus.Reserved:
                        {
                            selectedBook.Status = LendingStatus.OnHands;
                        } break;
                    default:
                        return Ok("\"Неопознанный статус ошибки\"");
                }

                await db.SaveChangesAsync();
                return Ok(null);
            }
            return BadRequest();
        }


    }
}

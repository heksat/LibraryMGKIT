using LibraryRestApi.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class BooksController:Controller
    {
        [HttpGet()]
        public async Task<List<Book>> GetBooks()
        {
            using (var db = new DB())
            {
                var list =  await db.Books.ToListAsync();
                return list;
            }
        }
        [HttpPost()]
        public async Task<ActionResult<Book>> CreateBook(Book book)
        {
            using (var db = new DB())
            {
                if (book == null)
                {
                    return BadRequest();
                }
                db.Books.Add(book);
                await db.SaveChangesAsync();
                return Ok(book);
            }
        }

    }
}

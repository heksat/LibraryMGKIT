using LibraryRestApi.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class BooksController : Controller
    {
        public BooksController(DB context) : base(context)
        {
        }
        [HttpGet()]
        public async Task<List<Book>> GetBooks()
        {
            var list = await db.Books.ToListAsync();
            return list;
        }
        [HttpPost()]
        public async Task<ActionResult<Book>> CreateBook(Book book)
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

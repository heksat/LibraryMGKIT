using LibraryRestApi.Models;
using LibraryRestApi.ViewModel;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class AccountController : Controller
    {
        public AccountController(DB context) : base(context)
        {
        }
        [HttpPost]
        [AllowAnonymous()]
        [Route("Login")]
        public async Task<IActionResult> Login(LoginModel model)
        {
            if (ModelState.IsValid)
            {
                User user = await db.Users.FirstOrDefaultAsync(u => u.Email == model.Email && u.Password == model.Password);
                if (user != null)
                {
                    await Authenticate(user); // аутентификация

                    return Ok();
                }
            }
            return BadRequest(model);
        }
        [HttpPost]
        [AllowAnonymous()]
        [Route("Register")]
        public async Task<IActionResult> Register(RegisterModel model)
        {
            User user = await db.Users.FirstOrDefaultAsync(u => u.Email == model.Email);
            if (user == null)
            {
                var role = db.Roles.Where(x => x.Code == Enums.RoleCode.Member).FirstOrDefault();
                // добавляем пользователя в бд
                var newuser = new User {LName = model.LName,
                    FName = model.FName,
                    SName = model.SName,
                    Email = model.Email,
                    Password = model.Pass,
                    RoleID = role.ID,
                    BirthDay = DateTime.Parse(model.Birthday),
                    Gender = model.Gender,
                    MaxBooks = 3
                };
                db.Users.Add(newuser);
                await db.SaveChangesAsync();

               // await Authenticate(newuser); // аутентификация

                return Ok();
            }
            return BadRequest();
        }

        [HttpGet()]
        [Route("Logout")]
        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);
            return Ok();
        }
    }
}

using LibraryRestApi.Models;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    public class UserController : Controller
    { 
        public UserController(DB context) : base(context)
        {
        }
        [HttpGet()]
        public User GetCurrentUser()
        {
            var user = db.Users.Where(x => x.Email == User.Identity.Name).FirstOrDefault();
            if (user != null)
            {
                return user;
            }
            return null;
        }
        [HttpPost()]
        public async Task<ActionResult> PostUser(PostUserModel model)
        {
            var user = db.Users.Where(x => x.Email == User.Identity.Name).FirstOrDefault();
            if (user != null)
            {
                user.LName = model.LName;
                user.FName = model.FName;
                user.SName = model.SName;
                user.Email = model.Email;
                if (model.Password!= null && String.Empty != model.Password)
                {
                    user.Password = model.Password;
                }
                await db.SaveChangesAsync();
                await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);
                await Authenticate(user);
                return Ok();
            }
            return BadRequest();
        }


    }
}

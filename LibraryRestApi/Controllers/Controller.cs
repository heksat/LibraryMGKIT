using LibraryRestApi.Attributies;
using LibraryRestApi.Models;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace LibraryRestApi.Controllers
{
    /// <summary>
    /// Абстрактный класс для всех контроллеров
    /// </summary>
    [Authorize]
    [ApiController()]
    [Route("api/[controller]")]
    [ExceptionLoggining()]
    public abstract class Controller:ControllerBase
    {
        protected DB db;
        protected async Task Authenticate(User user)
        {
            // создаем один claim
            var claims = new List<Claim>
            {
                new Claim(ClaimsIdentity.DefaultNameClaimType, user.ID.ToString()),
                new Claim(ClaimsIdentity.DefaultRoleClaimType, user.Role?.Code ?? "")
            };
            // создаем объект ClaimsIdentity
            ClaimsIdentity id = new ClaimsIdentity(claims, "ApplicationCookie", ClaimsIdentity.DefaultNameClaimType, ClaimsIdentity.DefaultRoleClaimType);
            // установка аутентификационных куки
            var cookie = new ClaimsPrincipal(id);
            await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme,cookie);
        }

        public Controller(DB context)
        {
            db = context;
        }
    }
}

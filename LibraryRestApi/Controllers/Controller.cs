using LibraryRestApi.Attributies;
using LibraryRestApi.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
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
        public Controller(DB context)
        {
            db = context;
        }
    }
}

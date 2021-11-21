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
    [ApiController()]
    [Route("api/[controller]")]
    public abstract class Controller:ControllerBase
    {
    }
}

using Microsoft.AspNetCore.Mvc.Filters;
using NLog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace LibraryRestApi.Attributies
{
    public class ExceptionLogginingAttribute : Attribute, IExceptionFilter
    {
        private static readonly Logger log = LogManager.GetCurrentClassLogger();
        public void OnException(ExceptionContext context)
        {
            log.Trace(context.Exception);
            context.HttpContext.Response.StatusCode = (int)HttpStatusCode.BadRequest;
            context.Exception = null;
        }
    }
}

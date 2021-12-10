using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.ViewModel
{
    public class RegisterModel
    {
        [Required(ErrorMessage = "Не указан Email")]
        public string Email { get; set; }

        [Required(ErrorMessage = "Не указан пароль")]
        public string Pass { get; set; }
        public string LName { get; set; }
        public string FName { get; set; }
        public string SName { get; set; }
        public char Gender { get; set; }
        public string Birthday { get; set; }

    }
}

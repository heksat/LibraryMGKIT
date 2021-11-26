using LibraryRestApi.Interfaces;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    public class AuthorModel:IModel, IValidatableObject
    {
         /// <summary>
        /// Фамилия
        /// </summary>
        public string LName { get; set; }
        /// <summary>
        /// Имя
        /// </summary>
        public string FName { get; set; }
        /// <summary>
        /// Отчество
        /// </summary>
        public string SName { get; set; }
        /// <summary>
        /// День рождения
        /// </summary>
        public DateTime BirthDay { get; set; }
        /// <summary>
        /// Пол
        /// </summary>
        public char Gender { get; set; }

        public Entity AsEntity()
        {
            return new Author()
            {
                FName = FName,
                BirthDay = BirthDay,
                LName = LName,
                SName = SName,
                Gender = Gender
            };
        }

        public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            List<ValidationResult> errors = new List<ValidationResult>();

            if (string.IsNullOrWhiteSpace(LName))
                errors.Add(new ValidationResult("Не указана фамилия"));
            if (string.IsNullOrWhiteSpace(FName))
                errors.Add(new ValidationResult("Не указано имя"));
            if (Gender == default(char))
                errors.Add(new ValidationResult("Не указан пол"));
            if (BirthDay == default(DateTime))
                errors.Add(new ValidationResult("Не указана дата рождения"));
            return errors;
        }
    }
}

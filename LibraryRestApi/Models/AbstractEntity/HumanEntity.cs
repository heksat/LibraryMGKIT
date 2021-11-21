using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Models
{
    /// <summary>
    /// Описание человеческой сущности
    /// </summary>
    public abstract class HumanEntity:Entity
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
        public Char Gender { get; set; }
    }
}

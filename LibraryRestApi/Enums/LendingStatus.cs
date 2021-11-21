using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryRestApi.Enums
{
    public enum LendingStatus
    {
        /// <summary>
        /// Неопределенный статус
        /// </summary>
        None = 0,
        /// <summary>
        /// Зарезервирован
        /// </summary>
        Reserved = 1,
        /// <summary>
        /// На руках у читателя
        /// </summary>
        OnHands= 2,
        /// <summary>
        /// Предупреждение о возврате
        /// </summary>
        ReturnRequest = 3,
        /// <summary>
        /// Вернул
        /// </summary>
        Returned = 4 
    }
}

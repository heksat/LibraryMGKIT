package com.example.librarymgkitclient.Enums

import com.google.gson.annotations.SerializedName

enum class enumStatusBook(val value:String) {
    /// <summary>
    /// Неопределенный статус
    /// </summary>
    @SerializedName("0")
    None("None"),
    /// <summary>
    /// Зарезервирован
    /// </summary>
    @SerializedName("1")
    Reserved("Reserved"),
    /// <summary>
    /// На руках у читателя
    /// </summary>
    @SerializedName("2")
    OnHands("OnHands"),
    /// <summary>
    /// Предупреждение о возврате
    /// </summary>
    @SerializedName("3")
    ReturnRequest("ReturnRequest"),
    /// <summary>
    /// Вернул
    /// </summary>
    @SerializedName("4")
    Returned("Returned")
}
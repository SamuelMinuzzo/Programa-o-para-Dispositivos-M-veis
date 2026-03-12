package br.edu.utfpr.appcontatos.data

data class Contact( //seria uma entidade
    val id: Int = 0, // trabalhar com valores default para evitar os nulos
    val firstName: String = "",
    val lastname:  String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val isFavorite: Boolean = false,

){
    val fullName get(): String = "$firstName $lastname".trim()
}

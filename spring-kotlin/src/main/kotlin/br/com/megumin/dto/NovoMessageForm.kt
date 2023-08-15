package br.com.megumin.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoMessageForm(
        @field:NotEmpty(message = "Titulo nao pode ser em branco")
        @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres")
        val titulo: String,
        @field:NotEmpty(message = "Mensagem nao pode ser em branco")
        val mensagem: String,
        @field:NotNull
        val idAnime: Long,
        @field:NotNull
        val idAutor: Long
)

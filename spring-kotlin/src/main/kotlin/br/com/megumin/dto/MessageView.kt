package br.com.megumin.dto

import br.com.megumin.model.StatusMessage
import java.time.LocalDateTime

data class MessageView(
        val id: Long?,
        val titulo: String,
        val mensagem: String,
        val status: StatusMessage,
        val dataCriacao: LocalDateTime
)

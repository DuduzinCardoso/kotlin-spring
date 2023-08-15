package br.com.megumin.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Resposta(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val mensagem: String,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val autor: Usuario,
)
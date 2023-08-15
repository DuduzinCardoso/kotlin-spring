package br.com.megumin.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Message(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var titulo: String,
        var text: String,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val anime: Anime,
        @ManyToOne
        val autor: Usuario,
        @Enumerated(value = EnumType.STRING)
        val status: StatusMessage = StatusMessage.NAO_ENVIADO,
        @OneToMany(mappedBy = "message")
        val respostas: List<Resposta> = ArrayList()
)
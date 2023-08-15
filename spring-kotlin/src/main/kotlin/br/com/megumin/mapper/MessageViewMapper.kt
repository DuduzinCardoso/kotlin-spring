package br.com.megumin.mapper

import br.com.megumin.dto.MessageView
import br.com.megumin.model.Message
import org.springframework.stereotype.Component

@Component
class MessageViewMapper: Mapper<Message, MessageView> {

    override fun map(dto: Message): MessageView {
        return MessageView(
                id = dto.id,
                titulo = dto.titulo,
                mensagem = dto.text,
                dataCriacao = dto.dataCriacao,
                status = dto.status
        )
    }
}
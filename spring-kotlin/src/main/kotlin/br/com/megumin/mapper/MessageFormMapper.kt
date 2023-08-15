package br.com.megumin.mapper

import br.com.megumin.dto.NovoMessageForm
import br.com.megumin.model.Message
import br.com.megumin.service.AnimeService
import br.com.megumin.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class MessageFormMapper(
        private val animeService: AnimeService,
        private val usuarioService: UsuarioService
): Mapper<NovoMessageForm, Message> {
    override fun map(dto: NovoMessageForm): Message {
        return Message(
                titulo = dto.titulo,
                text = dto.mensagem,
                anime = animeService.buscarPorId(dto.idAnime),
                autor = usuarioService.buscarPorId(dto.idAutor)
        )
    }

}

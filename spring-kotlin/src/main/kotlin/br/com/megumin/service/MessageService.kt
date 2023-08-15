package br.com.megumin.service

import br.com.megumin.dto.AtualizacaoMessageForm
import br.com.megumin.dto.NovoMessageForm
import br.com.megumin.dto.MessagePorCategoriaDto
import br.com.megumin.dto.MessageView
import br.com.megumin.exception.NotFoundException
import br.com.megumin.mapper.MessageFormMapper
import br.com.megumin.mapper.MessageViewMapper
import br.com.megumin.repository.MessageRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class MessageService(
        private val repository: MessageRepository,
        private val messageViewMapper: MessageViewMapper,
        private val messageFormMapper: MessageFormMapper,
        private val notFoundMessage: String = "Mensagem nao encontrado!",
        private val em: EntityManager
) {

    fun listar(
            nomeAnime: String?,
            paginacao: Pageable
    ): Page<MessageView> {
        print(em)
        val messages = if (nomeAnime == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByAnimeNome(nomeAnime, paginacao)
        }
        return messages.map { t ->
            messageViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): MessageView {
        val message = repository.findById(id)
                .orElseThrow{ NotFoundException(notFoundMessage) }
        return messageViewMapper.map(message)
    }

    fun cadastrar(form: NovoMessageForm): MessageView {
        val message = messageFormMapper.map(form)
        repository.save(message)
        return messageViewMapper.map(message)
    }

    fun atualizar(form: AtualizacaoMessageForm): MessageView {
        val message = repository.findById(form.id)
                .orElseThrow{ NotFoundException(notFoundMessage) }
        message.titulo = form.titulo
        message.text = form.mensagem
        return messageViewMapper.map(message)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<MessagePorCategoriaDto> {
        return repository.relatorio()
    }

}
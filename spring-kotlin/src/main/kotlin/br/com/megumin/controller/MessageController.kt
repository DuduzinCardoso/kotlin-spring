package br.com.megumin.controller

import br.com.megumin.dto.AtualizacaoMessageForm
import br.com.megumin.dto.NovoMessageForm
import br.com.megumin.dto.MessagePorCategoriaDto
import br.com.megumin.dto.MessageView
import br.com.megumin.service.MessageService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/messages")
class MessageController(private val service: MessageService) {

    @GetMapping
    @Cacheable("messages")
    fun listar(
            @RequestParam(required = false) nomeAnime: String?,
            @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<MessageView> {
        return service.listar(nomeAnime, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): MessageView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["messages"], allEntries = true)
    fun cadastrar(
            @RequestBody @Valid form: NovoMessageForm,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<MessageView> {
        val messageView = service.cadastrar(form)
        val uri = uriBuilder.path("/messages/${messageView.id}").build().toUri()
        return ResponseEntity.created(uri).body(messageView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["messages"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoMessageForm): ResponseEntity<MessageView> {
        val messageView = service.atualizar(form)
        return ResponseEntity.ok(messageView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["messages"], allEntries = true)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<MessagePorCategoriaDto> {
        return service.relatorio()
    }

}
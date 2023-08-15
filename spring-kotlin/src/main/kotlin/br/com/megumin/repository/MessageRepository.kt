package br.com.megumin.repository

import br.com.megumin.dto.MessagePorCategoriaDto
import br.com.megumin.model.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: JpaRepository<Message, Long> {

    fun findByAnimeNome(nomeAnime: String, paginacao: Pageable): Page<Message>

    @Query("SELECT new br.com.megumin.dto.MessagePorCategoriaDto(anime.categoria, count(t)) FROM Message t JOIN t.anime anime GROUP BY anime.categoria")
    fun relatorio(): List<MessagePorCategoriaDto>

}
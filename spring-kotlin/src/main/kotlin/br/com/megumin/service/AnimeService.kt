package br.com.megumin.service

import br.com.megumin.model.Anime
import br.com.megumin.repository.AnimeRepository
import org.springframework.stereotype.Service

@Service
class AnimeService(private val repository: AnimeRepository) {

    fun buscarPorId(id: Long): Anime {
        return repository.getOne(id)
    }
}

package br.com.megumin.repository

import br.com.megumin.model.Anime
import org.springframework.data.jpa.repository.JpaRepository


interface AnimeRepository: JpaRepository<Anime, Long> {
}
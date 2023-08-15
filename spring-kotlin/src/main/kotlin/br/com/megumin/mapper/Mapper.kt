package br.com.megumin.mapper

interface Mapper<T, U> {
    fun map(dto: T): U
}

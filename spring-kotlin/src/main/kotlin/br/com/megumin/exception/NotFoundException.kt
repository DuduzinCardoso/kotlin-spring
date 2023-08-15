package br.com.megumin.exception

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}
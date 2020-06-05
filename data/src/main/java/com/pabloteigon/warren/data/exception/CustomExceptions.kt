package com.pabloteigon.warren.data.exception

class UnauthorizedException(message: String? = "") : Exception(message)

class AccessDeniedException(message: String? = "") : Exception(message)

class ConflictException(message: String? = "") : Exception(message)

class BadRequestException(message: String? = "") : Exception(message)
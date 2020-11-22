package com.example.cache.mapper

interface CacheMapper<C, E> {
    fun mapFromCache(cache: C): E
    fun mapFromEntity(entity: E): C
}
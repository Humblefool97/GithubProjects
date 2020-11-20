package com.example.data.mapper

interface EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D

    fun mapToEntity(data: D): E
}
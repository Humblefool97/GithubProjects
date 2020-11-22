package com.example.presentation.mapper

interface Mapper<V, D> {
    fun fromMapToView(type: D): V
}
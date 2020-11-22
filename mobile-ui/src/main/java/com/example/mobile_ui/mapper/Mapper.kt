package com.example.mobile_ui.mapper

interface Mapper<P,V> {
    fun mapFromView(presentation:P):V
}
package com.example.remote.service

import com.example.remote.model.ProjectResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun searchRepo(
        @Query("q") query: String,
        @Query("sort") sortBy: String,
        @Query("order") order: String
    ): Observable<ProjectResponseModel>
}
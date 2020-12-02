package com.jintin.retrofitsample

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(
        @Path("user") user: String,
        @Query("type") type: String? = null,
        @Query("sort") sort: String? = null
    ): List<Repo>

}

package com.jintin.retrofitsample

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(
        @Path("user") user: String,
        @Query("type") type: String? = null,
        @Query("sort") sort: String? = null
    ): Single<List<Repo>>

}

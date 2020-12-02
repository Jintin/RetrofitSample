package com.jintin.retrofitsample

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Int,
    val name: String,
    val owner: User,
    @SerializedName("stargazers_count")
    val starCount: Int
)

data class User(
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
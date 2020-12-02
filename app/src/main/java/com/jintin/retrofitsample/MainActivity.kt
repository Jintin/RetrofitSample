package com.jintin.retrofitsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RepoAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(GitHubService::class.java)

        service.listRepos("Jintin")
                .enqueue(object : Callback<List<Repo>> {
                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Network fail or other error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (response.isSuccessful) {
                            // success
                            response.body().apply(adapter::submitList)
                        } else {
                            Toast.makeText(this@MainActivity, "Application level fail", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }
}
package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(arrayListOf())
    var page = 1
    var perPage = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding){
            updateResultBtn.setOnClickListener {
                perPage += 5
                adapter.notifyItemChanged(0)
                doRequest()
            }
            findBtn.setOnClickListener {
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        RetrofitService.api.getImage(photoEd.text.toString(), page = page, perPage = perPage)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter = ImageAdapter(response.body()!!.hits)
                        binding.resyclerView.adapter = this@MainActivity.adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {

                }

            })
    }
}
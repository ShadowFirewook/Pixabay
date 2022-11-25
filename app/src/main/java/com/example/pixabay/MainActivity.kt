package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pixabay.databinding.ActivityMainBinding
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(arrayListOf())
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding){
            updateResultBtn.setOnClickListener {

                doRequest(++page)


            }
            findBtn.setOnClickListener {
                adapter.clearResult()
                page = 1
                doRequest(page)

            }
        }
    }



    private fun ActivityMainBinding.doRequest(page:Int) {
        RetrofitService.api.getImage(photoEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.let {it.forEach{
                            adapter.addImage(it)
                        }

                        }
                        binding.resyclerView.adapter = this@MainActivity.adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo","onFailure${t.message}")
                }

            })
    }
}

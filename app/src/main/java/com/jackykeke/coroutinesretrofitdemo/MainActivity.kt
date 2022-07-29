package com.jackykeke.coroutinesretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jackykeke.coroutinesretrofitdemo.interfaze.GithubApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class MainActivity : AppCompatActivity() {

    val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ... 初始化RecyclerView
        fetchData()
    }

    private fun fetchData() {

        scope.launch {

            try {
                val result = GithubApi.createGithubApi().searchRepos("Android", 0, 20)
                if(result != null && !result.items.isNullOrEmpty()){
                    Log.i("MainActivity","result:${result.toString()}")
                }
            }catch (e: Exception){
                Log.i("MainActivity","Exception:${e.message}")
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel(CancellationException())
    }
}
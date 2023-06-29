package com.example.adityatest05

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDetail()
    }

    private fun getDetail() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.data.collect {
                when (it) {
                    is ApiState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            text.isVisible = true

                            text.text = it.data.Message.toString()
                        }

                    }
                    is ApiState.Failure -> {
                        binding.apply {
                            progress.isVisible = false
                            text.isVisible = true

                            text.text = it.msg
                        }
                    }
                    ApiState.Loading -> {
                        binding.apply {
                            progress.isVisible = true
                            text.isVisible = false

                        }
                    }
                }
            }
        }
    }

}
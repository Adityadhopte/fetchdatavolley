package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topLinksButton.setOnClickListener {
            getDetail()
        }
    }

    private fun getDetail() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.data.collect {
                when (it) {
                    is ApiState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            text.isVisible = true

                            text.text = it.data.supportWhatsappNumber.toString()
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
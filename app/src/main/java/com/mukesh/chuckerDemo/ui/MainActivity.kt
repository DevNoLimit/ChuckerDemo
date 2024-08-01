package com.mukesh.chuckerDemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.mukesh.chuckerDemo.R
import com.mukesh.chuckerDemo.databinding.ActivityMainBinding
import com.mukesh.chuckerDemo.utils.observeData

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clicks()
        observePostsData()
        observePostDetail()
        observeCommentsData()
        observerAddPost()
    }


    private fun clicks(){
        binding.btCallApi.setOnClickListener {
            viewModel.getAllPosts()
        }
    }


    private fun observePostsData() = viewModel.allPosts.observeData(
        lifecycleOwner = this,
        onLoading = {
            binding.progressBar.isVisible = true
            binding.btCallApi.visibility = View.INVISIBLE
        },
        onSuccess = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            findViewById<TextView>(R.id.tvData).text = it.toString()
        },
        onError = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    )



    private fun observePostDetail() = viewModel.postDetail.observeData(
        lifecycleOwner = this,
        onLoading = {
            binding.progressBar.isVisible = true
            binding.btCallApi.visibility = View.INVISIBLE
        }, onError = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }, onSuccess = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            binding.tvData.text = it.toString()
        }
    )



    private fun observeCommentsData() = viewModel.commentData.observeData(
        lifecycleOwner = this,
        onLoading = {
            binding.progressBar.isVisible = true
            binding.btCallApi.visibility = View.INVISIBLE
        },
        onSuccess = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            findViewById<TextView>(R.id.tvData).text = it.toString()
        },
        onError = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    )



    private fun observerAddPost() = viewModel.addPost.observeData(
        lifecycleOwner = this,
        onLoading = {
            binding.progressBar.isVisible = true
            binding.btCallApi.visibility = View.INVISIBLE
        },
        onSuccess = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            findViewById<TextView>(R.id.tvData).text = it.toString()
        },
        onError = {
            binding.progressBar.isVisible = false
            binding.btCallApi.isVisible = true
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    )

}
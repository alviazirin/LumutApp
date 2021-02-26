package com.example.alvimaghfirantoazirin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alvimaghfirantoazirin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        val EXTRA_ID = "extra"
    }

    private var _detailActivityBinding: ActivityDetailBinding? = null
    private val binding get() = _detailActivityBinding

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val id = intent.getStringExtra(EXTRA_ID)
        if (id!=null){
            viewModel.fetchDetail(id.toInt())
            viewModel.getData()
        }


    }
}
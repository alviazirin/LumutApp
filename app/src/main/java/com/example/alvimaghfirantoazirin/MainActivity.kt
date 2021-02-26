package com.example.alvimaghfirantoazirin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alvimaghfirantoazirin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainActivViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        adapter = MainAdapter()
        adapter.notifyDataSetChanged()
        binding?.rvMain?.layoutManager = LinearLayoutManager(this)
        binding?.rvMain?.setHasFixedSize(true)


        viewModel.fetchData()
        viewModel.getData().observe(this, {data ->
            if (data!=null){
                adapter.setData(data)

                adapter.setOnItemClick(object : MainAdapter.OnItemClick{
                    override fun onItemClicked(data: Entity) {
                        val intent = Intent (this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_ID, data.id)
                        startActivity(intent)
                    }

                })
            }
        })
    }
}
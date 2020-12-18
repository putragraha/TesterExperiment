package com.android.nsystem.testapps.fruit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nsystem.testapps.databinding.ActivityFruitListBinding

class FruitListActivity : AppCompatActivity(), FruitListContract.View {

    private lateinit var binding: ActivityFruitListBinding

    private lateinit var fruitAdapter: FruitAdapter

    private lateinit var presenter: FruitListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupComponent()
    }

    override fun onSuccessGetData(fruits: List<Fruit>) {
        fruitAdapter.submitList(fruits)
    }

    private fun setupComponent() {
        fruitAdapter = FruitAdapter { fruit ->
            FruitDetailActivity.startActivity(
                this,
                Bundle().apply {
                    putString(FruitDetailActivity.FRUIT_NAME, fruit.name)
                    putInt(FruitDetailActivity.FRUIT_IMAGE, fruit.image)
                }
            )
        }
        presenter = FruitListPresenter(this).apply {
            getData()
        }
        binding.rvFruit.apply {
            layoutManager = LinearLayoutManager(this@FruitListActivity)
            adapter = fruitAdapter
        }
    }
}
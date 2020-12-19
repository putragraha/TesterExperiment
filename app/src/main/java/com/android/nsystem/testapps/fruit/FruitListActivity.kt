package com.android.nsystem.testapps.fruit

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nsystem.testapps.R
import com.android.nsystem.testapps.databinding.ActivityFruitListBinding
import com.android.nsystem.testapps.webview.WebviewActivity
import java.util.Locale

class FruitListActivity : AppCompatActivity(), FruitListContract.View {

    companion object {

        const val MAP_REQUEST_CODE = 1
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fruit_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_open_webview -> {
                openWebView()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        requestMapPermission()
        binding.fabMessage.setOnClickListener { openMap() }
    }

    private fun requestMapPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            MAP_REQUEST_CODE
        )
    }

    private fun openMap() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(String.format(
                    Locale.ENGLISH,
                    "geo:%f,%f",
                    0.5348769939376866,
                    101.44729466832969)
                )
            )
        )
    }

    private fun openWebView() {
        startActivity(Intent(this, WebviewActivity::class.java))
    }
}
package com.android.nsystem.testapps.fruit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.nsystem.testapps.R
import com.android.nsystem.testapps.databinding.ActivityFruitDetailBinding

class FruitDetailActivity : AppCompatActivity() {

    companion object {

        const val FRUIT_NAME = "FRUIT_NAME"

        const val FRUIT_IMAGE = "FRUIT_IMAGE"

        fun startActivity(
            source: Activity,
            bundle: Bundle
        ) {
            source.startActivity(
                Intent(source, FruitDetailActivity::class.java).apply {
                    putExtras(bundle)
                }
            )
        }
    }

   private lateinit var binding: ActivityFruitDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityFruitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupComponent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_share -> {
                sendShareIntent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fruit_menu, menu)
        return true
    }

    private fun setupComponent() = with(binding) {
        actvFruitName.text = intent?.extras?.getString(FRUIT_NAME) ?: "NA"
        acivFruitImage.setImageResource(intent?.extras?.getInt(FRUIT_IMAGE) ?: 0)
    }

    private fun sendShareIntent() {
        startActivity(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, intent?.extras?.getString(FRUIT_NAME))
                type = "text/plain"
            }
        )
    }
}
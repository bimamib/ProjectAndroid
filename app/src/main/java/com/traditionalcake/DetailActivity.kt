package com.traditionalcake

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvDeskripsi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvNamaKue: TextView = findViewById(R.id.tv_cake_name)
        tvDeskripsi = findViewById(R.id.tv_data_received_description)
        val tvDaerah: TextView = findViewById(R.id.tv_data_daerah_asal)
        val buttonShare: Button = findViewById(R.id.action_share)

        val traditionalCake = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<TraditionalCake>(
                EXTRA_TRADITIONAL_CAKE,
                TraditionalCake::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<TraditionalCake>(EXTRA_TRADITIONAL_CAKE)
        }

        if (traditionalCake != null) {
            imgPhoto.setImageResource(traditionalCake.photo)
            tvNamaKue.text = traditionalCake.name
            tvDeskripsi.text = traditionalCake.description
            tvDaerah.text = traditionalCake.daerah
        }

        buttonShare.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this@DetailActivity, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, tvDeskripsi.text)
        startActivity(Intent.createChooser(intent, null))
    }

    companion object {
        const val EXTRA_TRADITIONAL_CAKE = "extra_traditional_cake"
    }
}

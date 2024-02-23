package com.traditionalcake

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTraditionalCake: RecyclerView
    private val list = ArrayList<TraditionalCake>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTraditionalCake = findViewById(R.id.rv_traditional_cake)
        rvTraditionalCake.setHasFixedSize(true)

        list.addAll(getListTraditionalCake())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent (this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
        @Suppress("UNREACHABLE_CODE")
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListTraditionalCake(): ArrayList<TraditionalCake> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataDaerahAsal = resources.getStringArray(R.array.data_daerah)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listTraditionalCake = ArrayList<TraditionalCake>()
        for (i in dataName.indices) {
            val traditionalCake = TraditionalCake(
                dataName[i],
                dataDescription[i],
                dataDaerahAsal[i],
                dataPhoto.getResourceId(i, -1)
            )
            listTraditionalCake.add(traditionalCake)
        }
        return listTraditionalCake
    }

    private fun showRecyclerList() {
        rvTraditionalCake.layoutManager = LinearLayoutManager(this)
        val listTraditionalCakeAdapter = ListTraditionalCakeAdapter(list)
        rvTraditionalCake.adapter = listTraditionalCakeAdapter

        listTraditionalCakeAdapter.setOnItemClickCallback(object :
            ListTraditionalCakeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TraditionalCake) {
                showSelectedTraditionalCake(data)
            }
        })
    }

    private fun showSelectedTraditionalCake(traditionalCake: TraditionalCake) {
        val moveDetail = Intent(this@MainActivity, DetailActivity::class.java).putExtra(
            DetailActivity.EXTRA_TRADITIONAL_CAKE,
            traditionalCake
        )
        startActivity(moveDetail)
    }
}
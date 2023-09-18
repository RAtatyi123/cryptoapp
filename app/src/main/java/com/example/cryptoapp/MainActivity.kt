package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.presentation.coinlist.CoinAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var coinAdapter : CoinAdapter
    private lateinit var layoutManager : GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpTheRecyclerView()
        layoutManager = GridLayoutManager(this,2)
    }

    private fun setUpTheRecyclerView() {
        coinAdapter = CoinAdapter(this@MainActivity,ArrayList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

}
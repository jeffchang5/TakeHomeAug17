package me.jeffreychang.walmarttakehome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.jeffreychang.walmarttakehome.databinding.ActivityMainBinding

class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
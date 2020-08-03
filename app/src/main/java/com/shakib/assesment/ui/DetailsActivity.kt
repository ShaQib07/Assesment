package com.shakib.assesment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shakib.assesment.databinding.ActivityDetailsBinding
import com.shakib.assesment.model.Data

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user :Data = intent.getSerializableExtra("User") as Data
        binding.user = user

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
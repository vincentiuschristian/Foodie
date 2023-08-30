package com.example.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodie.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    companion object {
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_EMAIL = "extra_email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val tvName = binding.tvNameProfile
        val tvEmail = binding.tvEmailDicoding

        val name = intent.getStringExtra(EXTRA_NAMA)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        tvName.text = name
        tvEmail.text = email
    }
}
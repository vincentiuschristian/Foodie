package com.example.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodie.databinding.ActivityAboutPageBinding

class AboutPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutPageBinding
    companion object {
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_EMAIL = "extra_email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)

        binding = ActivityAboutPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.about)

        val tvName = binding.tvNameProfile
        val tvEmail = binding.tvEmailDicoding

        val name = intent.getStringExtra(EXTRA_NAMA)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        tvName.text = name
        tvEmail.text = email
    }
}
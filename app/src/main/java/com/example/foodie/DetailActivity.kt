package com.example.foodie

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.foodie.data.Foodie
import com.example.foodie.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val KEY_FOOD = "key_food"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val tvName: TextView = binding.tvNameDetail
        val tvTime: TextView = binding.tvTimeDetail
        val tvServing: TextView = binding.tvServingDetail
        val imgFood: ImageView = binding.imgFoodDetail
        val tvIngredients: TextView = binding.tvIngredients
        val tvDirection: TextView = binding.tvDirections
        val tvCal: TextView = binding.tvCalories

        val dataFood = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_FOOD, Foodie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_FOOD)
        }

        if (dataFood != null) {
            val name = dataFood.name
            tvName.text = name
            val time = dataFood.time
            tvTime.text = time
            val img = dataFood.photo
            imgFood.setImageResource(img)
            val serving = dataFood.servings
            tvServing.text = "$serving servings"
            val ing = dataFood.ingredients
            tvIngredients.text = ing
            val direction = dataFood.directions
            tvDirection.text = direction
            val cal = dataFood.calories
            tvCal.text = cal
        }

        binding.shareAction.setOnClickListener {
            val nameRecipe = tvName.text.toString()
            val ingr = tvIngredients.text.toString()
            val direc = tvDirection.text.toString()
            val shareText = "$nameRecipe \nIngredients:\n$ingr \n\nDirections:\n$direc"
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, shareText)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }

    override fun onClick(v: View?) {
    }
}


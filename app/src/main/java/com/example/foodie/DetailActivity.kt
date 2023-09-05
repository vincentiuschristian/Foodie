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

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvName: TextView = binding.tvNameDetail
        val tvTime: TextView = binding.tvTimeDetail
        val tvServing: TextView = binding.tvServingDetail
        val imgFood: ImageView = binding.imgFoodDetail
        val tvIngredients: TextView = binding.tvIngredients
        val tvInstruction: TextView = binding.tvInstructions
        val tvCal: TextView = binding.tvCalories

        val dataFood = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_FOOD, Foodie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_FOOD)
        }

        val nameFood = dataFood?.name
        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.detailFood, nameFood)

        if (dataFood != null) {
            val name = dataFood.name
            tvName.text = name
            val time = dataFood.time
            tvTime.text = time
            val img = dataFood.photo
            imgFood.setImageResource(img)
            val serving = dataFood.servings
            tvServing.text = resources.getString(R.string.servings_detail, serving)
            val ing = dataFood.ingredients
            tvIngredients.text = ing
            val instructions = dataFood.instructions
            tvInstruction.text = instructions
            val cal = dataFood.calories
            tvCal.text = cal
        }



        binding.shareAction.setOnClickListener {
            val nameRecipe = tvName.text.toString()
            val ingredients = tvIngredients.text.toString()
            val instructions = tvInstruction.text.toString()
            val shareText = resources.getString(R.string.share_detail, nameRecipe, ingredients, instructions)
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


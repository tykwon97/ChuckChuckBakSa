package org.tensorflow.codelabs.objectdetection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.R
import org.tensorflow.odelabs.objectdetection.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){

        binding.ConfusedBtn.setOnClickListener {
            //분류된 결과 초기화하고 들어가기
            App.prefs1!!.myIndex1 = ""

            val intent = Intent(this,TipActivity::class.java)
            startActivity(intent)
        }

        binding.PlasticBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.plastic)
        }

        binding.CanBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.can)
        }

        binding.VinylBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.vinly)
        }

        binding.GlassBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.glass)
        }

        binding.PaperBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.paper)
        }

        binding.StyrofoamBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.styrofoam)
        }

        binding.BatteryBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.battery)
        }

        binding.HomeApplianceBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.homeappliance)
        }

        binding.ClothBtn.setOnClickListener {
            binding.DetailImg.setImageResource(R.drawable.cloth)
        }
    }


}
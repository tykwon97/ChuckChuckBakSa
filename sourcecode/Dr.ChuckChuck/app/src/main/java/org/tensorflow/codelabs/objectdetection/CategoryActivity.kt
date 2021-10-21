package org.tensorflow.codelabs.objectdetection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.R

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        init()
    }
    private fun init(){
        val plastic = findViewById<Button>(R.id.Plastic_btn)
        val can = findViewById<Button>(R.id.Can_btn)
        val vinyl = findViewById<Button>(R.id.Vinyl_btn)
        val glass = findViewById<Button>(R.id.Glass_btn)
        val paper = findViewById<Button>(R.id.Paper_btn)
        val styrofoam = findViewById<Button>(R.id.Styrofoam_btn)
        val confused = findViewById<Button>(R.id.Confused_btn)
        val homeapplicane = findViewById<Button>(R.id.HomeAppliance_btn)
        val cloth = findViewById<Button>(R.id.Cloth_btn)
        val battery = findViewById<Button>(R.id.Battery_btn)
        val image = findViewById<ImageView>(R.id.Detail_img)

        confused.setOnClickListener {
            //분류된 결과 초기화하고 들어가기
            App.prefs1!!.myIndex1 = ""

            val intent = Intent(this,TipActivity::class.java)
            startActivity(intent)
        }

        plastic.setOnClickListener {
            image.setImageResource(R.drawable.plastic)
        }

        can.setOnClickListener {
            image.setImageResource(R.drawable.can)
        }

        vinyl.setOnClickListener {
            image.setImageResource(R.drawable.vinly)
        }

        glass.setOnClickListener {
            image.setImageResource(R.drawable.glass)
        }

        paper.setOnClickListener {
            image.setImageResource(R.drawable.paper)
        }

        styrofoam.setOnClickListener {
            image.setImageResource(R.drawable.styrofoam)
        }

        battery.setOnClickListener {
            image.setImageResource(R.drawable.battery)
        }

        homeapplicane.setOnClickListener {
            image.setImageResource(R.drawable.homeappliance)
        }

        cloth.setOnClickListener {
            image.setImageResource(R.drawable.cloth)
        }
    }


}
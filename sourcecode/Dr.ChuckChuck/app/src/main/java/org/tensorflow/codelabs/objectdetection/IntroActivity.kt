package org.tensorflow.codelabs.objectdetection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        init()
    }
    private fun init(){
        val btn1 = findViewById<Button>(R.id.start_btn)
        val btn2 = findViewById<Button>(R.id.category_btn)

        btn1.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}
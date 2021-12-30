package org.tensorflow.codelabs.objectdetection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){

        binding.startBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.categoryBtn.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}
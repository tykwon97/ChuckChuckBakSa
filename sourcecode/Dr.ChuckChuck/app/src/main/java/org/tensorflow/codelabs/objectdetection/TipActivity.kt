package org.tensorflow.codelabs.objectdetection

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.R
import org.tensorflow.odelabs.objectdetection.databinding.ActivityTipBinding

class TipActivity : AppCompatActivity() {
    lateinit var binding : ActivityTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
       //저장된 값 가져오기
        var category_name = App.prefs1.myIndex1!!
        binding.categorytext.setText(category_name)

        if(category_name == "젖병") {
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_feedingbottle)
        } else if(category_name == "건전지"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_battery)
        } else if(category_name == "캔"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_can)
        } else if(category_name == "컵라면"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_cupramen)
        }else if(category_name == "아이스팩"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_icepack)
        }else if(category_name == "우유팩"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_milk)
        }else if(category_name == "종이컵"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_papercup)
        }else if(category_name == "페트병"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_petbottle)
        }else if(category_name == "고무장갑"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_rubberglove)
        }else if(category_name == "일회용 숟가락"){
            blind(1)
            binding.categoryImage.setImageResource(R.drawable.category_plasticspoon)
        }else{ //분류 결과에 대한 정보가 아닌 경우
            binding.textView4.visibility = View.INVISIBLE
            binding.categorytext.visibility = View.INVISIBLE
            false
        }

        binding.button1.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_petbottle)
        }

        binding.button2.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_can)
        }

        binding.button3.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_milk)
        }

        binding.button4.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_cupramen)
        }

        binding.button5.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_papercup)
        }

        binding.button6.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_feedingbottle)
        }

        binding.button7.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_battery)
        }

        binding.button8.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_icepack)
        }

        binding.button9.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_plasticspoon)
        }

        binding.button10.setOnClickListener {
            blind(0)
            binding.categoryImage.setImageResource(R.drawable.category_rubberglove)
        }

    }

    fun blind(n:Int){
        if(n == 1){ //button가리는함수
            binding.button1.visibility = View.INVISIBLE
            binding.button2.visibility = View.INVISIBLE
            binding.button3.visibility = View.INVISIBLE
            binding.button4.visibility = View.INVISIBLE
            binding.button5.visibility = View.INVISIBLE
            binding.button6.visibility = View.INVISIBLE
            binding.button7.visibility = View.INVISIBLE
            binding.button8.visibility = View.INVISIBLE
            binding.button9.visibility = View.INVISIBLE
            binding.button10.visibility = View.INVISIBLE
        }else{ //button 보여주고 분류결과 text 가리기
            binding.button1.visibility = View.VISIBLE
            binding.button2.visibility = View.VISIBLE
            binding.button3.visibility = View.VISIBLE
            binding.button4.visibility = View.VISIBLE
            binding.button5.visibility = View.VISIBLE
            binding.button6.visibility = View.VISIBLE
            binding.button7.visibility = View.VISIBLE
            binding.button8.visibility = View.VISIBLE
            binding.button9.visibility = View.VISIBLE
            binding.button10.visibility = View.VISIBLE
        }
    }
}

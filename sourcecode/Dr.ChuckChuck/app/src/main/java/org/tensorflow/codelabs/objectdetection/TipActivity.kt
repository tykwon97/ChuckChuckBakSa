package org.tensorflow.codelabs.objectdetection

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.odelabs.objectdetection.R

class TipActivity : AppCompatActivity() {

    private lateinit var categorytext: TextView
    private lateinit var text1 : TextView
    private lateinit var image : ImageView
    private lateinit var b1 : Button
    private lateinit var b2 : Button
    private lateinit var b3 : Button
    private lateinit var b4 : Button
    private lateinit var b5 : Button
    private lateinit var b6 : Button
    private lateinit var b7 : Button
    private lateinit var b8 : Button
    private lateinit var b9 : Button
    private lateinit var b10 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip)
        init()
    }

    fun init() {
        categorytext = findViewById(R.id.categorytext)
        image = findViewById(R.id.category_image)
        text1 = findViewById(R.id.textView4)

        b1 = findViewById<Button>(R.id.button1)
        b2 = findViewById<Button>(R.id.button2)
        b3 = findViewById<Button>(R.id.button3)
        b4 = findViewById<Button>(R.id.button4)
        b5 = findViewById<Button>(R.id.button5)
        b6 = findViewById<Button>(R.id.button6)
        b7 = findViewById<Button>(R.id.button7)
        b8 = findViewById<Button>(R.id.button8)
        b9 = findViewById<Button>(R.id.button9)
        b10 = findViewById<Button>(R.id.button10)

       //저장된 값 가져오기
        var category_name = App.prefs1.myIndex1!!
        categorytext.setText(category_name)

        if(category_name == "젖병") {
            blind(1)
            image.setImageResource(R.drawable.category_feedingbottle)
        } else if(category_name == "건전지"){
            blind(1)
            image.setImageResource(R.drawable.category_battery)
        } else if(category_name == "캔"){
            blind(1)
            image.setImageResource(R.drawable.category_can)
        } else if(category_name == "컵라면"){
            blind(1)
            image.setImageResource(R.drawable.category_cupramen)
        }else if(category_name == "아이스팩"){
            blind(1)
            image.setImageResource(R.drawable.category_icepack)
        }else if(category_name == "우유팩"){
            blind(1)
            image.setImageResource(R.drawable.category_milk)
        }else if(category_name == "종이컵"){
            blind(1)
            image.setImageResource(R.drawable.category_papercup)
        }else if(category_name == "페트병"){
            blind(1)
            image.setImageResource(R.drawable.category_petbottle)
        }else if(category_name == "고무장갑"){
            blind(1)
            image.setImageResource(R.drawable.category_rubberglove)
        }else if(category_name == "일회용 숟가락"){
            blind(1)
            image.setImageResource(R.drawable.category_plasticspoon)
        }else{ //분류 결과에 대한 정보가 아닌 경우
            text1.visibility = View.INVISIBLE
            categorytext.visibility = View.INVISIBLE
            false
        }

        b1.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_petbottle)
        }

        b2.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_can)
        }

        b3.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_milk)
        }

        b4.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_cupramen)
        }

        b5.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_papercup)
        }

        b6.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_feedingbottle)
        }

        b7.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_battery)
        }

        b8.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_icepack)
        }

        b9.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_plasticspoon)
        }

        b10.setOnClickListener {
            blind(0)
            image.setImageResource(R.drawable.category_rubberglove)
        }

    }

    fun blind(n:Int){
        if(n == 1){ //button가리는함수
            b1.visibility = View.INVISIBLE
            b2.visibility = View.INVISIBLE
            b3.visibility = View.INVISIBLE
            b4.visibility = View.INVISIBLE
            b5.visibility = View.INVISIBLE
            b6.visibility = View.INVISIBLE
            b7.visibility = View.INVISIBLE
            b8.visibility = View.INVISIBLE
            b9.visibility = View.INVISIBLE
            b10.visibility = View.INVISIBLE
        }else{ //button 보여주고 분류결과 text 가리기
            b1.visibility = View.VISIBLE
            b2.visibility = View.VISIBLE
            b3.visibility = View.VISIBLE
            b4.visibility = View.VISIBLE
            b5.visibility = View.VISIBLE
            b6.visibility = View.VISIBLE
            b7.visibility = View.VISIBLE
            b8.visibility = View.VISIBLE
            b9.visibility = View.VISIBLE
            b10.visibility = View.VISIBLE
        }
    }
}

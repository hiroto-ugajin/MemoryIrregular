package jp.kanoyastore.hiroto.ugajin.memoryirregular

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import jp.kanoyastore.hiroto.ugajin.memoryirregular.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val drawableResId1 = R.drawable.cardgroup1
        binding.groupImage1.setImageResource(drawableResId1)

        val drawableResId2 = R.drawable.cardgroup2
        binding.groupImage2.setImageResource(drawableResId2)

        val drawableResId3 = R.drawable.cardgroup3touka
        binding.groupImage3.setImageResource(drawableResId3)


        binding.subTitle.text = "matching cards group"
        binding.subTitle.textSize = 20f
        binding.smallTitle1.text = "pairs from Trump cards"
        binding.smallTitle2.text = "3cards group:Karuta,female with red box,male with green box"
        binding.smallTitle3.text = "4cards group:Mahjomg,East,South,West and North"



    }
}
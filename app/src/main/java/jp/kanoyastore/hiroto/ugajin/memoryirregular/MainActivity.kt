package jp.kanoyastore.hiroto.ugajin.memoryirregular

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import jp.kanoyastore.hiroto.ugajin.memoryirregular.databinding.ActivityMainBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var mediaPlayer1: MediaPlayer
private lateinit var mediaPlayer2: MediaPlayer

class MainActivity : AppCompatActivity() {

    // クリックされたカードの情報を保持する変数
    private var firstCard: ImageButton? = null
    private var secondCard: ImageButton? = null
    private var thirdCard: ImageButton? = null
    private var fourthCard: ImageButton? = null


    private var firstCardIndex = -1
    private var secondCardIndex = -1
    private var thirdCardIndex = -1
    private var fourthCardIndex = -1

    private lateinit var binding: ActivityMainBinding

    val drawableArray = arrayOf(
        R.drawable.b0,
        R.drawable.b1,
        R.drawable.b2,
        R.drawable.b3,
        R.drawable.b4,
        R.drawable.b5,
        R.drawable.b6,
        R.drawable.b7,
        R.drawable.b8,
        R.drawable.b9,
        R.drawable.b10,
        R.drawable.b11,
        R.drawable.b12,
        R.drawable.b13,
        R.drawable.b14,
        R.drawable.b15
    )

    val imageButtonIds = arrayOf(
        R.id.button0,
        R.id.button1,
        R.id.button2,
        R.id.button3,
        R.id.button4,
        R.id.button5,
        R.id.button6,
        R.id.button7,
        R.id.button8,
        R.id.button9,
        R.id.button10,
        R.id.button11,
        R.id.button12,
        R.id.button13,
        R.id.button14,
        R.id.button15
    )

    val imageButtons: MutableList<ImageButton> = mutableListOf()

//    private fun disableAllImageButtons() {
//        for (imageButton in imageButtons) {
//            imageButton.isEnabled = false
//        }
//    }

//    private fun enableAllImageButtons() {
//        for (i in 0 until 16) {
//            val buttonId = resources.getIdentifier("button$i", "id", packageName)
//            val imageButton = findViewById<ImageButton>(buttonId)
//            imageButton.isEnabled = false
//        }
//    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mediaPlayer1 = MediaPlayer.create(this, R.raw.nice)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.boo)

        val shuffledDrawableArray = drawableArray.clone().apply {
            shuffle()
        }

        fun enableAllImageButtons() {
            binding.button0.isEnabled = true
            binding.button1.isEnabled = true
            binding.button2.isEnabled = true
            binding.button3.isEnabled = true
            binding.button4.isEnabled = true
            binding.button5.isEnabled = true
            binding.button6.isEnabled = true
            binding.button7.isEnabled = true
            binding.button8.isEnabled = true
            binding.button9.isEnabled = true
            binding.button10.isEnabled = true
            binding.button11.isEnabled = true
            binding.button12.isEnabled = true
            binding.button13.isEnabled = true
            binding.button14.isEnabled = true
            binding.button15.isEnabled = true
    }

       fun disableAllImageButtons() {
           binding.button0.isEnabled = false
           binding.button1.isEnabled = false
           binding.button2.isEnabled = false
           binding.button3.isEnabled = false
           binding.button4.isEnabled = false
           binding.button5.isEnabled = false
           binding.button6.isEnabled = false
           binding.button7.isEnabled = false
           binding.button8.isEnabled = false
           binding.button9.isEnabled = false
           binding.button10.isEnabled = false
           binding.button11.isEnabled = false
           binding.button12.isEnabled = false
           binding.button13.isEnabled = false
           binding.button14.isEnabled = false
           binding.button15.isEnabled = false
       }



                // カードがクリックされたときの処理
        for (i in 0 until 16) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val imageButton = findViewById<ImageButton>(buttonId)

                        imageButton.setOnClickListener {

                            // カードの画像を表示
                            imageButton.setImageResource(shuffledDrawableArray[i])
                            val element = shuffledDrawableArray[i]
                            disableAllImageButtons()
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(300)
                                enableAllImageButtons()
                                imageButton.isEnabled = false
                            }
                            // ボタンを無効化
//                            imageButton.isEnabled = false

//                            CoroutineScope(Dispatchers.Main).launch {
//                                delay(700)
//                                imageButton.isEnabled = true
//                            }


                            // 最初にめくったカードと次にめくったカードの画像が同じかどうかを判定
                            if (firstCard == null) {
                                // 最初のカード
                                firstCard = imageButton
                                firstCardIndex = drawableArray.indexOfFirst { it == element }


                            } else if (secondCard == null) {
                                // 2枚目のカード
                                secondCard = imageButton
                                secondCardIndex = drawableArray.indexOfFirst { it == element }

                                if (firstCardIndex <= 5 && secondCardIndex <= 5) {
                                    val isMatch = firstCardIndex / 2 == secondCardIndex / 2
                                    if (isMatch) {
                                        mediaPlayer1.start()
                                        // 2枚のカードを非表示にする
                                        firstCard?.visibility = View.INVISIBLE
                                        secondCard?.visibility = View.INVISIBLE
                                        // カードの情報をリセット
                                        firstCard = null
                                        secondCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0

                                    } else {
                                        mediaPlayer2.start()
                                        // Coroutineを使用して1秒後に背景画像に置き換える処理を実行
                                        CoroutineScope(Dispatchers.Main).launch {
                                            delay(1000)
                                            firstCard?.setImageResource(R.drawable.background_image)
                                            secondCard?.setImageResource(R.drawable.background_image)
                                            // カードの情報をリセット
                                            firstCard!!.isEnabled = true
                                            secondCard!!.isEnabled = true
                                            secondCard = null
                                            firstCard = null
                                            secondCard = null
                                            firstCardIndex = 0
                                            secondCardIndex = 0
                                        }
                                    }
                                } else if (!((5 < firstCardIndex && firstCardIndex <= 8
                                            && 5 < secondCardIndex && secondCardIndex <= 8)
                                            || (8 < firstCardIndex && firstCardIndex <= 11
                                            && 8 < secondCardIndex && secondCardIndex <= 11)
                                            || (11 < firstCardIndex && firstCardIndex <= 15
                                            && 11 < secondCardIndex && secondCardIndex <= 15)
                                            )
                                ) {
                                    mediaPlayer2.start()
                                    // Coroutineを使用して1秒後に背景画像に置き換える処理を実行
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(1000)
                                        firstCard?.setImageResource(R.drawable.background_image)
                                        secondCard?.setImageResource(R.drawable.background_image)
                                        // カードの情報をリセット
                                        firstCard!!.isEnabled = true
                                        secondCard!!.isEnabled = true
                                        firstCard = null
                                        secondCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0
                                    }
                                }
                            } else if (thirdCard == null) {
                                // 3枚目のカード
                                thirdCard = imageButton
                                thirdCardIndex = drawableArray.indexOfFirst { it == element }

                                if (5 < firstCardIndex && firstCardIndex <= 8
                                    && 5 < secondCardIndex && secondCardIndex <= 8
                                    && 5 < thirdCardIndex && thirdCardIndex <= 8
                                ) {
                                    mediaPlayer1.start()
                                    firstCard?.visibility = View.INVISIBLE
                                    secondCard?.visibility = View.INVISIBLE
                                    thirdCard?.visibility = View.INVISIBLE
                                    // カードの情報をリセット
                                    firstCard = null
                                    secondCard = null
                                    thirdCard = null
                                    firstCardIndex = 0
                                    secondCardIndex = 0
                                    thirdCardIndex = 0
                                } else if (5 < firstCardIndex && firstCardIndex <= 8
                                    && 5 < secondCardIndex && secondCardIndex <= 8
                                    && !(5 < thirdCardIndex && thirdCardIndex <= 8)
                                ) {
                                    mediaPlayer2.start()
                                    // Coroutineを使用して2秒後に背景画像に置き換える処理を実行
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(1000)
                                        firstCard?.setImageResource(R.drawable.background_image)
                                        secondCard?.setImageResource(R.drawable.background_image)
                                        thirdCard?.setImageResource(R.drawable.background_image)
                                        // カードの情報をリセット
                                        firstCard!!.isEnabled = true
                                        secondCard!!.isEnabled = true
                                        thirdCard!!.isEnabled = true
                                        firstCard = null
                                        secondCard = null
                                        thirdCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0
                                        thirdCardIndex = 0
                                    }
                                } else if (8 < firstCardIndex && firstCardIndex <= 11
                                    && 8 < secondCardIndex && secondCardIndex <= 11
                                    && 8 < thirdCardIndex && thirdCardIndex <= 11
                                ) {
                                    mediaPlayer1.start()
                                    firstCard?.visibility = View.INVISIBLE
                                    secondCard?.visibility = View.INVISIBLE
                                    thirdCard?.visibility = View.INVISIBLE
                                    // カードの情報をリセット

                                    firstCard = null
                                    secondCard = null
                                    thirdCard = null
                                    firstCardIndex = 0
                                    secondCardIndex = 0
                                    thirdCardIndex = 0
                                } else if (8 < firstCardIndex && firstCardIndex <= 11
                                    && 8 < secondCardIndex && secondCardIndex <= 11
                                    && !(8 < thirdCardIndex && thirdCardIndex <= 11)
                                ) {
                                    mediaPlayer2.start()
                                    // Coroutineを使用して2秒後に背景画像に置き換える処理を実行
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(1000)
                                        firstCard?.setImageResource(R.drawable.background_image)
                                        secondCard?.setImageResource(R.drawable.background_image)
                                        thirdCard?.setImageResource(R.drawable.background_image)
                                        // カードの情報をリセット
                                        firstCard!!.isEnabled = true
                                        secondCard!!.isEnabled = true
                                        thirdCard!!.isEnabled = true
                                        firstCard = null
                                        secondCard = null
                                        thirdCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0
                                        thirdCardIndex = 0
                                    }
                                } else if (11 < firstCardIndex && firstCardIndex <= 15
                                    && 11 < secondCardIndex && secondCardIndex <= 15
                                    && !(11 < thirdCardIndex && thirdCardIndex <= 15)
                                ) {
                                    mediaPlayer2.start()
                                    // Coroutineを使用して2秒後に背景画像に置き換える処理を実行
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(1000)
                                        firstCard?.setImageResource(R.drawable.background_image)
                                        secondCard?.setImageResource(R.drawable.background_image)
                                        thirdCard?.setImageResource(R.drawable.background_image)
                                        // カードの情報をリセット
                                        firstCard!!.isEnabled = true
                                        secondCard!!.isEnabled = true
                                        thirdCard!!.isEnabled = true
                                        firstCard = null
                                        secondCard = null
                                        thirdCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0
                                        thirdCardIndex = 0
                                    }
                                }

                            } else if (fourthCard == null) {
                                // 3枚目のカード
                                fourthCard = imageButton
                                fourthCardIndex = drawableArray.indexOfFirst { it == element }

                                if (11 < fourthCardIndex && fourthCardIndex <= 15) {

                                    mediaPlayer1.start()
                                    firstCard?.visibility = View.INVISIBLE
                                    secondCard?.visibility = View.INVISIBLE
                                    thirdCard?.visibility = View.INVISIBLE
                                    fourthCard?.visibility = View.INVISIBLE
                                    // カードの情報をリセット
                                    firstCard = null
                                    secondCard = null
                                    thirdCard = null
                                    fourthCard = null
                                    firstCardIndex = 0
                                    secondCardIndex = 0
                                    thirdCardIndex = 0
                                    fourthCardIndex = 0
                                } else {
                                    mediaPlayer2.start()
                                    // Coroutineを使用して2秒後に背景画像に置き換える処理を実行
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(1000)
                                        firstCard?.setImageResource(R.drawable.background_image)
                                        secondCard?.setImageResource(R.drawable.background_image)
                                        thirdCard?.setImageResource(R.drawable.background_image)
                                        fourthCard?.setImageResource(R.drawable.background_image)
                                        // カードの情報をリセット
                                        firstCard!!.isEnabled = true
                                        secondCard!!.isEnabled = true
                                        thirdCard!!.isEnabled = true
                                        fourthCard!!.isEnabled = true
                                        firstCard = null
                                        secondCard = null
                                        thirdCard = null
                                        fourthCard = null
                                        firstCardIndex = 0
                                        secondCardIndex = 0
                                        thirdCardIndex = 0
                                        fourthCardIndex = 0
                                    }
                                }
                            }
                        }
        }
    }
}
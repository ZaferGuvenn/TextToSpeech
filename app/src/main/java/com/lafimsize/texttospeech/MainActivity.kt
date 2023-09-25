package com.lafimsize.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lafimsize.texttospeech.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textToSpeech= TextToSpeech(this,ttsListener)

        binding.tvSpeak.setOnClickListener {
            speakNow()
        }
    }

    private var ttsListener= TextToSpeech.OnInitListener {status ->

        if (status==TextToSpeech.SUCCESS){
            val lang=textToSpeech.setLanguage(Locale.US)

            if (lang==TextToSpeech.LANG_MISSING_DATA||lang==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language not supported.")
                println("hata mising data")
            }
        }else {
            Log.e("TTS", "Initialization failed.")
            println("init failed")
        }
    }

    private fun speakNow(){
        textToSpeech.speak(binding.tvSpeak.text,TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()

        super.onDestroy()
    }
}
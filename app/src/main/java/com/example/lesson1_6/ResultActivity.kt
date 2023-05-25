package com.example.lesson1_6

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lesson1_6.MainActivity.Companion.KEY_FOR_TEXT
import com.example.lesson1_6.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etEnterText.setText(intent.getStringExtra(KEY_FOR_TEXT))
        initListener()
    }

    private fun initListener() {
        with(binding) {
            btnPrevious.setOnClickListener {
                if (etEnterText.text?.isNotEmpty() == true) {
                    val intent = Intent(this@ResultActivity, MainActivity::class.java)
                    intent.putExtra(KEY_FOR_TEXT, etEnterText.text.toString())
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else {
                    Toast.makeText(
                        this@ResultActivity,
                        "Поле не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
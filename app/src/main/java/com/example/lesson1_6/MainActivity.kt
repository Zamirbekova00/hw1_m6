package com.example.lesson1_6

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lesson1_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val secondLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data: Intent? = it.data
                val text = data?.getStringExtra(KEY_TEXT)
                binding.etEnterText.setText(text)
            }
        }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        with(binding) {
            btnNext.setOnClickListener {
                if (etEnterText.text?.isNotEmpty() == true) {
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra(KEY_FOR_TEXT, etEnterText.text.toString())
                    secondLauncher.launch(intent)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Поле не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        const val KEY_FOR_TEXT = "text"
        const val KEY_TEXT = "text"
    }
}

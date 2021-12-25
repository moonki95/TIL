package com.autoever.kot_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autoever.kot_study.databinding.ActivityMainBinding
import android.util.Log
import android.widget.CompoundButton
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    val listener by lazy {
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    R.id.checkBox4 -> Log.d("CheckBox", "1번선택")
                    R.id.checkBox5 -> Log.d("CheckBox", "2번선택")
                    R.id.checkBox6 -> Log.d("CheckBox", "3번선택")
                }
            } else {
                when (buttonView.id) {
                    R.id.checkBox4 -> Log.d("CheckBox", "1번해제")
                    R.id.checkBox5 -> Log.d("CheckBox", "2번해제")
                    R.id.checkBox6 -> Log.d("CheckBox", "3번해제")
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.checkBox4.setOnCheckedChangeListener(listener)
        binding.checkBox5.setOnCheckedChangeListener(listener)
        binding.checkBox6.setOnCheckedChangeListener(listener)

    }
}
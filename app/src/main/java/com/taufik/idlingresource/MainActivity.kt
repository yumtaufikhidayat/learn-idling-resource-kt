package com.taufik.idlingresource

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.taufik.idlingresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButton()
    }

    private fun setButton() {
        binding.apply {
            button.setOnClickListener {
                delay1()
                delay2()
            }
        }
    }

    private fun delay1() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.apply {
                textView.text = getString(R.string.delay1)
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        }, 2000)
    }

    private fun delay2() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.apply {
                textView.text = getString(R.string.delay2)
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        }, 2000)
    }
}
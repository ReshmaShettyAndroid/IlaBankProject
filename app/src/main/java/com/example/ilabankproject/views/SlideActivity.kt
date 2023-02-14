package com.example.ilabankproject.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ilabankproject.databinding.ActivityMainBinding

class SlideActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}
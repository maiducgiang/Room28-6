package com.example.room28_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room28_6.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var responsitory: EmailResponsitory
    private lateinit var adapter : EmailAdapter
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = EmailAdapter(this)
        binding.revListMail.adapter = adapter
        binding.revListMail.layoutManager = LinearLayoutManager(applicationContext)
        responsitory = EmailResponsitory(application)
        responsitory.getAllEmail().observe(this){
            if (it != null){
                for (email in it){
                    adapter.arrEmail.add(email)
                }
                adapter.notifyDataSetChanged()
            }
        }

    }

}

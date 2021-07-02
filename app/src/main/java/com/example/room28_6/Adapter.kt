package com.example.room28_6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import com.example.room28_6.dao.EmailDao
import com.example.room28_6.databinding.ItemBinding
import com.example.room28_6.entity.Email
import java.time.chrono.HijrahChronology.INSTANCE

class EmailAdapter(var lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {
    val arrEmail = mutableListOf(Email("snkz","lmao", "nothing"))

    class ViewHolder (val binding: ItemBinding, val lifecycleOwner: LifecycleOwner)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(email : Email){
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: EmailAdapter.ViewHolder, position: Int) {
        val email = arrEmail[position]
        holder.binding.textItemSender.text = "Sender: " +email.sender
        holder.binding.textItemTitle.text = "Title: " +email.title
        holder.binding.textItemContent.text = "Content: " +email.content
    }

    override fun getItemCount(): Int {
        if (arrEmail != null){
            return arrEmail.size
        }
        return  0
    }
}
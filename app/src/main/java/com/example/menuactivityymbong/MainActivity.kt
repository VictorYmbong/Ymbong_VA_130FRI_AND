package com.example.menuapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.menuactivityymbong.FragmentActivity
import com.example.menuactivityymbong.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.burgerIcon.setOnClickListener {
            toggleMenu()
        }

        binding.fragmentButton.setOnClickListener {
            // Intent to start another activity
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

        binding.dialogButton.setOnClickListener {
            showDialog()
        }

        binding.exitButton.setOnClickListener {
            finish() // Exit the app
        }
    }

    private fun toggleMenu() {
        binding.menuOptions.visibility = if (binding.menuOptions.visibility == View.GONE) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("This is a dialogue with positive and negative buttons.")
            .setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
            .setNegativeButton("Cancel") { dialog, id -> dialog.dismiss() }

        val alert = builder.create()
        alert.show()
    }
}
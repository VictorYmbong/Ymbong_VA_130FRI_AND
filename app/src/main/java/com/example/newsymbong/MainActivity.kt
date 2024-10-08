package com.example.newsymbong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsymbong.fragments.HeadlineFragment
import com.example.newsymbong.fragments.NewsContentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the headline list fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HeadlineFragment()) // Corrected to use HeadlineFragment
                .commit()
        }
    }

    // Function to switch to the news content fragment
    fun showNewsContent(newsTitle: String, newsContent: String) {
        val newsContentFragment = NewsContentFragment.newInstance(newsTitle, newsContent)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newsContentFragment)
            .addToBackStack(null)
            .commit()
    }
}

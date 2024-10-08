package com.example.newsymbong.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsymbong.R

class NewsContentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_content, container, false)

        val newsTitle: String? = arguments?.getString("title")
        val newsContent: String? = arguments?.getString("content")

        val titleTextView: TextView = view.findViewById(R.id.news_title)
        val contentTextView: TextView = view.findViewById(R.id.news_content)

        titleTextView.text = newsTitle ?: "No Title"
        contentTextView.text = newsContent ?: "No Content"

        return view
    }

    companion object {
        fun newInstance(title: String, content: String): NewsContentFragment {
            val fragment = NewsContentFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("content", content)
            fragment.arguments = args
            return fragment
        }
    }
}

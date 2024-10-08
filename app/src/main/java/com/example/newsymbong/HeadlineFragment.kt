package com.example.newsymbong.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.newsymbong.MainActivity
import com.example.newsymbong.R

class HeadlineFragment : Fragment() {

    private val headlines = arrayOf(
        "Breaking News: Market Crash",
        "New Technology in AI",
        "Sports Update: Local Team Wins",
        "Weather Forecast: Sunny Days Ahead"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headline, container, false) // Corrected to match your XML file name
        val listView: ListView = view.findViewById(R.id.list_view)

        // Create an adapter to display the headlines
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, headlines)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            // Handle item clicks
            val selectedHeadline = headlines[position]
            val newsContent = "Detailed content about: $selectedHeadline"
            (activity as MainActivity).showNewsContent(selectedHeadline, newsContent)
        }

        return view
    }
}

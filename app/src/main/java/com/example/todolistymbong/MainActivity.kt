package com.example.todolistymbong

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskListView: ListView
    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskListView = findViewById(R.id.listViewTasks)
        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)

        adapter = ArrayAdapter(this, R.layout.task_item, R.id.textViewTask, tasks)
        taskListView.adapter = adapter

        buttonAddTask.setOnClickListener {
            addTask()
        }

        taskListView.setOnItemClickListener { _, view, position, _ ->
            if (view is View) {
                val checkBox = view.findViewById<CheckBox>(R.id.checkBoxTask)
                checkBox.isChecked = !checkBox.isChecked
            }
        }

        taskListView.setOnItemLongClickListener { _, view, position, _ ->
            showEditDeleteDialog(position)
            true
        }
    }

    private fun addTask() {
        val taskName = editTextTask.text.toString().trim()
        if (taskName.isNotEmpty()) {
            tasks.add(taskName)
            adapter.notifyDataSetChanged()
            editTextTask.text.clear()
        }
    }

    private fun showEditDeleteDialog(position: Int) {
        val options = arrayOf("Edit", "Delete")
        AlertDialog.Builder(this)
            .setTitle("Choose an action")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> editTask(position)
                    1 -> deleteTask(position)
                }
            }
            .show()
    }

    private fun editTask(position: Int) {
        val currentTask = tasks[position]
        val editText = EditText(this).apply { setText(currentTask) }

        AlertDialog.Builder(this)
            .setTitle("Edit Task")
            .setView(editText)
            .setPositiveButton("Update") { _, _ ->
                val updatedTask = editText.text.toString().trim()
                if (updatedTask.isNotEmpty()) {
                    tasks[position] = updatedTask
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteTask(position: Int) {
        tasks.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}
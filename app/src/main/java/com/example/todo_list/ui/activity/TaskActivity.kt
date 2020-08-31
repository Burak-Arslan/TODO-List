package com.example.todo_list.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_list.R
import com.example.todo_list.db.TaskRepository
import com.example.todo_list.model.Task
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(taskToolbar)
        supportActionBar?.title = "ADD - TODO"

        init()
        events()
    }

    private fun events() {
        fabAdd.setOnClickListener {
            if (!TextUtils.isEmpty(edtTaskName.text.toString())) {
                val rowID = taskRepository.insertTask(
                    Task(
                        taskName = edtTaskName.text.toString(),
                        date = "17.08.1994"
                    )
                )

                if (rowID > -1) {
                    Toast.makeText(this, "EKLENDİ", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "EKLENMEDİ", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "TASK ADI BOŞ GEÇİLEMEZ", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun init() {
        taskRepository = TaskRepository(this)
    }
}
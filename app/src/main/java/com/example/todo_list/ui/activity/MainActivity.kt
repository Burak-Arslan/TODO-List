package com.example.todo_list.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.R
import com.example.todo_list.db.TaskRepository
import com.example.todo_list.model.Task
import com.example.todo_list.model.TaskAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskAdapter.onTaskCompleteClickListener {

    private lateinit var taskRepository: TaskRepository
    private lateinit var taskList: ArrayList<Task>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        supportActionBar?.title = "TODO - List"

        init()
        fillTaskRecylerview()
        events()

    }

    private fun events() {
        btnAddTask.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }
    }

    private fun fillTaskRecylerview() {
        taskList = taskRepository.getAllTask()
        recTask.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(this, taskList)
        recTask.adapter = adapter
        recTask.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun init() {
        taskRepository = TaskRepository(this)


    }


    override fun onResume() {
        super.onResume()
        taskList = taskRepository.getAllTask()
        adapter.updateList(taskList)
    }

    override fun onTaskComplete(taskID: Int) {
        taskRepository.deleteTask(taskID)
        adapter.updateList(taskList)
    }
}
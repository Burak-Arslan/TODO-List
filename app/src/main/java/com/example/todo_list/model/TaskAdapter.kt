package com.example.todo_list.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R

class TaskAdapter(var context: Context, var taskList: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var onTaskCompleteListener: onTaskCompleteClickListener

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName = view.findViewById<TextView>(R.id.txtTaskName)
        val date = view.findViewById<TextView>(R.id.textView)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.taskName.text = taskList[position].taskName
        holder.taskName.text = taskList[position].date

        holder.checkBox.setOnClickListener {
            onTaskCompleteListener.let {
                it.onTaskComplete(taskList[position].id)
            }
        }
    }

    fun setOnTaskCompleteListener(onTaskCompleteClickListener: onTaskCompleteClickListener){
        this.onTaskCompleteListener = onTaskCompleteListener
    }

    fun updateList(newList: ArrayList<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

    interface onTaskCompleteClickListener {
        fun onTaskComplete(taskID: Int)
    }

    override fun getItemCount(): Int = taskList.size
}
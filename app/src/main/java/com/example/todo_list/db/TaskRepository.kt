package com.example.todo_list.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.todo_list.model.Task

class TaskRepository(var context: Context) {

    private var mDBHelper: DBHelper = DBHelper.getInstance(context)

    fun getAllTask(): ArrayList<Task> {

        val list = ArrayList<Task>()
        val db = mDBHelper.readableDatabase

        val query =
            "SELECT ${DBHelper.KEY_ID},${DBHelper.KEY_NAME},${DBHelper.KEY_DATE} FROM ${DBHelper.TABLE_NAME}"

        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID))
                    val taskName = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                    val taskDate = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DATE))
                    val task = Task(id, taskName, taskDate)

                    list.add(task)

                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()

        return list
    }

    fun insertTask(task: Task): Int {
        val db = mDBHelper.writableDatabase

        val values = ContentValues()
        values.apply {
            put(DBHelper.KEY_NAME, task.taskName)
            put(DBHelper.KEY_DATE, task.date)
        }

        val id = db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()

        return id.toInt()
    }

    fun deleteTask(taskID: Int) {
        val db = mDBHelper.writableDatabase
        db.delete(DBHelper.TABLE_NAME, DBHelper.KEY_ID + "= ?", arrayOf(taskID.toString()))
        db.close()
    }

    fun updateTask() {

    }
}
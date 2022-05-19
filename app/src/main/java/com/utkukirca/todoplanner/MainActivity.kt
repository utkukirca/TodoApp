package com.utkukirca.todoplanner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkukirca.todoplanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var todoList : ArrayList<Todo>
    private lateinit var todoAdapter : TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        todoList = ArrayList<Todo>()
        todoAdapter = TodoAdapter(todoList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = todoAdapter


        try {

            val database = this.openOrCreateDatabase("Todos", Context.MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM todos",null)
            val todoNameIx = cursor.getColumnIndex("todoname")
            val todoDescIx = cursor.getColumnIndex("tododesc")

            while (cursor.moveToNext()) {
                val name = cursor.getString(todoNameIx)
                val description = cursor.getString(todoDescIx)
                val todo = Todo(name,description)
                todoList.add(todo)
            }


            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    fun newTodo( view : View){

        val intent = Intent(this,NewTodo::class.java)
        startActivity(intent)
    }

}
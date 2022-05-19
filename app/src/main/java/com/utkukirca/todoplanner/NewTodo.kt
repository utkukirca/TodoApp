package com.utkukirca.todoplanner

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.utkukirca.todoplanner.databinding.ActivityNewTodoBinding
import java.lang.Exception

class NewTodo : AppCompatActivity() {

    private lateinit var binding: ActivityNewTodoBinding
    private lateinit var database : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = this.openOrCreateDatabase("Todos", Context.MODE_PRIVATE,null)


    }


    fun cancel (view : View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


    fun saveTodo ( view : View) {
        val todoName = binding.todoNameText.text.toString()
        val todoDesc = binding.todoDescText.text.toString()

        try
        {
           database.execSQL("CREATE TABLE IF NOT EXISTS todos ( id INTEGER PRIMARY KEY, todoname VARCHAR, tododesc VARCHAR)")

            val sqlString = "INSERT INTO todos (todoname,tododesc) VALUES (?,?)"
            val statement = database.compileStatement(sqlString)

            statement.bindString(1, todoName)
            statement.bindString(2, todoDesc)

            statement.execute()

        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
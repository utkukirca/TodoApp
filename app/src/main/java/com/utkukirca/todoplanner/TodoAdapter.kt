package com.utkukirca.todoplanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utkukirca.todoplanner.databinding.TodoCardBinding

class TodoAdapter(private var todoList : ArrayList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoCard>() {

    class TodoCard(val todoCardBinding : TodoCardBinding): RecyclerView.ViewHolder(todoCardBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCard {
        val layoutInflater = LayoutInflater.from(parent.context)
        val todoCardBinding = TodoCardBinding.inflate(layoutInflater,parent,false)
        return TodoCard(todoCardBinding)
    }

    override fun onBindViewHolder(holder: TodoCard, position: Int) {
        val todo = todoList[position]

        holder.todoCardBinding.todoNameText.text = todo.name
        holder.todoCardBinding.todoDescText.text = todo.description
    }

    override fun getItemCount(): Int {
      return  todoList.size
    }
}
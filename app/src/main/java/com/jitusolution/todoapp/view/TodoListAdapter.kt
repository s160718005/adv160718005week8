package com.jitusolution.todoapp.view

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jitusolution.todoapp.R
import com.jitusolution.todoapp.databinding.TodoItemLayoutBinding
import com.jitusolution.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Any)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(), TodoCheckedChangeListener,TodoEditClickListener {
    //sebelumnya menggunakan view:View dan belakangnya view.root(pengganti view)
    class TodoListViewHolder(var view: TodoItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout,parent,false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(
            inflater,
            R.layout.todo_item_layout,
            parent,
            false
        )
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
//cara data binding langsung akses todoo yang terdapat pada todo_item_layout
        holder.view.todo = todoList[position]
        //listener harus di set dulu set this karena mengacu pada dirinya sendiri
        holder.view.listener=this
        holder.view.editlistener=this
        //        holder.view.checkTask.text= todoList[position].title +" "+ todoList[position].priority
//        //var uuid=todoList[position].uuid
//        holder.view.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        compound button itu bawaan android
//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            //b itu boolean
//            if(isChecked) {
//                //jika ya di delete jika tidak do nothing
//                adapterOnClick(todoList[position])
//            }
//        }
        //kita ganti jadi menggunakan data binding
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked) {
//                //jika ya di delete jika tidak do nothing
            adapterOnClick(obj)
//            }
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}
package com.jitusolution.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.jitusolution.todoapp.model.Todo
import com.jitusolution.todoapp.model.TodoDatabase
import com.jitusolution.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()

    private var job = Job()

    fun refresh ()
    {
        launch{
            //apapun yang di dalam launch ini dilakukand dalam thread terpisah tapi masih punya akses dalam ui
           // val db = Room.databaseBuilder(getApplication(),
            //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            todoLD.value= db.todoDao().selectAllTodoIsDone()

        }
    }

    fun clearTask(todo:Todo){
        // untuk mendelete task yang mau kita delete
        launch {
            //val db = Room.databaseBuilder(getApplication(),
                //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.todoDao().deleteTodo(todo)
            todoLD.value= db.todoDao().selectAllTodo()
            //delete lalu select all kembali
        }
    }
    fun clearTask1(todo:Todo){
        // untuk mendelete task yang mau kita delete
        launch {
            //val db = Room.databaseBuilder(getApplication(),
            //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.todoDao().updateIsDone(todo.uuid)
            todoLD.value= db.todoDao().selectAllTodoIsDone()
            //delete lalu select all kembali
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}
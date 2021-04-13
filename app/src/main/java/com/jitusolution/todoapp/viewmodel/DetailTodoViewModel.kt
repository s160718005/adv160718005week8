package com.jitusolution.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.jitusolution.todoapp.model.Todo
import com.jitusolution.todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {

    private val job= Job()

    fun addTodo(todo: Todo)
    {
        //ini single todoo
        launch{
            //apapun yang di dalam launch ini dilakukand dalam thread terpisah tapi masih punya akses dalam ui
            val db = Room.databaseBuilder(getApplication(),
                TodoDatabase::class.java, "tododb").build()
                db.todoDao().insertAll(todo)

        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}
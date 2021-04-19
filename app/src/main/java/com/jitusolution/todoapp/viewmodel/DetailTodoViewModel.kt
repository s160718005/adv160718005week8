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

class DetailTodoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {

    private val job= Job()
    val todoLD = MutableLiveData<Todo>()
    fun fetch(uuid:Int){
        launch{
            val db = buildDB(getApplication())
            todoLD.value=db.todoDao().selectTodo(uuid)
        }
    }
    fun addTodo(todo: Todo)
    {
        //ini single todoo
        launch{
            //apapun yang di dalam launch ini dilakukand dalam thread terpisah tapi masih punya akses dalam ui
            //val db = Room.databaseBuilder(getApplication(),
                //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
                db.todoDao().insertAll(todo)

        }
    }
    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}
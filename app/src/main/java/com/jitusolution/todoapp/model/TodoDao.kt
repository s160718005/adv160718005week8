package com.jitusolution.todoapp.model

import androidx.room.*
import java.util.*

@Dao
interface TodoDao {
    //tuliskan operasi apa saja yang dibutuhkan untuk table todoo
    //kalau cuma single ga usah varargs tapi di project ini kita buat supaya bisa banyak todoo 1 2 3 4 dan seterusnya parameter nya bisa lebih dari 1
    //kalau primary key carash yang lama di ganti dengan yang baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)
    //suspend ini ada kaitannya dengna corotine aitu bisa di pause dan continue
    @Query("Select * from todo ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>
    //@Query("select * from todoo where uuid= :id AND kolumb = :c")
    @Query("select * from todo where uuid= :id")
    suspend fun selectTodo(id:Int):Todo
    //untuk update
    @Query("update todo set title=:title, notes=:notes, priority=:priority where uuid=:uuid")
    suspend fun update(title:String,notes:String,priority:Int,uuid: Int)
    @Query("Select * from todo where is_done=0 ORDER BY priority DESC")
    suspend fun selectAllTodoIsDone():List<Todo>
    @Query("update todo set is_done=1 where uuid= :uuid")
    suspend fun updateIsDone(uuid: Int)


    @Delete
    suspend fun deleteTodo(todo:Todo)

}
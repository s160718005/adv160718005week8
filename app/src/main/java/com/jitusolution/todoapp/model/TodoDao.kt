package com.jitusolution.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    //tuliskan operasi apa saja yang dibutuhkan untuk table todoo
    //kalau cuma single ga usah varargs tapi di project ini kita buat supaya bisa banyak todoo 1 2 3 4 dan seterusnya parameter nya bisa lebih dari 1
    //kalau primary key carash yang lama di ganti dengan yang baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)
    //suspend ini ada kaitannya dengna corotine aitu bisa di pause dan continue
    @Query("Select * from todo")
    suspend fun selectAllTodo():List<Todo>
    //@Query("select * from todoo where uuid= :id AND kolumb = :c")
    @Query("select * from todo where uuid= :id")
    suspend fun selectTodo(id:Int):Todo

    @Delete
    suspend fun deleteTodo(todo:Todo)

}
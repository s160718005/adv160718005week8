package com.jitusolution.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//@Entity(tableName="todo_table")
@Entity
data class Todo(
    @ColumnInfo(name="title")
    //ini untuk ganti nama dari tabel database
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
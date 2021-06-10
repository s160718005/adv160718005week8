package com.jitusolution.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jitusolution.todoapp.util.MIGRATION_1_2
import com.jitusolution.todoapp.util.MIGRATION_2_3

@Database(entities = arrayOf(Todo::class), version = 4)
abstract class TodoDatabase:RoomDatabase() {
    //isinya semua dao yang kalian punya ini kebutulan cuma 1
    abstract fun todoDao():TodoDao
    companion object{
        @Volatile private var instance: TodoDatabase ?= null
        private  val LOCK = Any()
        //lock ini di sinkronisasi dengan synchornized
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "tododb"
        )
                //bisa ditambahkan koma koma kalau migrationnya banyak
            .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
            .build()
        //kalau ngga null dijalankan
        //kalau instance null build database
        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }

        }


    }
}
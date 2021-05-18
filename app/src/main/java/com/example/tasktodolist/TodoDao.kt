package com.example.tasktodolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao{
    @Insert
    suspend fun insertTask(todoModel: TodoModel)

    @Query("SELECT * FROM  TODOMODEL WHERE id!=1")
    fun getTask():LiveData<List<TodoModel>>

    //the task that are not completed have the id 0
    //the tasks that are getting finished have the id 1
    @Query("UPDATE TodoModel SET isFinished=1 WHERE id=:uid")
    fun finishTask(uid:Long)

    @Query("Delete FROM TodoModel WHERE id=:uid")
    fun deleteTask(uid:Long)
}
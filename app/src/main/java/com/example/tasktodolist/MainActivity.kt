package com.example.tasktodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list= arrayListOf<TodoModel>()
    val adapter=TodoAdapter(list)

    val db by lazy{
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodos.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.adapter
        }

        fabNewTask.setOnClickListener {
            val i= Intent(baseContext,TaskActivity::class.java)
            startActivity(i)
        }

        toolbar.inflateMenu(R.menu.main_menu)

        db.TodoDao().getTask().observe(this, Observer {
            if(!it.isNullOrEmpty()){
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
            else{
                list.clear()
                adapter.notifyDataSetChanged()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
package com.example.android_sqlite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_subject.*

class MainActivity : AppCompatActivity() {

    lateinit var lists :ArrayList<Subject>
    lateinit var DB:SQHelper
    lateinit var data:Cursor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val go = findViewById<Button>(R.id.go_subject)

        val delete = findViewById<Button>(R.id.delete_btn)



            go.setOnClickListener {
            startActivity(Intent(this@MainActivity, add_subject::class.java))
        }

        lists = ArrayList<Subject>()
        DB = SQHelper(applicationContext)
        data = DB.data_geter

        val adapter = Adapter(applicationContext,lists)
        val recycler :RecyclerView = findViewById<RecyclerView>(R.id.list)


        showData()


        list.layoutManager = GridLayoutManager(applicationContext,2)
        list.adapter = adapter
    }




    fun showData(){
        if (data.count == 0){
            Toast.makeText(applicationContext, "There is no items", Toast.LENGTH_SHORT).show()
        }
        while (data.moveToNext()){
            lists.add(Subject(data.getString(0),
                            data.getString(1),
                            data.getString(2)
                ))
        }
    }

    override fun onStart() {
        super.onStart()
        showData()
    }
}


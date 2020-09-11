package com.example.android_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.strictmode.SqliteObjectLeakedViolation

class SQHelper(context: Context):SQLiteOpenHelper(context, DB_name, null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY AUTOINCREMENT, S_title TEXT, S_desc TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TB_name")
    }

    fun ADD_DATA(title_text:String, desc_text:String){
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(title, title_text)
        values.put(desc, desc_text)

        DB.insert(TB_name, null, values)

    }

    fun Delete_Data(id:String):Int{
        val DB :SQLiteDatabase = this.writableDatabase
        val item:Int = DB.delete(TB_name, "id=?" , arrayOf(id))
        return item
    }

    val data_geter: Cursor get(){
        val DB : SQLiteDatabase = this.writableDatabase
        var data:Cursor = DB.rawQuery("select * from "+ TB_name, null)
        return data
    }

    companion object {
        val DB_name = "subjects.db"
        val TB_name = "subject"
        val id = "ID"
        val title = "S_title"
        val desc = "S_desc"
}


}
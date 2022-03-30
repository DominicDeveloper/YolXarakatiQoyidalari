package com.dominic.ypx.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.net.toUri
import com.dominic.ypx.Target.Target

const val DB_NAME = "base"
const val DB_VERSION = 1

const val TABLE_NAME = "target"
const val TARGET_ID = "id"
const val TARGET_NAME = "name"
const val TARGET_INFO = "info"
const val TARGET_IMAGE = "image"
const val TARGET_TYPE = "type"
const val TARGET_IS_LIKED = "liked"

class MyDbHelper(context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION),MyService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE_NAME ($TARGET_ID integer not null primary key autoincrement unique, $TARGET_NAME text not null, $TARGET_INFO text not null, $TARGET_IMAGE text not null, $TARGET_TYPE text not null, $TARGET_IS_LIKED text not null);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
        
    }

    override fun addTarget(target: Target) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TARGET_NAME,target.target_name)
        contentValues.put(TARGET_INFO,target.target_info)
        contentValues.put(TARGET_IMAGE,target.target_image.toString())
        contentValues.put(TARGET_TYPE,target.target_type)
        contentValues.put(TARGET_IS_LIKED,target.target_isliked)
        db.insert(TABLE_NAME,null,contentValues)
        db.close()

    }

    override fun getAllTargets(): ArrayList<Target> {
        val targetList = ArrayList<Target>()
        val query = "select * from $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val target = Target(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3).toUri(),cursor.getString(4),cursor.getString(5))
                targetList.add(target)
            }while (cursor.moveToNext())
        }

        return targetList

    }

    override fun editTarget(target: Target): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TARGET_ID,target.id)
        contentValues.put(TARGET_NAME,target.target_name)
        contentValues.put(TARGET_INFO,target.target_info)
        contentValues.put(TARGET_IMAGE,target.target_image.toString())
        contentValues.put(TARGET_TYPE,target.target_type)
        contentValues.put(TARGET_IS_LIKED,target.target_isliked)

       return database.update(TABLE_NAME,contentValues,"$TARGET_ID = ?", arrayOf(target.id.toString()))
    }

    override fun deleteTarget(target: Target) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME,"$TARGET_ID = ?", arrayOf(target.id.toString()))
        database.close()

    }
}
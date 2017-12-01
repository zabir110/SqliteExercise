package com.example.user.sqliteexercise;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by User on 11/22/2017.
 */

public class StudentDbHelper extends SQLiteOpenHelper
{
    public static String dataBase_name="student.db";
    public static String id="ID";
    public static String name="Name";
    public static String marks="Marks";
    public static String TableName ="student";

    public StudentDbHelper(Context context) {
        super(context,dataBase_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a table 3 row elements named (name,id,marks)
        db.execSQL("Create table student "+"("+name+" text,"+id+" text,"+marks+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //If a table already exists then delete/drop it
        db.execSQL("drop table if exists "+dataBase_name);
        //then create for just call oncreate method
        onCreate(db);
    }
    public boolean insert (String fetch_name,String fetch_id,String fetch_marks)
    {
        //This to write data into table
        SQLiteDatabase db = getWritableDatabase();
        //to put into database we need Contentvalues
        ContentValues values=new ContentValues();

        values.put(id,fetch_id);
        values.put(name,fetch_name);
        values.put(marks,fetch_marks);
        // now insert into the database
        long check= db.insert("student",null,values);

        if (check==-1) return  false;
        else  return true;

    }

    public Cursor viewData()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor;
        cursor=db.rawQuery("SELECT * FROM student",null);
        return cursor;


    }
    public boolean UpdateData(String fetch_name,String fetch_id,String fetch_marks){
        //This to write data into table
        SQLiteDatabase db = getWritableDatabase();
        //to put into database we need Contentvalues
        ContentValues values=new ContentValues();

        values.put(id,fetch_id);
        values.put(name,fetch_name);
        values.put(marks,fetch_marks);

        db.update(TableName,values,"ID= ?",new String[]{fetch_id});
        return  true;
    }
    public Integer DeleteData (String id){
        SQLiteDatabase db = getWritableDatabase();
        return  db.delete(TableName,"ID = ?",new String[] {id});
    }
}

package com.example.kishan.pbd_project;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDateBase extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Context context;
    private static  String DATABASE_NAME="MYNEWDATABASE.db";
    private final String TABLE_NAME1="DONOR_INFORMATION";
    private final String TABLE_NAME2="LOGIN_INFORMATION";

    public MyDateBase(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME2+"(userName varchar(20) primary key ,password varchar(20) not null)");
        db.execSQL("create table "+TABLE_NAME1+"(fullName varchar(20) not null, bloodGroup varchar(10) not null, phoneNumber varchar(10) primary key, address varchar(40) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+TABLE_NAME1);
        db.execSQL("drop table "+TABLE_NAME2);
        onCreate(db);
    }


    //For Table 1
    public void onInsert_Table1(String name, String bgroup, String phone, String add)
    {
        try{
            db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("fullName",name);
            contentValues.put("bloodGroup",bgroup);
            contentValues.put("phoneNumber",phone);
            contentValues.put("address",add);
            long c=db.insert(TABLE_NAME1,null,contentValues);
            if(c==-1)
                Toast.makeText(context,"No data is inserted !!!",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Data is inserted successfully !!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public String onSearch_Table1(String bgroup)
    {
        try{
            db=this.getReadableDatabase();
            int count=1;
            String result="";
            Cursor cursor;
            cursor=db.rawQuery("select * from "+TABLE_NAME1+" where bloodGroup =?",new String[]{bgroup});
            while(cursor.moveToNext())
            {
                result=result+String.valueOf(count)+". "+cursor.getString(0)+"  "+cursor.getString(2)+"  "+cursor.getString(3)+" \n";
                count+=1;
            }
            cursor.close();
            if(result.equals(""))
                return("No data is found !!!");
            else
                return result;
        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }


    //For Table 2
    public Boolean onInsert_Table2(String uname,String pass)
    {
        try{
            db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("userName",uname);
            contentValues.put("password",pass);
            long c=db.insert(TABLE_NAME2,null,contentValues);
            if(c==-1)
            {
                Toast.makeText(context,"No record is inserted !!!",Toast.LENGTH_SHORT).show();
                return false;
            }
            else
            {
                Toast.makeText(context,"Your account is created !!!",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public  Boolean onCheck_Table2(String uname,String passwrd)
    {
        try{
            db=this.getReadableDatabase();
            String nam,pas;
            Cursor cursor;
            cursor=db.rawQuery(" select * from "+TABLE_NAME2+" where userName=? and password=? ",new String[]{uname,passwrd});
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}

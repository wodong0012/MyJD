package com.ye.myjd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ye.myjd.cons.DbConstant;

/**
 * @author : WoDong
 * @date : 2020/3/16 21:35
 * @desc :
 */
public class UserDao {

    private final DbOpenHelper mDbHelper;

    public UserDao(Context context) {
        mDbHelper = new DbOpenHelper(context);
    }

    public UserInfo aquireUser(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConstant.TABLE_NAME, new String[]{DbConstant._NAME, DbConstant._PWD}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            String pwd = cursor.getString(1);
            return new UserInfo(name, pwd);
        }
        return null;
    }

    public boolean saveUser(String name, String pwd) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConstant._NAME, name);
        values.put(DbConstant._PWD, pwd);
        long insert = db.insert(DbConstant.TABLE_NAME, null, values);
        return insert != -1;
    }

    public void clearUser(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.delete(DbConstant.TABLE_NAME, null, null);
    }

    public class UserInfo{
        public String name;
        public String pwd;

        public UserInfo(String name, String pwd) {
            this.name = name;
            this.pwd = pwd;
        }

        public String getName() {
            return name;
        }

        public String getPwd() {
            return pwd;
        }
    }

}

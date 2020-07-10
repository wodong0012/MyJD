package com.ye.myjd.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ye.myjd.cons.DbConstant;

/**
 * @author : WoDong
 * @date : 2020/3/16 21:19
 * @desc :
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(@Nullable Context context) {
        super(context, DbConstant.DB_NAME , null, DbConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ DbConstant.TABLE_NAME +" ("+DbConstant._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DbConstant._NAME+" TEXT,"+DbConstant._PWD+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

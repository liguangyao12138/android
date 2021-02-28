package com.example.datastorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datastorage.bean.UserInfo;

import java.util.ArrayList;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";//数据库的名称
    private static final int DB_VERSION = 1;//数据库的版本号
    private static UserDBHelper mHelper = null;//数据库帮助器的实例
    private SQLiteDatabase mDB = null;//数据库的实例
    private static final String TABLE_NAME = "user_info";//表的名称


    private UserDBHelper(Context context){
        super(context , DB_NAME , null , DB_VERSION);
    }

    private UserDBHelper(Context context , int version){
        super(context , DB_NAME , null , version);
    }

    //利用单例模式获取数据库帮助器的唯一实例
    public static UserDBHelper getInstance(Context context , int version){
        if(version>0 && mHelper==null){
            mHelper = new UserDBHelper(context , version);
        }else if(mHelper == null){
            mHelper = new UserDBHelper(context);
        }

        return mHelper;
    }

    //打开数据库的读连接
    public SQLiteDatabase openReadLink(){
        if(mDB==null || !mDB.isOpen()){
            mDB = mHelper.getReadableDatabase();
        }
        return mDB;
    }

    //打开数据库的写连接
    public SQLiteDatabase openWriteLink(){
        if(mDB==null || !mDB.isOpen()){
            mDB = mHelper.getWritableDatabase();
        }
        return mDB;
    }

    //关闭数据库连接
    public void closeLink(){
        if(mDB!=null || mDB.isOpen()){
            mDB.close();
            mDB = null;
        }
    }

    //创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {

        String drop_sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(drop_sql);
        String create_sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
                + "_id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,"
                + "name VARCHAR NOT NULL," + "age INTEGER NOT NULL,"
                + "height LONG NOT NULL," + "weight FLOAT NOT NULL,"
                + "married INTEGER NOT NULL," + "update_time VARCHAR NOT NULL"
                + ",phone VARCHAR"+",password VARCHAR"+");";
        db.execSQL(create_sql);
    }



    //修改数据，执行表结构变更语句
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //根据指定条件删除表记录
    public int delete(String condition){
        return mDB.delete(TABLE_NAME , condition , null);
    }

    // 删除该表的所有记录
    public int deleteAll() {
        // 执行删除记录动作，该语句返回删除记录的数目
        return mDB.delete(TABLE_NAME, "1=1", null);
    }

    // 往该表添加一条记录
    public long insert(UserInfo info) {
        ArrayList<UserInfo> infoArray = new ArrayList<UserInfo>();
        infoArray.add(info);
        return insert(infoArray);
    }

    //往该表添加多条数据
    public long insert(ArrayList<UserInfo> infoArray){
        long result = -1;
        for (int i = 0; i < infoArray.size(); i++) {
            UserInfo info = infoArray.get(i);
            ArrayList<UserInfo> tempArray = new ArrayList<UserInfo>();

            if(info.phone!=null && info.phone.length()>0){
                String condition = String.format("phone='%s'" , info.phone);
                tempArray = query(condition);
                if (tempArray.size()>0){
                    update(info,condition);
                    result = tempArray.get(0).rowid;
                    continue;
                }
            }

            ContentValues cv = new ContentValues();
            cv.put("name" , info.name);
            cv.put("age" , info.age);
            cv.put("height" , info.height);
            cv.put("weight" , info.weight);
            cv.put("married" , info.married);
            cv.put("update_time" , info.update_time);
            cv.put("phone" , info.phone);
            cv.put("password" , info.password);
            result = mDB.insert(TABLE_NAME , "" , cv);
            if(result == -1){
                return result;
            }
        }

        return result;

    }

    //根据条件更新指定的表记录
    private int update(UserInfo info , String condition){
        ContentValues cv = new ContentValues();
        cv.put("name" , info.name);
        cv.put("age" , info.age);
        cv.put("height" , info.height);
        cv.put("weight" , info.weight);
        cv.put("married" , info.married);
        cv.put("update_time" , info.update_time);
        cv.put("phone" , info.phone);
        cv.put("password" , info.password);
        //执行更新记录动作，该语句返回记录更新的数目
        return mDB.update(TABLE_NAME , cv , condition , null);

    }

    //根据指定条件查询记录，并返回结果数据队列
    public ArrayList<UserInfo> query(String condition) {

        String sql = String.format("select rowid, _id, name, age, height, weight, married, update_time,"+"phone, password from %s where %s;", TABLE_NAME, condition );
        ArrayList<UserInfo> infoArray = new ArrayList<UserInfo>();

        Cursor cursor = mDB.rawQuery(sql,null);
        while (cursor.moveToNext()){
            UserInfo info = new UserInfo();
            info.rowid = cursor.getLong(0);
            info.xuhao = cursor.getInt(1);
            info.name = cursor.getString(2);
            info.age = cursor.getInt(3);
            info.height = cursor.getLong(4);
            info.weight = cursor.getFloat(5);
            info.married = (cursor.getInt(6)==0)?false:true;
            info.update_time = cursor.getString(7);
            info.phone = cursor.getString(8);
            info.password = cursor.getString(9);
            infoArray.add(info);
        }

        cursor.close();
        return infoArray;
    }

    //根据手机号查询指定记录
    public UserInfo queryByPhone(String phone){
        UserInfo info = null;
        ArrayList<UserInfo> infoArray = query(String.format("phone=%s",phone));
        if (infoArray.size()>0){
            info = infoArray.get(0);
        }
        return info;
    }
}

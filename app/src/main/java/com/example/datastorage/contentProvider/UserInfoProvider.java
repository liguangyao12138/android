package com.example.datastorage.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datastorage.database.UserDBHelper;

public class UserInfoProvider extends ContentProvider {

    private UserDBHelper userDB;
    public static final int USER_INFO = 1;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(UserInfoContent.AUTHORITIES , "/user" , USER_INFO);
    }


    //创建ContentProvider时调用，可在此获取具体的数据库帮助器实例
    @Override
    public boolean onCreate() {
        userDB = UserDBHelper.getInstance(getContext() , 1);
        return false;
    }

    //根据指定条件查询数据库
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        if(uriMatcher.match(uri) == USER_INFO){
            SQLiteDatabase db = userDB.getReadableDatabase();
            cursor = db.query(UserInfoContent.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);

            cursor.setNotificationUri(getContext().getContentResolver() , uri);
        }
        return cursor;
    }

    //获取Uri数据的访问类型，暂未实现
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    //插入数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Uri newUri = uri;

        if(uriMatcher.match(uri) == USER_INFO){
            //获取SQLite数据库的写链接
            SQLiteDatabase db = userDB.getWritableDatabase();

            //向指定的表插入数据，返回记录的行号
            long rowId = db.insert(UserInfoContent.TABLE_NAME , null , values);

            //判断插入是否执行成功
            if(rowId>0){

                //如果添加成功，利用新纪录的行号生成新的地址
                newUri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI , rowId);

                //通知监听器，数据已经改变
                getContext().getContentResolver().notifyChange(newUri , null);
            }

            db.close();
        }
        return uri;
    }

    //根据指定条件删除数据
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;
        if(uriMatcher.match(uri) == USER_INFO){

            //获取SQLite数据库的写链接
            SQLiteDatabase db = userDB.getWritableDatabase();

            //执行SQLite的删除操作，返回删除记录的数目
            count = db.delete(UserInfoContent.TABLE_NAME , selection , selectionArgs);

            //关闭SQLite数据库链接
            db.close();
        }
        return count;
    }

    //更新数据
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

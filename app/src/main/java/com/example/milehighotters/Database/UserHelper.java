package com.example.milehighotters.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.milehighotters.Database.UserSchema.UserTable;
import com.example.milehighotters.UserItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserHelper extends SQLiteOpenHelper {

    private static final String TAG = "USER";

    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "user.db";

    private SQLiteDatabase db;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + UserTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserTable.Cols.UUID + ","+
                UserTable.Cols.USERNAME + ","+
                UserTable.Cols.PASSWORD + ","+
                UserTable.Cols.DUE +
                ")"
        );

        // todo: preload ur shit mon niggeur
        // check if default values alice5
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public static ContentValues getContentValues(UserItem user) {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.UUID, user.getUserID().toString());
        cv.put(UserTable.Cols.USERNAME, user.getUsername());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.DUE, user.getDue());

        return cv;
    }

    private long insertUser(UserItem user){

        ContentValues cv = getContentValues(user);

        db = this.getWritableDatabase();

        return db.insert(UserTable.NAME, null, cv);

    }

    public long addUserItem(UserItem user) {
        if (this.getUserItem(user.getUserID()) == null) {
            return insertUser(user);
        } else {
            return updateUserItem(user);
        }
    }

    private UserItem getUserItem(UUID userUUID) {
        String whereClause = UserTable.Cols.UUID + " = ? +";
        String[] whereArgs = { userUUID.toString() };

        UserCursorWrapper cursor = new UserCursorWrapper(this.queryDB(UserTable.NAME, whereClause, whereArgs));

        try {
            if (cursor.getCount() == 0) {
                Log.d(TAG, "No results from getUserItem.");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUserItem();
        } finally {
            cursor.close();
        }
    }

    public int updateUserItem(UserItem user) {
        db = this.getWritableDatabase();
        ContentValues cv = UserHelper.getContentValues(user);

        String whereClause = UserTable.Cols.UUID + " = ? ";
        String[] whereArgs = {user.getUserID().toString()};

        try {
            return db.update(UserTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e) {
            Log.d(TAG, "Something is wrong in updateUser.");
            return -1;
        }
    }

    public List<UserItem> getUsers() {
        List<UserItem> users = new ArrayList<>();

        UserCursorWrapper cursor = new UserCursorWrapper(this.queryDB(UserTable.NAME, null, null));

        try {
            if (cursor.getCount() == 0) {
                Log.d(TAG, "No results from getUserItem.");
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUserItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return users;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try {
            return db.query(UserTable.NAME, null, whereClause, whereArgs, null, null, null);
        } catch (Exception e) {
            Log.d(TAG, "Problem in queryDB");
            return null;
        }
    }

}

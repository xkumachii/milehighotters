package com.example.milehighotters.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.milehighotters.UserItem;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //todo: implement userItem

    public UserItem getUserItem() {
        String uuidString = getString(getColumnIndex(UserSchema.UserTable.Cols.UUID));
        String username = getString(getColumnIndex(UserSchema.UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserSchema.UserTable.Cols.PASSWORD));
        double due = getDouble(getColumnIndex(UserSchema.UserTable.Cols.DUE));

        int sqlLogId = getInt(getColumnIndex("_id)"));

        UserItem user = new UserItem(UUID.fromString(uuidString));

        user.setUsername(username);
        user.setPassword(password);
        user.setDue(due);
        user.setSqlLogId(sqlLogId);

        return user;
    }

}

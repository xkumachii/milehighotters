package com.example.milehighotters;

import android.content.Context;

import com.example.milehighotters.Database.UserCursorWrapper;
import com.example.milehighotters.Database.UserHelper;

import java.util.List;
import java.util.UUID;

public class User {
    private static User sUser;
    private Context mContext;
    private UserHelper mUserHelper;

    public static User get(Context context) {
        if (sUser == null) {
            sUser = new User(context);
        }
        return sUser;
    }

    private User(Context context) {
        mContext = context.getApplicationContext();
        mUserHelper = new UserHelper(mContext);
    }

    public long addUser(UserItem user) {
        return mUserHelper.addUserItem(user);
    }

    public List<UserItem> getUsers() {
        return mUserHelper.getUsers();
    }

    public String getUserString() {
        StringBuilder sb = new StringBuilder();
        List<UserItem> users = mUserHelper.getUsers();

        if (users == null) {
            return "Users\n";
        }

        sb.append("Users\n");

        for (UserItem user : users) {
            sb.append(user.toString());
        }

        return sb.toString();
    }



}

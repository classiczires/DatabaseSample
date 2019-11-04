package com.zires.databasesample;

import android.content.Context;

import com.zires.databasesample.data.LocalUserDataSource;
import com.zires.databasesample.data.UserDataSource;
import com.zires.databasesample.data.UsersDatabase;
import com.zires.databasesample.view.ViewModelFactory;

/**
 * Created by ClassicZires on 11/4/2019.
 */
public class Injection {

    public static UserDataSource provideUserDataSource(Context context) {
        UsersDatabase database = UsersDatabase.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}

package com.zires.databasesample.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zires.databasesample.data.User;
import com.zires.databasesample.data.UserDataSource;

/**
 * Created by ClassicZires on 11/4/2019.
 */

/**
 * View Model for the {@link UserActivity}
 */
public class UserViewModel extends ViewModel {

    private final UserDataSource mDataSource;

    private User mUser;

    public UserViewModel(UserDataSource dataSource) {
        mDataSource = dataSource;
    }

    /**
     * Get the user name of the user.
     */
    public LiveData<User> getUserName() {
        return mDataSource.getUser();
    }

    /**
     * Update the user name.
     *
     * @param userName the new user name
     */
    public void updateUserName(final String userName) {
        // if there's no user, create a new user.
        // if we already have a user, then, since the user object is immutable,
        // create a new user, with the id of the previous user and the updated user name.
        mUser = mUser == null
                ? new User(userName)
                : new User(mUser.getId(), userName);

        mDataSource.insertOrUpdateUser(mUser);
    }
}

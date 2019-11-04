package com.zires.databasesample.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zires.databasesample.Injection;
import com.zires.databasesample.R;
import com.zires.databasesample.data.User;

public class UserActivity extends AppCompatActivity {
    private TextView mUserName;
    private EditText mUserNameInput;
    private Button mUpdateButton;
    private ViewModelFactory mViewModelFactory;
    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUserName = findViewById(R.id.user_name);
        mUserNameInput = findViewById(R.id.user_name_input);
        mUpdateButton = findViewById(R.id.update_user);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);


        mViewModel.getUserName().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null)
                    mUserName.setText(user.getUserName());
            }
        });

        mUpdateButton.setOnClickListener(v -> updateUserName());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void updateUserName() {
        String userName = mUserNameInput.getText().toString();


        new InsertDB(userName).execute();
    }


    class InsertDB extends AsyncTask<Void, Void, Void> {
        String userName;

        public InsertDB(String userName) {
            this.userName = userName;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserActivity.this.mViewModel.updateUserName(userName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }

    }
}

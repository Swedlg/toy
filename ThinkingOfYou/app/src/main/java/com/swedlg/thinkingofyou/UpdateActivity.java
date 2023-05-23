package com.swedlg.thinkingofyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.swedlg.thinkingofyou.Room.User;
import com.swedlg.thinkingofyou.Room.UserDao;
import com.swedlg.thinkingofyou.Room.UserDatabase;


public class UpdateActivity extends AppCompatActivity {

    EditText editText_login, editText_password;

    Button button_update;

    private User user;

    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        editText_login = findViewById(R.id.editText_login);
        editText_password = findViewById(R.id.editText_password);
        button_update = findViewById(R.id.button_update);

        user = (User) getIntent().getSerializableExtra("model");

        editText_login.setText(user.getLogin());
        editText_password.setText(user.getPassword());

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userModel = new User(user.getId(), editText_login.getText().toString(), editText_password.getText().toString());
                userDao.update(userModel);
                finish();
            }
        });
    }
}
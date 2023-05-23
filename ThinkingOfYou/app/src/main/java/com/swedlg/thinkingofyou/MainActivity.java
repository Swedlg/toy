package com.swedlg.thinkingofyou;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import com.swedlg.thinkingofyou.Room.User;
import com.swedlg.thinkingofyou.Room.UserDao;
import com.swedlg.thinkingofyou.Room.UserDatabase;
import com.swedlg.thinkingofyou.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {

    private ActivityMainBinding binding;

    EditText editText_login, editText_password;

    Button button_insert;

    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserAdapter userAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        editText_login = findViewById(R.id.editText_login);
        editText_password = findViewById(R.id.editText_password);
        button_insert = findViewById(R.id.button_insert);
        recyclerView = findViewById(R.id.recyclerview_users);

        userAdapter = new UserAdapter(this, this);

        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = editText_login.getText().toString();
                String password = editText_password.getText().toString();

                User user = new User(0, login, password);
                userAdapter.addUser(user);

                userDao.insert(user);

                editText_login.setText("");
                editText_password.setText("");

                Toast.makeText(MainActivity.this, "User Wad Added", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchData(){
        userAdapter.clearData();
        List<User> usersList = userDao.getAll();
        for (User user: usersList) {
            userAdapter.addUser(user);
        }
    }

    @Override
    public void onUpdate(User user) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", user);
        startActivity(intent);
    }

    @Override
    public void onDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUser(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
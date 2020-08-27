package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DB.PersonDA;
import Model.Person;

public class MainActivity extends AppCompatActivity {

    EditText password_edt;
    EditText username_edt;
    Button sing_in_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PersonDA personDA = new PersonDA(MainActivity.this);
//        personDA.openDB();
//        personDA.addNewData(new Person("mohammadmehdi khajehzadeh" , "@" , "09******" , "admin" , "admin"));
        initViews();
        viewsController();
    }

    private void initViews() {
        password_edt = findViewById(R.id.password_edt);
        username_edt = findViewById(R.id.username_edt);
        sing_in_btn = findViewById(R.id.sing_in_btn);
    }

    private void viewsController() {

        sing_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonDA personDA = new PersonDA(MainActivity.this);
                personDA.openDB();

                Person person = personDA.person(username_edt.getText().toString(), password_edt.getText().toString());
                if (person == null) {
                    Toast.makeText(getApplicationContext(), "username or password is wrong", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(MainActivity.this, InfoPage.class);
                    intent.putExtra("fullName", person.getFullName());
                    intent.putExtra("username", person.getUsername());
                    intent.putExtra("email", person.getEmail());
                    finish();
                    startActivity(intent);

                }

                personDA.closeDB();
            }
        });

    }

}
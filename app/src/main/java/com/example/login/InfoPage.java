package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Model.Person;

public class InfoPage extends AppCompatActivity {

    private int img_choose = 1;

    TextView fullName_Text;
    TextView username_Text;
    TextView email_Text;
    Button exit_BTN;
    ImageView user_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        initViews();
        ControlViews();
        setThePerson();
        setEditTextValues();

    }

    private void initViews() {
        fullName_Text = findViewById(R.id.fullName_Text);
        username_Text = findViewById(R.id.username_Text);
        email_Text = findViewById(R.id.email_Text);
        exit_BTN = findViewById(R.id.exit_BTN);
        user_img = findViewById(R.id.user_img);
    }

    private void ControlViews() {
        exit_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, img_choose);
            }
        });
    }

    private void setEditTextValues() {
        Person person = setThePerson();
        fullName_Text.setText(person.getFullName());
        String str = person.getUsername();
        username_Text.setText(str);
        email_Text.setText(person.getEmail());

    }

    private Person setThePerson() {
        Person person = new Person(getIntent().getStringExtra("fullName"), getIntent().getStringExtra("email"), getIntent().getStringExtra("username"));
        return person;
    }

    // to set the pic choosed in external storeg in the phone in the imageView part   ()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == img_choose && resultCode == RESULT_OK) { // use -1 instead of result_ok
            user_img.setImageURI(data.getData());
        }
    }
}
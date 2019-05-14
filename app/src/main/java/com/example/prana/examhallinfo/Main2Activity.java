package com.example.prana.examhallinfo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main2Activity extends AppCompatActivity {

    EditText email;
    EditText Password;
    Button login;
    String emaill;
    String passwordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.password);
       emaill = email.getText().toString();
       passwordd = Password.getText().toString();

        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputStream is;
                is= getResources().openRawResource(R.raw.adminlogin);

                //getResources().openRawResource(R.raw.examhallinfo);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String line = "";
                try {
                    reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        String[] tokens = line.split(",");
                        Email e = new Email();

                        e.setEmail(tokens[0]);
                        e.setPassword(tokens[1]);
                        if(emaill==tokens[0] && passwordd ==tokens[1])
                        {
                            Toast.makeText(Main2Activity.this,"Login Successful", Toast.LENGTH_LONG).show();
                            openMainActivity();
                        }
                        else
                            {
                                Toast.makeText(Main2Activity.this,"Invalid email or password", Toast.LENGTH_LONG).show();
                        }

                    }

                    } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        });


    }

private void openMainActivity()
{
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
}
}

package com.example.kishan.pbd_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signUp,logIn,register;
    EditText unam,pass;
    String username,password;

    MyDateBase myDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unam=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pass);

        myDateBase=new MyDateBase(getApplicationContext());

        onSignup();
        onLogin();
        onRegister();
    }

    private void onRegister() {
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }



    public void onSignup()
    {
        signUp=(Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup_2.class);
                startActivity(intent);
            }
        });
    }


    public void onLogin()
    {
        logIn=(Button)findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=unam.getText().toString();
                password=pass.getText().toString();
                if(username.equals("")||password.equals(""))
                {
                    if(username.equals(""))
                        unam.setError("Empty Area !");
                    if(password.equals(""))
                        pass.setError("Empty Area !");
                }
                else {
                    Boolean k = myDateBase.onCheck_Table2(username, password);
                    if (k == true) {
                        Intent intent = new Intent(MainActivity.this, loginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter valid Username and Password !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

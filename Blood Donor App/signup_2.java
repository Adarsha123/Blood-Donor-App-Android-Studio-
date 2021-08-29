package com.example.kishan.pbd_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup_2 extends AppCompatActivity {

    Button sbtm;
    EditText userName,pass_word,cpass_word;

    String name,pass,cpass;

    MyDateBase myDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_2);

        userName=(EditText)findViewById(R.id.name);
        pass_word=(EditText)findViewById(R.id.password);
        cpass_word=(EditText)findViewById(R.id.cpassword);

        myDateBase=new MyDateBase(getApplicationContext());

        Insert_Table2();
    }

    public void Insert_Table2()
    {
        sbtm=(Button)findViewById(R.id.submit_t2);
        sbtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=userName.getText().toString();
                pass=pass_word.getText().toString();
                cpass=cpass_word.getText().toString();
                if(name.equals("")||pass.equals("")||cpass.equals(""))
                {
                    if(name.equals(""))
                        userName.setError("Empty Area !");
                    if(pass.equals(""))
                        pass_word.setError("Empty Area !");
                    if(cpass.equals(""))
                        cpass_word.setError("Empty Area !");
                }
                else {
                    if (pass.equals(cpass)) {
                        if(myDateBase.onInsert_Table2(name, pass)){
                            Intent intent=new Intent(signup_2.this,MainActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        cpass_word.setError("Mis-match Password !");
                    }
                }
            }
        });
    }
}

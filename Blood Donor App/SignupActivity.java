package com.example.kishan.pbd_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText uname,bgroup,adds,phoneno;
    Button sbm_bt;
    String userName,bloodGroup,pass_word,addres,phoneNumber;

    MyDateBase myDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uname = (EditText) findViewById(R.id.name);
        bgroup = (EditText) findViewById(R.id.bloodgroup);
        adds = (EditText) findViewById(R.id.address);
        phoneno = (EditText) findViewById(R.id.phone);

        myDateBase = new MyDateBase(getApplicationContext());
        Insert_Table1();
    }

    public void Insert_Table1()
    {
        sbm_bt=(Button)findViewById(R.id.submit);
        sbm_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=uname.getText().toString();
                bloodGroup=bgroup.getText().toString();
                phoneNumber=phoneno.getText().toString();
                addres=adds.getText().toString();
                if(userName.equals("")||bloodGroup.equals("")||phoneNumber.equals("")||addres.equals(""))
                {
                    if(userName.equals(""))
                        uname.setError("Empty Area !");
                    if(bloodGroup.equals(""))
                        bgroup.setError("Empty Area !");
                    if(phoneNumber.equals(""))
                        phoneno.setError("Empty Area !");
                    if(addres.equals(""))
                        adds.setError("Empty Area !");
                }
                else {
                    myDateBase.onInsert_Table1(userName, bloodGroup, phoneNumber, addres);
                }
            }
        });

    }



}

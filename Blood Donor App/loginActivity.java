package com.example.kishan.pbd_project;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    EditText searchB;
    Button srh_bt;
    String bloodGroup;
    MyDateBase myDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        searchB=(EditText)findViewById(R.id.searchblood);
        myDateBase=new MyDateBase(getApplicationContext());

        Search();
    }

    public void Search()
    {
        srh_bt=(Button)findViewById(R.id.search);
        srh_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup=searchB.getText().toString();
                String k=myDateBase.onSearch_Table1(bloodGroup);
                AlertDialog.Builder builder=new AlertDialog.Builder(loginActivity.this);
                builder.setMessage(k);
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("INFORMATION :");
                alertDialog.show();
            }
        });
    }

}

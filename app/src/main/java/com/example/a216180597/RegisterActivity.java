package com.example.a216180597;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtusername , txtpassword;
    private Button login , register ;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        txtusername = (EditText)findViewById(R.id.username);
        txtpassword = (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        openHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                submit();
            }
        });
    }


    private void submit()
    {
        String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();

        if (TextUtils.isEmpty(username) | TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Field can't be Empty", Toast.LENGTH_SHORT).show();
        }else
        {
            Cursor c =  db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 + " = ? AND " + DatabaseHelper.COL_3 + " =? " ,new String[] { username,password});
            if ( c != null)
            {
                if (c.getCount() > 0){

                    Toast.makeText(this,  username + "already exists", Toast.LENGTH_SHORT).show();

                }
                else if (c.getCount() == 0) {
                    insertdata(username,password);

                }
            }

        }

    }

    private void insertdata(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2 , username);
        contentValues.put(DatabaseHelper.COL_3 , password);

        db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        Toast.makeText(this,  username + "registeres successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

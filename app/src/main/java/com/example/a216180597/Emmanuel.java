package com.example.a216180597;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Emmanuel extends AppCompatActivity {

      private Button select;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emmanuel);

       select = (Button)findViewById(R.id.select);
        openHelper = new DatabaseHelper(this);

       select.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               db = openHelper.getReadableDatabase();

               Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME ,null);
             if (c.getCount() == 0)
             {
                 showmessage("Error" , "Nothing found");
             }
             StringBuffer buffer = new StringBuffer();
             while (c.moveToNext())
             {
                 buffer.append("id" + c.getString(0) + " \n");
                 buffer.append("username" + c.getString(1) + " \n");
                 buffer.append("passweord" + c.getString(2) + " \n");

             }
             showmessage("users" ,buffer.toString());

           }
       });

    }

    private void showmessage( String title ,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}

package com.example.libven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnRegister;
    EditText etName,etPass;
    String name="",password="";
    SQLiteDatabase sqdb;
    DBHelper my_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_db= new DBHelper(this);

        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.etName);
        etPass= findViewById(R.id.etPass);

        btnLogin= findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etName.getText().toString();
                password=etPass.getText().toString();

                if(checkUser()) {
                    Intent go= new Intent(MainActivity.this,Login.class);
                    startActivity(go);
                }
                else if (checkUser()==false)
                {
                    AlertDialog.Builder adb;
                    adb= new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("User doesn't exist or Incorrect info");
                    adb.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                }
            }
        });
        btnRegister= findViewById(R.id.btnReg);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(MainActivity.this,Register.class);
                startActivity(go);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        menu.add(0,1,0,"Guide");
        menu.add(0,2,0,"Credits");

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID=item.getItemId();
        if(itemID==R.id.back)
        {
            finish();
        }
        if(itemID==1)
        {
            Intent go= new Intent(this,Guide.class);
            startActivity(go);
        }
        if(itemID==2)
        {
            Intent go= new Intent(this,Credits.class);
            startActivity(go);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkUser() {
        boolean flag= false;
        sqdb=my_db.getWritableDatabase();
        Cursor c=sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        int col1=c.getColumnIndex(DBHelper.NAME);
        int col3=c.getColumnIndex(DBHelper.PASSWORD);

        c.moveToFirst();
        while(!c.isAfterLast() && !flag)
        {
            String s1=c.getString(col1);
            String s3=c.getString(col3);

            if(name.equals(s1) && password.equals(s3))
            {
                flag=true;
            }
            c.moveToNext();
        }
        sqdb.close();
        return flag;
    }
}
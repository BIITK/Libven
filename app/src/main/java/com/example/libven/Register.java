package com.example.libven;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    SQLiteDatabase sqdb;
    DBHelper my_db;
    User user;
    String [] info= new String[5];
    ContentValues cv = new ContentValues();

    EditText etName, etNick, etPass, etPhone, etMail;
    Button btnAdd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=findViewById(R.id.etName);
        etNick=findViewById(R.id.etNick);
        etPass=findViewById(R.id.etPass);
        etPhone=findViewById(R.id.etPhone);
        etMail=findViewById(R.id.etMail);
        btnAdd=findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = true;

                String Name = etName.getText().toString();
                String Nick = etNick.getText().toString();
                String Pass = etPass.getText().toString();
                String Phone = etPhone.getText().toString();
                String Mail = etMail.getText().toString();

                if (Name.isEmpty()) {
                    AlertDialog.Builder adb;
                    adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("Please fill the field: Name");
                    adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                } else if (Nick.isEmpty()) {
                    AlertDialog.Builder adb;
                    adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("Please fill the field: Nick");
                    adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                } else if (Pass.isEmpty()) {
                    AlertDialog.Builder adb;
                    adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("Please fill the field: Pass");
                    adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                } else if (Phone.isEmpty()) {
                    AlertDialog.Builder adb;
                    adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("Please fill the field: Phone");
                    adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                } else if (Mail.isEmpty()) {
                    AlertDialog.Builder adb;
                    adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("Please fill the field: Email");
                    adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.show();
                } else {
                    info[0] = Name;
                    info[1] = Nick;
                    info[2] = Pass;
                    info[3] = Phone;
                    info[4] = Mail;

                    sqdb = my_db.getWritableDatabase();
                    Cursor c = sqdb.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
                    int col1 = c.getColumnIndex(DBHelper.NAME);
                    int col2 = c.getColumnIndex(DBHelper.EMAIL);

                    c.moveToFirst();
                    while (!c.isAfterLast()) {
                        String s1 = c.getString(col1);
                        String s2 = c.getString(col2);

                        if (info[0].equals(s1)) {
                            flag = false;
                            Toast.makeText(Register.this, "The name is already taken", Toast.LENGTH_SHORT).show();
                        }
                        if (info[4].equals(s2)) {
                            flag = false;
                            Toast.makeText(Register.this, "The email is already taken", Toast.LENGTH_SHORT).show();
                        }
                        c.moveToNext();
                    }
                    sqdb.close();
                    if (flag == true) {
                        cv.put(my_db.NAME, info[0]);
                        cv.put(my_db.NICK, info[1]);
                        cv.put(my_db.PASSWORD, info[2]);
                        cv.put(my_db.PHONE, info[3]);
                        cv.put(my_db.EMAIL, info[4]);

                        sqdb = my_db.getWritableDatabase();
                        sqdb.insert(my_db.TABLE_NAME, null, cv);
                        sqdb.close();
                    }

                    etName.setText("");
                    etNick.setText("");
                    etPass.setText("");
                    etPhone.setText("");
                    etMail.setText("");
                }
            }
        });

        my_db=new DBHelper(this);
        sqdb=my_db.getWritableDatabase();

        sqdb.close();




    }
}
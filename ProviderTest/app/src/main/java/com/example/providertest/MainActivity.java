package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 添加数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                if (newUri == null) {
                    Toast.makeText(MainActivity.this, "insert data is failed", Toast.LENGTH_SHORT).show();
                }else {
                    newId = newUri.getPathSegments().get(1);
                }
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 查询数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Log.d(TAG, "book name is " + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d(TAG, "book author is " + cursor.getString(cursor.getColumnIndex("author")));
                        Log.d(TAG, "book pages is " + cursor.getInt(cursor.getColumnIndex("pages")));
                        Log.d(TAG, "book price is " + cursor.getDouble(cursor.getColumnIndex("price")));
                    }
                    cursor.close();
                }
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 更新数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                int updateRows = getContentResolver().update(uri, values, null, null);
                Toast.makeText(MainActivity.this, "update rows is " + updateRows, Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 删除数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                int deleteRows = getContentResolver().delete(uri, null, null);
                Toast.makeText(MainActivity.this, "delete rows is " + deleteRows, Toast.LENGTH_SHORT).show();
            }
        });

        Button getType = (Button) findViewById(R.id.get_type);
        getType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 查询类型
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                String type = getContentResolver().getType(uri);
                Toast.makeText(MainActivity.this, "type is " + type, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

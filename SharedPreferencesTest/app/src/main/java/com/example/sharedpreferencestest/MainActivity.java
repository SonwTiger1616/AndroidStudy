package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button) findViewById(R.id.save_data);
        Button restoreData = (Button) findViewById(R.id.restore_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 18);
                editor.putString("","");
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                String name = preferences.getString("name", "");
                int age = preferences.getInt("age", 0);
                boolean married = preferences.getBoolean("married", false);
                Toast.makeText(MainActivity.this, "name:" + name + "\nage:" + age + "\nmarried:"+married, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

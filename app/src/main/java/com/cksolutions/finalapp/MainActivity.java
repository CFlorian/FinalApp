package com.cksolutions.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cksolutions.finalapp.activity.IngresarColorActivity;
import com.cksolutions.finalapp.activity.VerColorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIngresar, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiUI();
    }

    private void initiUI() {
        btnIngresar = findViewById(R.id.btnIngresar);
        btnVer = findViewById(R.id.btnVer);
        btnVer.setOnClickListener(this);
        btnIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnIngresar:
                 intent = new Intent(getApplicationContext(), IngresarColorActivity.class);
                 startActivity(intent);
                break;
            case R.id.btnVer:
                intent = new Intent(getApplicationContext(), VerColorActivity.class);
                startActivity(intent);
                break;
        }
    }
}

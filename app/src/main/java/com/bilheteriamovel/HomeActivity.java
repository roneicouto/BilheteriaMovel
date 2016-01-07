package com.bilheteriamovel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void ir(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void irAListaUsuarios(View view) {
        startActivity(new Intent(this, ListUsuariosActivity.class));
        finish();
    }
}
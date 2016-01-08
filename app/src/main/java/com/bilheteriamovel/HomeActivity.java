package com.bilheteriamovel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import util.Mensagem;

/**
 * Created by mrcoyote on 06/01/16.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.home:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ir(View view) {
        SharedPreferences preferences   = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(this, LoginActivity.class));
        Mensagem.Msg(this,"Logout efetuado!");
    }

    public void irAListaUsuarios(View view) {
        startActivity(new Intent(this, ListUsuariosActivity.class));
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.
        finish();
    }


    public void irAListaEventos(View view) {
        startActivity(new Intent(this, ListEventosActivity.class));
        finish();
    }

    public void cadastrarEvento(View view) {
        startActivity(new Intent(this, CadEventoActivity.class));
        finish();
    }

}
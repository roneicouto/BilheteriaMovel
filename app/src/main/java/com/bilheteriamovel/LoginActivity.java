package com.bilheteriamovel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import dao.UsuarioDAO;
import util.Mensagem;


public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtSenha;
    private UsuarioDAO helper;
    private CheckBox ckbConectado;

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME  = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario   = (EditText) findViewById(R.id.edt_TxtUser);
        edtSenha     = (EditText) findViewById(R.id.edt_TxtPassw);
        ckbConectado = (CheckBox) findViewById(R.id.login_ckbConectado);

        helper = new UsuarioDAO(this);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean conectado             = preferences.getBoolean(MANTER_CONECTADO, false);

        if(conectado){
            ChamarMainAcitivity();
        }
    }

    public void logar(View view){
        String usuario = edtUsuario.getText().toString();
        String senha   = edtSenha.getText().toString();

        boolean validacao = true;

        if(usuario == null || usuario.equals("")){
            validacao = false;
            edtUsuario.setError(getString(R.string.login_valUsuario));
        }

        if(senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.login_valSenha));
        }

        if(validacao){
            //logar
            if(helper.logar(usuario,senha)){
                if(ckbConectado.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor     = sharedPreferences.edit();

                    editor.putBoolean(MANTER_CONECTADO, true);
                    editor.commit();
                }

                ChamarMainAcitivity();
            }else{
                Mensagem.Msg(this, getString(R.string.msg_login_incorreto));
            }
        }
    }

    private void ChamarMainAcitivity(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void ChamarCadastroUsuarioAcitivity(){
        startActivity(new Intent(this, CadUsuarioActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void cadastrarULogin(CadUsuarioActivity cadUsuarioActivity){

        ChamarCadastroUsuarioAcitivity();

    }

    public void cUHome(View view){
        ChamarCadastroUsuarioAcitivity();
    }

}

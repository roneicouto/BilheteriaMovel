package com.bilheteriamovel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;

/**
 * Created by mrcoyote on 06/01/16.
 */

public class CadUsuarioActivity extends AppCompatActivity {

    private EditText usuario_edtNome, edtLogin, edtSenha, edtEmail;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        usuario_edtNome  = (EditText) findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText) findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText) findViewById(R.id.usuario_edtSenha);
        edtEmail = (EditText) findViewById(R.id.usuario_edtEmail);;

        //Modo de edição
        idusuario = getIntent().getIntExtra("USUARIO_ID", 0);
        if(idusuario > 0){
            Usuario model = usuarioDAO.buscarUsuarioPorId(idusuario);
            usuario_edtNome.setText(model.getNome());
            edtLogin.setText(model.getLogin());
            edtSenha.setText(model.getSenha());
            edtEmail.setText(model.getEmail());
            setTitle("Atualizar usuário");
        }
    }

    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    public void cadastrar(){
        boolean validacao = true;

        String nome  = usuario_edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();
        String email = edtEmail.getText().toString();

        if (nome == null || nome.equals("")){
            validacao = false;
            usuario_edtNome.setError(getString(R.string.campo_obrigatorio));
        }

        if (login == null || login.equals("")){
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));
        }

        if (senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));
        }
        if (email == null || email.equals("")){
            validacao = false;
            edtEmail.setError(getString(R.string.campo_obrigatorio));
        }

        if(validacao){
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setEmail(email);


            //Se for atualizar
            if(idusuario > 0){
                usuario.set_id(idusuario);
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);

            if(resultado != -1){
                if(idusuario > 0){
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else{
                    Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
                }

                finish();
                startActivity(new Intent(this, ListUsuariosActivity.class));
            }else{
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastros, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, ListUsuariosActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
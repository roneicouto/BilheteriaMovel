package com.bilheteriamovel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;

/**
 * Created by mrcoyote on 06/01/16.
 */

public class ListUsuariosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista;
    private List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    private int idposicao;

    private AlertDialog alertDialog, alertConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        alertDialog      = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialogConfirmacao(this);

        usuarioDAO     = new UsuarioDAO(this);
        usuarioList    = usuarioDAO.listarUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, usuarioList);

        lista = (ListView) findViewById(R.id.lvUsuarios);
        lista.setAdapter(usuarioAdapter);

        lista.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {

            case R.id.action_cadastrar_usuario:
                finish();
                startActivity(new Intent(this, CadUsuarioActivity.class));
                break;

            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = usuarioList.get(idposicao).get_id();

        switch (which){
            case 0:
                usuarioDAO.buscarUsuarioPorId(id);
                Intent intent = new Intent(this, CadUsuarioActivity.class);
                intent.putExtra("USUARIO_ID", id);
                startActivity(intent);
                break;
            case 1:
                alertConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                usuarioList.remove(idposicao);
                usuarioDAO.removerUsuario(id);
                Mensagem.Msg(this, "Usuário excluído com sucesso");
                lista.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposicao = position;
        alertDialog.show();
    }
}
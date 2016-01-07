package com.bilheteriamovel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.EventoAdapter;
import dao.EventoDAO;
import model.Evento;
import util.Mensagem;

/**
 * Created by mrcoyote on 06/01/16.
 */

public class ListEventosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista;
    private List<Evento> eventoList;
    private EventoAdapter eventoAdapter;
    private EventoDAO eventoDAO;

    private int idposicao;

    private AlertDialog alertDialog, alertConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        alertDialog      = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialogConfirmacao(this);

        eventoDAO     = new EventoDAO(this);
        eventoList    = eventoDAO.listarEventos();
        eventoAdapter = new EventoAdapter(this, eventoList);

        lista = (ListView) findViewById(R.id.lvEventos);
        lista.setAdapter(eventoAdapter);

        lista.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_eventos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cadastrar_evento) {
            startActivity(new Intent(this, CadEventoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = eventoList.get(idposicao).get_id();

        switch (which){
            case 0:
                eventoDAO.removerEvento(id);
                Intent intent = new Intent(this, CadEventoActivity.class);
                intent.putExtra("EVENTO_ID", id);
                startActivity(intent);
                break;
            case 1:
                alertConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                eventoList.remove(idposicao);
                eventoDAO.removerEvento(id);
                Mensagem.Msg(this, "Evento excluído com sucesso");
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
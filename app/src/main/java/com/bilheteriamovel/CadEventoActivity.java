package com.bilheteriamovel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import dao.EventoDAO;
import model.Evento;
import util.Mensagem;

/**
 * Created by mrcoyote on 06/01/16.
 */
public class CadEventoActivity extends AppCompatActivity {

    private EditText evento_edtN_Evento, evento_edtTotaltickets, evento_edtRestickets;
    private EventoDAO eventoDAO;
    private Evento evento;
    private Integer idevento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

        eventoDAO = new EventoDAO(this);

        evento_edtN_Evento  = (EditText) findViewById(R.id.evento_edtN_Evento);
        evento_edtTotaltickets = (EditText) findViewById(R.id.evento_edtTotaltickets);
        evento_edtRestickets = (EditText) findViewById(R.id.evento_edtRestickets);

        //Modo de edição
        idevento = getIntent().getIntExtra("EVENTO_ID", 0);
        if(idevento > 0){
            Evento model = eventoDAO.buscarEventoPorId(idevento);
            evento_edtN_Evento.setText(model.getN_evento());
            evento_edtTotaltickets.setText(model.getTotaltickets());
            evento_edtRestickets.setText(model.getRestickets());
            setTitle("Atualizar usuário");
        }
    }

    @Override
    protected void onDestroy() {
        eventoDAO.fechar();
        super.onDestroy();
    }

    public void cadastrar(){
        boolean validacao = true;

        String n_evento  = evento_edtN_Evento.getText().toString();
        String totaltickets = evento_edtTotaltickets.getText().toString();

        if (n_evento == null || n_evento.equals("")){
            validacao = false;
            evento_edtN_Evento.setError(getString(R.string.campo_obrigatorio));
        }

        if (totaltickets == null || totaltickets.equals("")){
            validacao = false;
            evento_edtTotaltickets.setError(getString(R.string.campo_obrigatorio));
        }



        if(validacao){
            evento = new Evento();
            evento.setN_evento(n_evento);
            evento.setTotaltickets(Integer.parseInt(totaltickets));

            //Se for atualizar
            if(idevento > 0){
                evento.set_id(idevento);
            }

            long resultado = eventoDAO.salvarEvento(evento);

            if(resultado != -1){
                if(idevento > 0){
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else{
                    Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
                }

                finish();
                startActivity(new Intent(this, HomeActivity.class));
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
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

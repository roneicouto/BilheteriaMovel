package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bilheteriamovel.R;

import java.util.List;

import model.Evento;

/**
 * Created by mrcoyote on 06/01/16.
 */
public class EventoAdapter extends BaseAdapter {
    private Context context;
    private List<Evento> lista;

    public EventoAdapter(Context ctx, List<Evento> eventos){
        this.context = ctx;
        this.lista = eventos;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Evento evento = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.eventos, null);
        }

        TextView txtNome = (TextView) view.findViewById(R.id.evento_lista_nome);
        //TextView txtTotickets = (TextView) view.findViewById(R.id.evento_lista_totickets);
        //TextView txtRestickets = (TextView) view.findViewById(R.id.evento_lista_restickets);
        txtNome.setText(evento.getN_evento());
        //txtTotickets.setText(evento.getTotaltickets());
        //txtRestickets.setText(evento.getRestickets());

        return view;
    }
}

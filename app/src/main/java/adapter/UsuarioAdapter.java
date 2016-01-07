package adapter;

/**
 * Created by mrcoyote on 05/01/16.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bilheteriamovel.R;

import java.util.List;

import model.Usuario;

/**
 * Created by mrcoyote on 05/01/16.
 */
public class UsuarioAdapter extends BaseAdapter {

    private Context context;
    private List<Usuario> lista;

    public UsuarioAdapter(Context ctx, List<Usuario> usuarios){
        this.context = ctx;
        this.lista = usuarios;
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
        Usuario usuario = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.usuarios, null);
        }

        TextView txtNome = (TextView) view.findViewById(R.id.usuario_lista_nome);
        txtNome.setText(usuario.getNome());

        return view;
    }
}

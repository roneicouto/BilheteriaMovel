package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Evento;


/**
 * Created by mrcoyote on 06/01/16.
 */
public class EventoDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public EventoDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Evento criarEvento(Cursor cursor){
        Evento model = new Evento(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Eventos._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Eventos.N_EVENTO)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Eventos.TOTALTICKETS)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Eventos.RESTICKETS))
        );
        return model;
    }

    public List<Evento> listarEventos(){

        Cursor cursor = getDatabase().query(DatabaseHelper.Eventos.TABELA,
                DatabaseHelper.Eventos.COLUNAS, null, null, null, null, null);

        List<Evento> eventos = new ArrayList<Evento>();
        while (cursor.moveToNext()){
            Evento model = criarEvento(cursor);
            eventos.add(model);
        }
        cursor.close();
        return eventos;
    }

    public long salvarEvento(Evento evento){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Eventos.N_EVENTO, evento.getN_evento());
        valores.put(DatabaseHelper.Eventos.TOTALTICKETS, evento.getTotaltickets());
        valores.put(DatabaseHelper.Eventos.RESTICKETS, evento.getRestickets());

        if(evento.get_id() != null){
            return getDatabase().update(DatabaseHelper.Eventos.TABELA, valores,
                    "_id = ?", new String[]{ evento.get_id().toString() });
        }
        return getDatabase().insert(DatabaseHelper.Eventos.TABELA, null, valores);
    }

    public boolean removerEvento(Integer id){
        return getDatabase().delete(DatabaseHelper.Eventos.TABELA, "_id = ?",
                new String[]{Integer.toString(id)})>0;
    }

    public Evento buscarEventoPorId(Integer id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Eventos.TABELA,
                DatabaseHelper.Eventos.COLUNAS, "_id = ?", new String[]{ Integer.toString(id) }, null, null, null);

        if(cursor.moveToNext()){
            Evento model = criarEvento(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public void fechar(){
        databaseHelper.close();
        database = null;
    }

}

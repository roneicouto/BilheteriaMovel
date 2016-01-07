package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mrcoyote on 04/01/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tickets";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de Usuários
        db.execSQL("create table usuarios (_id integer primary key autoincrement, "
        +"nome text not null, login text not null, senha text not null, email text, "
                +"dtcriado datetime default current_timestamp)");

        //Usuario padrao
        db.execSQL("insert into usuarios(nome, login, senha, email) values('Admin','admin','123',"
        +"'admin@admin.com')");

        //Tabela de Eventos
        db.execSQL("create table eventos (_id integer primary key autoincrement, "
        +"n_evento text not null,"
        +"totaltickets integer, restickets integer, dtcriado datetime default current_timestamp)");

        //Tabela de Tickets
        db.execSQL("create table tickets(_id integer primary key autoincrement, "
        +"_iduser integer, _idevent integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{

        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String EMAIL = "email";

        public static final String[] COLUNAS = new String[]{
               _ID, NOME, LOGIN, SENHA, EMAIL
        };
    }

    public static class Eventos{

        public static final String TABELA = "eventos";
        public static final String _ID = "_id";
        public static final String N_EVENTO = "n_evento";
        public static final String TOTALTICKETS = "totaltickets";
        public static final String RESTICKETS = "restickets";

        public static final String[] COLUNAS = new String[]{
          _ID, N_EVENTO, TOTALTICKETS, RESTICKETS
        };
    }

    public static class Tickets{

        public static final String TABELA = "tickets";
        public static final String _ID = "_id";
        public static final String _IDUSER = "_iduser";
        public static final String _IDEVENT = "_idevent";

        public static final String[] COLUNAS = new String[]{
          _ID, _IDUSER, _IDEVENT
        };
    }

}

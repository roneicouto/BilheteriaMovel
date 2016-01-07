package model;

/**
 * Created by mrcoyote on 04/01/16.
 */
public class Ticket {

    private int _id;
    private int _iduser;
    private int _idevento;

    //Construtores
    public Ticket() {
    }

    public Ticket(int id, int _iduser, int _idevento) {
        this._id = id;
        this._iduser = _iduser;
        this._idevento = _idevento;
    }

    //Getters e Setters


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_iduser() {
        return _iduser;
    }

    public void set_iduser(int _iduser) {
        this._iduser = _iduser;
    }

    public int get_idevento() {
        return _idevento;
    }

    public void set_idevento(int _idevento) {
        this._idevento = _idevento;
    }
}

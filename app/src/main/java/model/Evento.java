package model;

/**
 * Created by mrcoyote on 04/01/16.
 */
public class Evento {
    private Integer _id;
    private String n_evento;
    private Integer totaltickets;
    private Integer restickets;

    //CONSTRUTORES
    public Evento() {
    }

    public Evento(Integer _id, String n_evento, Integer totaltickets, Integer restickets) {
        this._id = _id;
        this.n_evento = n_evento;
        this.totaltickets = totaltickets;
        this.restickets = restickets;
    }

    //Getters e Setters

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getN_evento() {
        return n_evento;
    }

    public void setN_evento(String n_evento) {
        this.n_evento = n_evento;
    }

    public Integer getTotaltickets() {
        return totaltickets;
    }

    public void setTotaltickets(Integer totaltickets) {
        this.totaltickets = totaltickets;
    }

    public Integer getRestickets() {
        return restickets;
    }

    public void setRestickets(Integer restickets) {
        this.restickets = restickets;
    }
}

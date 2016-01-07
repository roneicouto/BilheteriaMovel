package model;

/**
 * Created by mrcoyote on 04/01/16.
 */
public class Usuario {

    private  Integer _id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String dtcriado;

    //Construtores
    public Usuario(){}

    public Usuario(Integer id, String nome, String login, String senha, String email, String dtcriado){
        this._id=id;
        this.nome=nome;
        this.login=login;
        this.senha=senha;
        this.email=email;
        this.dtcriado=dtcriado;
    }

    public Usuario(Integer id, String nome, String login, String senha, String email){
        this._id=id;
        this.nome=nome;
        this.login=login;
        this.senha=senha;
        this.email=email;
    }

    public Usuario(String nome, String login, String senha, String email){
        this.nome=nome;
        this.login=login;
        this.senha=senha;
        this.email=email;
    }
    //Getters and Setters

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDtcriado() {
        return dtcriado;
    }

    public void setDtcriado(String dtcriado) {
        this.dtcriado = dtcriado;
    }
}

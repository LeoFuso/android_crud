package mackleaps.csbc.model;

/**
 * Created by Leonardo Fuso on 31/05/17.
 */

public class Register {

    private long id_registro;

    private String nome;

    private String telefone;

    private String email;

    private Boolean status;


    public Register() {
    }

    public Register(long id_registro, String nome, String telefone, String email, Boolean status) {

        this.id_registro = id_registro;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
    }

    public long getId_registro() {
        return id_registro;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId_registro(long id_registro) {
        this.id_registro = id_registro;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}

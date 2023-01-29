package ifpr.pgua.eic.projetointegrador.model.entities;

import java.time.LocalDateTime;

public class Usuario {
    private int id; 
    private String nome;
    private LocalDateTime dataNascimento;
    private String email;

    public Usuario(int id, String nome, LocalDateTime dataNascimento, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }
    
    public Usuario(String nome, LocalDateTime dataNascimento, String email){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento.toString() + ", email=" + email + "]";
    }


}

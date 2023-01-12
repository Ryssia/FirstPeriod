package ifpr.pgua.eic.projetointegrador.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    private String nome;
    private LocalDateTime dataNascimento;
    private String email;

    public Usuario(String nome, LocalDateTime dataNascimento, String email){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
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


}

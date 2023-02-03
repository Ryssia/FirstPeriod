package ifpr.pgua.eic.projetointegrador.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaCadastro extends BaseController{
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private Label lbErro;

    private UsuarioRepository repositorio;

    public TelaCadastro(UsuarioRepository repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    private void onActionSalvar(){
        LocalDate localDate = dpDataNascimento.getValue();
        Result resultado = repositorio.salvar(new Usuario(tfNome.getText(), LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0), tfEmail.getText()));
        System.out.println(resultado.getMsg());
    }

}

    

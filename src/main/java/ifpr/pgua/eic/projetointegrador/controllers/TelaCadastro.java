package ifpr.pgua.eic.projetointegrador.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        if(tfNome.getText() != null && dpDataNascimento.getValue() != null && tfEmail.getText() != null){

            LocalDate localDate = dpDataNascimento.getValue();
            Result resultado = repositorio.salvar(new Usuario(tfNome.getText(), LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0), tfEmail.getText()));
            
            Alert popAlert = new Alert(Alert.AlertType.INFORMATION);
            popAlert.setHeaderText(resultado.getMsg());
            popAlert.show();

            if(resultado instanceof SuccessResult){
                
                App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
            }

        }
        else{
            Alert popAlert = new Alert(Alert.AlertType.ERROR);
            popAlert.setHeaderText("Todos os campos devem estar preenchidos!");
            popAlert.show();
        }

    }

}

    

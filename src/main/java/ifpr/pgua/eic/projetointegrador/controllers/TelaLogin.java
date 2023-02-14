package ifpr.pgua.eic.projetointegrador.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaLogin extends BaseController{
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private Label lbErro;   

    private UsuarioRepository repositorio;

    public TelaLogin(UsuarioRepository repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    private void onActionLogar(){

        if(!(tfEmail.getText() == null) && !(dpDataNascimento.getValue() == null)){     //verifica se usuário e data estão preenchidos
            LocalDate localDate = dpDataNascimento.getValue();
            Usuario usuario = repositorio.logar(tfEmail.getText(), LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0));

            if(usuario == null){            //testa se usuário logou
                System.out.println("");

                Alert popAlert = new Alert(Alert.AlertType.ERROR);
                popAlert.setHeaderText("Usuário inválido!");
                popAlert.show();

            }
            else{
                App.pushScreen("PRINCIPAL");
                App.changeScreenRegion("HOME", BorderPaneRegion.CENTER);
            }
        }
        else{

            Alert popAlert = new Alert(Alert.AlertType.ERROR);
            popAlert.setHeaderText("Email ou data de nascimento não preenchidos!");
            popAlert.show();
        }
        
    }

    @FXML
    private void onActionCadastrar(){
        App.changeScreenRegion("CADASTRO", BorderPaneRegion.CENTER); //abre tela Cadastro
    }

}

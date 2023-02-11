package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TelaPrincipal extends BaseController implements Initializable {
    
    @FXML
    private Button btTelaLogin, btTelaCiclo, btTelaInfo;

    @FXML
    private void abrirTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    }

    @FXML
    private void abrirTelaCicloMenstrual(){
        App.changeScreenRegion("LISTARCICLO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void abrirTelaInfo(){
        App.changeScreenRegion("INFO", BorderPaneRegion.CENTER);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(UsuarioRepository.getUsuarioLogado() == null){
            btTelaInfo.setDisable(true);
            btTelaCiclo.setDisable(true);
        }
        else{
            btTelaLogin.setDisable(true);
            btTelaInfo.setDisable(false);
            btTelaCiclo.setDisable(false);
            
        }
        
    }

}

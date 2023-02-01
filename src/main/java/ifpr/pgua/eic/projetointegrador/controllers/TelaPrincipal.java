package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaPrincipal extends BaseController {
    
    @FXML
    private void carregarClientes(){
        App.changeScreenRegion("CLIENTES", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregarProdutos(){
        App.changeScreenRegion("PRODUTOS", BorderPaneRegion.CENTER);
    }

    @FXML
    private void abrirTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    }

    @FXML
    private void abrirTelaCicloMenstrual(){
        // TODO  Auto-generated method stub
        //App.changeScreenRegion("CICLOMENSTRUAL", BorderPaneRegion.CENTER);
    }

    @FXML
    private void abrirTelaInfo(){
        App.changeScreenRegion("INFO", BorderPaneRegion.CENTER);
    }


    
}

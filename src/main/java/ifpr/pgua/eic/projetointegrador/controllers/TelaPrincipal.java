package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaPrincipal extends BaseController {
    
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


    
}

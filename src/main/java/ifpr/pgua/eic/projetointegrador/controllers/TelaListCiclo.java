package ifpr.pgua.eic.projetointegrador.controllers;

import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TelaListCiclo implements Initializable{

    private CicloMenstrualRepository cicloMenstrualRepository;

    @FXML
    private ListView<CicloMenstrual> lvCiclos;


    public TelaListCiclo(CicloMenstrualRepository cicloMenstrualRepository){
        this.cicloMenstrualRepository = cicloMenstrualRepository;
    }

    private void listarCiclo(){
        List<CicloMenstrual> listaCiclos = this.cicloMenstrualRepository.listar(UsuarioRepository.getUsuarioLogado());
        lvCiclos.getItems().clear();
        for (CicloMenstrual cicloMenstrual : listaCiclos) {
            lvCiclos.getItems().add(cicloMenstrual);
        }
    }
    
    @FXML
    public void onActionAdd(){
        App.changeScreenRegion("CADASTROCICLO", BorderPaneRegion.CENTER);
    }

    @FXML
    public void onActionEditar(){
        CicloMenstrualRepository.selecionado = lvCiclos.getSelectionModel().getSelectedItem();
        App.changeScreenRegion("CADASTROCICLO", BorderPaneRegion.CENTER);
        
    }

    @FXML
    public void onActionExcluir(){
        //"verificar objeto selecionado" para impedir "null"pointer
        cicloMenstrualRepository.excluir(lvCiclos.getSelectionModel().getSelectedItem());
        //"atualizar a lista" de ciclos para que o item excluido não apareça mais
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        CicloMenstrualRepository.selecionado = null;
        listarCiclo();
    }
}

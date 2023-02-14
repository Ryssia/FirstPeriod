package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCiclo implements Initializable{

    private CicloMenstrualRepository cicloMenstrualRepository;

    public TelaCiclo(CicloMenstrualRepository cicloMenstrualRepository){
        this.cicloMenstrualRepository = cicloMenstrualRepository;
    }
    
    @FXML
    private DatePicker dpDataIni;

    @FXML
    private DatePicker dpDataTerm;

    @FXML
    private TextField tfFluxo;

    @FXML
    private TextArea taComentarios;

    @FXML
    public void onActionSalvar(){
        Usuario usuarioLogado = UsuarioRepository.getUsuarioLogado();
        LocalDateTime dataInicio = null;
        LocalDateTime dataTermino = null;
        String tipoFluxo = "";
        String comentarios = "";

        Alert popAlert = new Alert(Alert.AlertType.NONE);
        Result pop;

        if(usuarioLogado == null){
            System.out.println("Usuário não logado");
            return;
        }
        int idUsuario = UsuarioRepository.getUsuarioLogado().getId();
        

        if(dpDataIni.getValue() != null && dpDataTerm.getValue() != null){
            dataInicio = LocalDateTime.of(dpDataIni.getValue().getYear(), dpDataIni.getValue().getMonth(), dpDataIni.getValue().getDayOfMonth(), 0, 0, 0);
            dataTermino = LocalDateTime.of(dpDataTerm.getValue().getYear(), dpDataTerm.getValue().getMonth(), dpDataTerm.getValue().getDayOfMonth(), 0, 0, 0);;
            tipoFluxo = tfFluxo.getText();
            comentarios = taComentarios.getText();

        }


        if(CicloMenstrualRepository.selecionado == null){       //só habilita cadastro se não houver nada selecionado
            CicloMenstrual cicloMenstrual = new CicloMenstrual(idUsuario, dataInicio, dataTermino, tipoFluxo, comentarios);
            pop = cicloMenstrualRepository.cadastrar(cicloMenstrual);
            
            popAlert.setAlertType(AlertType.INFORMATION);
            popAlert.setHeaderText(pop.getMsg());
            popAlert.show();
            App.changeScreenRegion("LISTARCICLO", BorderPaneRegion.CENTER);
        }
        else{
            CicloMenstrualRepository.selecionado.setDataInicio(dataInicio);
            CicloMenstrualRepository.selecionado.setDataTermino(dataTermino);
            CicloMenstrualRepository.selecionado.setTipoFluxo(tipoFluxo);
            CicloMenstrualRepository.selecionado.setComentarios(comentarios); 
            cicloMenstrualRepository.editar(CicloMenstrualRepository.selecionado);
            CicloMenstrualRepository.selecionado = null;
            App.changeScreenRegion("LISTARCICLO", BorderPaneRegion.CENTER);
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(CicloMenstrualRepository.selecionado != null){       //popula a tela do editar SE tiver item selecionado
            dpDataIni.setValue(CicloMenstrualRepository.selecionado.getDataInicio().toLocalDate());
            dpDataTerm.setValue(CicloMenstrualRepository.selecionado.getDataTermino().toLocalDate());
            tfFluxo.setText(CicloMenstrualRepository.selecionado.getTipoFluxo());
            taComentarios.setText(CicloMenstrualRepository.selecionado.getComentarios());
        }
    }
    
}

package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.google.protobuf.Empty;

import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

            //pop alert dados nulos
        }


        if(CicloMenstrualRepository.selecionado == null){       //só habilita cadastro se não houver nada selecionado
            CicloMenstrual cicloMenstrual = new CicloMenstrual(idUsuario, dataInicio, dataTermino, tipoFluxo, comentarios);
            cicloMenstrualRepository.cadastrar(cicloMenstrual);
        }
        else{
            CicloMenstrualRepository.selecionado.setDataInicio(dataInicio);
            CicloMenstrualRepository.selecionado.setDataTermino(dataTermino);
            CicloMenstrualRepository.selecionado.setTipoFluxo(tipoFluxo);
            CicloMenstrualRepository.selecionado.setComentarios(comentarios); 
            cicloMenstrualRepository.editar(CicloMenstrualRepository.selecionado);
            CicloMenstrualRepository.selecionado = null;

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

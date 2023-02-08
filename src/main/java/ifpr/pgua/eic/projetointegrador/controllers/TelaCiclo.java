package ifpr.pgua.eic.projetointegrador.controllers;

import java.time.LocalDateTime;

import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCiclo {

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
        if(usuarioLogado == null){
            System.out.println("Usuário não logado");
            return;
        }
        int idUsuario = UsuarioRepository.getUsuarioLogado().getId();
        LocalDateTime dataInicio = LocalDateTime.of(dpDataIni.getValue().getYear(), dpDataIni.getValue().getMonth(), dpDataIni.getValue().getDayOfMonth(), 0, 0, 0);
        LocalDateTime dataTermino = LocalDateTime.of(dpDataTerm.getValue().getYear(), dpDataTerm.getValue().getMonth(), dpDataTerm.getValue().getDayOfMonth(), 0, 0, 0);;
        String tipoFluxo = tfFluxo.getText();
        String comentarios = taComentarios.getText();
        CicloMenstrual cicloMenstrual = new CicloMenstrual(idUsuario, dataInicio, dataTermino, tipoFluxo, comentarios);
        cicloMenstrualRepository.cadastrar(cicloMenstrual);
    }

    
}

package ifpr.pgua.eic.projetointegrador.controllers;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.JDBCInfoDAO;
import ifpr.pgua.eic.projetointegrador.utils.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaInfo extends BaseController{

    private JDBCInfoDAO jdbcInfoDAO;

    @FXML
    private HBox hbInfo;

    public TelaInfo(JDBCInfoDAO jdbcInfoDAO) {
        this.jdbcInfoDAO = jdbcInfoDAO;
    }

    public void onActionCiclo(){
        hbInfo.getChildren().clear();
        List<String> listaInformacoes = jdbcInfoDAO.consultaInfo(Categoria.CICLO);
        for(String info: listaInformacoes){
            VBox postIt = new VBox();
            Label label = new Label();
            label.setText(info);
            postIt.getChildren().add(label);
            hbInfo.getChildren().add(postIt);
        }


    }
}

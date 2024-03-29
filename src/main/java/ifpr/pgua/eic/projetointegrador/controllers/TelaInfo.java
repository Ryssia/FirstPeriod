package ifpr.pgua.eic.projetointegrador.controllers;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.JDBCInfoDAO;
import ifpr.pgua.eic.projetointegrador.utils.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TelaInfo extends BaseController{

    private JDBCInfoDAO jdbcInfoDAO;

    @FXML
    private VBox vbInfo;

    public TelaInfo(JDBCInfoDAO jdbcInfoDAO) {
        this.jdbcInfoDAO = jdbcInfoDAO;
    }

    @FXML
    private void onActionCiclo(){
        montaInformacoes(jdbcInfoDAO.consultaInfo(Categoria.CICLO));
    }

    @FXML 
    private void onActionColicas(){
        montaInformacoes(jdbcInfoDAO.consultaInfo(Categoria.COLICA));
    }

    @FXML
    public void onActionAbs(){
        montaInformacoes(jdbcInfoDAO.consultaInfo(Categoria.ABSORVENTE));
    }

    private void montaInformacoes(List<String> informacoes){
        vbInfo.getChildren().clear();

        for(String info: informacoes){      //laço que cria componentes na tela para cada informação
            VBox postIt = new VBox();
            Label label = new Label();

            label.setText(info);
            label.setWrapText(true);
            postIt.setPrefWidth(400.0);

            postIt.getChildren().add(label);
            postIt.getStyleClass().add("card");
            vbInfo.getChildren().add(postIt);
            
        }
    }
}

package ifpr.pgua.eic.projetointegrador.controllers;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.JDBCInfoDAO;
import ifpr.pgua.eic.projetointegrador.utils.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaInfo extends BaseController{

    private JDBCInfoDAO jdbcInfoDAO;

    @FXML
    private HBox hbInfo;

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
        int loop = 1;
        hbInfo.getChildren().clear();

        for(String info: informacoes){
            String[] tokens = info.split("&");  //fraciona a String no ponto &
            if(tokens.length == 1){
                VBox postIt = new VBox();
                Label label = new Label();
                label.setText(tokens[0]);
                postIt.getChildren().add(label);
                hbInfo.getChildren().add(postIt);
            }
            else if(tokens.length == 2){
                if(loop%2 == 0){
                    //aqui vai a imagem
                    
                    VBox postIt = new VBox();
                    Label label = new Label();
                    label.setText(info);
                    postIt.getChildren().add(label);
                    hbInfo.getChildren().add(postIt);
                    loop++;
                }
                else{
                    VBox postIt = new VBox();
                    Label label = new Label();
                    Image img = new Image("@../img/i"+tokens[1]); //verificar depois caminho da pasta para acesso da img
                    ImageView imageView = new ImageView(img);
                    label.setText(info);
                    postIt.getChildren().add(label);
                    hbInfo.getChildren().add(postIt);

                    VBox vbImg = new VBox();
                    vbImg.getChildren().add(imageView);
                    hbInfo.getChildren().add(vbImg);

                    loop++;
                }
                
            }
           
        }
    }
}

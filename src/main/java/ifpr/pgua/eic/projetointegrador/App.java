package ifpr.pgua.eic.projetointegrador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import ifpr.pgua.eic.projetointegrador.controllers.TelaCadastro;
import ifpr.pgua.eic.projetointegrador.controllers.TelaHome;
import ifpr.pgua.eic.projetointegrador.controllers.TelaInfo;
import ifpr.pgua.eic.projetointegrador.controllers.TelaLogin;
import ifpr.pgua.eic.projetointegrador.controllers.TelaPrincipal;
import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.CicloMenstrualDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCCicloMenstrualDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCInfoDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.ScreenRegistryFXML;


/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    FabricaConexoes fabricaConexoes;
    UsuarioDAO usuarioDAO;
    CicloMenstrualDAO cicloMenstrualDAO;
    JDBCInfoDAO infoDAO;
    UsuarioRepository usuarioRepository;
    CicloMenstrualRepository cicloMenstrualRepository;
    
    //DEFINIR A FABRICA DE CONEXÕES, DAOS e REPOSITÓRIOS

    @Override
    public void init() throws Exception {
        super.init();

        this.fabricaConexoes = FabricaConexoes.getInstance();
        this.usuarioDAO = new JDBCUsuarioDAO(fabricaConexoes);
        this.cicloMenstrualDAO = new JDBCCicloMenstrualDAO(fabricaConexoes);
        this.infoDAO = new JDBCInfoDAO(fabricaConexoes);
        this.usuarioRepository = new UsuarioRepository(usuarioDAO);
        this.cicloMenstrualRepository = new CicloMenstrualRepository(cicloMenstrualDAO);

        //INSTANCIAR FABRICA, DAOS E REPOSITÓRIOS
    }

    @Override
    public void stop() throws Exception {
        super.stop();

    }



    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        return "Projeto Integrador";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o)->new TelaPrincipal()));
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o)->new TelaLogin(usuarioRepository)));
        registraTela("CADASTRO", new ScreenRegistryFXML(getClass(), "fxml/cadastro.fxml", (o)->new TelaCadastro(usuarioRepository)));
        registraTela("HOME", new ScreenRegistryFXML(getClass(), "fxml/home.fxml", (o)->new TelaHome()));
        registraTela("INFO", new ScreenRegistryFXML(getClass(), "fxml/info.fxml", (o)->new TelaInfo(infoDAO)));
        
        //REGISTRAR AS OUTRAS TELAS

    }

}
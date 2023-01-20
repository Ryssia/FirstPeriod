package ifpr.pgua.eic.projetointegrador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCCicloMenstrualDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.repositories.CicloMenstrualRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.UsuarioRepository;

public class MainDebug {

    public static void main(String[] args) {
        
        FabricaConexoes fabricaConexoes = FabricaConexoes.getInstance();
        UsuarioDAO jdbcUsuarioDAO = new JDBCUsuarioDAO(fabricaConexoes);
        UsuarioRepository usuarioRepository = new UsuarioRepository(jdbcUsuarioDAO);
        
        String nome = "Beltrano", email = "beltrano@gmail.com";
        LocalDateTime dataNascimento = LocalDateTime.now();

        Usuario usuario = new Usuario(nome, dataNascimento, email);

        System.out.println(usuarioRepository.salvar(usuario).getMsg());

        usuario = usuarioRepository.buscar("beltrano@gmail.com"); //busca

        if(usuario != null){
            System.out.println("Achou!! "  + usuario.getNome() + " : " + usuario.getId() );
            // usuario.setNome("fulano");
            // usuario.setEmail("fulano@gmail.com");
            // usuarioRepository.salvar(usuario); //edita
        }
        else
            System.out.println("Nao achei =[");

        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        listaUsuarios = usuarioRepository.listar();

        for(Usuario user: listaUsuarios){
            System.out.println("====================================");
            System.out.println("Id: \t\t\t" + user.getId());
            System.out.println("Nome: \t\t\t" + user.getNome());
            System.out.println("Email: \t\t\t" + user.getEmail());
            System.out.println("Data Nascimento: \t" + user.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("====================================");
        }
        
        usuarioRepository.excluir(usuarioRepository.buscar("beltrano@gmail.com"));

        listaUsuarios = usuarioRepository.listar();

        for(Usuario user: listaUsuarios){
            System.out.println("====================================");
            System.out.println("Id: \t\t\t" + user.getId());
            System.out.println("Nome: \t\t\t" + user.getNome());
            System.out.println("Email: \t\t\t" + user.getEmail());
            System.out.println("Data Nascimento: \t" + user.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("====================================");
        }


        JDBCCicloMenstrualDAO jdbcCicloMenstrualDAO = new JDBCCicloMenstrualDAO(fabricaConexoes);
        CicloMenstrualRepository cicloMenstrualRepository = new CicloMenstrualRepository(jdbcCicloMenstrualDAO);
        
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(3); //DatePicker.getValue();
        LocalDateTime dataTermino = LocalDateTime.now().plusDays(3); //DatePicker.getValue();
        String tipoFluxo = "Aterrorizante"; //textField.getValue();
        String comentarios = "Nada a comentar"; //textField.getValue();

        usuario = usuarioRepository.buscar("fulano@gmail.com");

        CicloMenstrual cicloMenstrual = new CicloMenstrual(usuario.getId(), dataInicio, dataTermino, tipoFluxo, comentarios);

        System.out.println(cicloMenstrualRepository.cadastrar(cicloMenstrual).getMsg());

        List<CicloMenstrual> listaCiclos = new ArrayList<CicloMenstrual>();

        listaCiclos = cicloMenstrualRepository.listar(usuario);

        for(CicloMenstrual ciclo: listaCiclos){
            System.out.println("====================================");
            System.out.println("Id: \t\t\t\t" + ciclo.getIdCicloMenstrual());
            System.out.println("Id Usuario: \t\t\t" + ciclo.getIdUsuario());
            System.out.println("Data inicio: \t\t\t" + ciclo.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Data termino: \t\t\t" + ciclo.getDataTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Tipo de Fluxo: \t\t\t" + ciclo.getTipoFluxo());
            System.out.println("Comentários: \t\t\t" + ciclo.getComentarios() );
            System.out.println("====================================");
        }
        
        cicloMenstrualRepository.excluir(cicloMenstrual);

        listaCiclos = cicloMenstrualRepository.listar(usuario);

        for(CicloMenstrual ciclo: listaCiclos){
            System.out.println("====================================");
            System.out.println("Id: \t\t\t\t" + ciclo.getIdCicloMenstrual());
            System.out.println("Id Usuario: \t\t\t" + ciclo.getIdUsuario());
            System.out.println("Data inicio: \t\t\t" + ciclo.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Data termino: \t\t\t" + ciclo.getDataTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Tipo de Fluxo: \t\t\t" + ciclo.getTipoFluxo());
            System.out.println("Comentários: \t\t\t" + ciclo.getComentarios() );
            System.out.println("====================================");
        }
    }
}
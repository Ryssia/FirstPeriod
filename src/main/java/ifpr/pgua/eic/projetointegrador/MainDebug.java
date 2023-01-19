package ifpr.pgua.eic.projetointegrador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
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
    }
}
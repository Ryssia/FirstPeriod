package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class UsuarioRepository {

    private UsuarioDAO dao;

    public UsuarioRepository(UsuarioDAO dao) {
        this.dao = dao;
    }

    // public Result salvar(Usuario usuario){
    //     //validações
    //     Result resultado;
    //     if(usuario.getId() != 0)
    //         resultado = dao.editar(usuario);
    //     else
    //         resultado = dao.cadastrar(usuario);

    //     return resultado;
    // }

    public Result cadastrar(String nome, LocalDateTime dataNascimento, String email){
        //validação dos dados
        // nome não pode ser vazio, apenas letras, 
        //data n pode ser vazia, e nao pode ser futura
        //email deve conter o @.com  //procurar REGEX (Regular Expression)
        
        Usuario usuario = new Usuario(nome, dataNascimento, email);
        return dao.cadastrar(usuario);
    }
    


    public List<Usuario> listar(){
        
        return lista;
    }
}

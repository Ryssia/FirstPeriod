package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class UsuarioRepository {

    private UsuarioDAO dao; //DAO Data Access Object

    public UsuarioRepository(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Result salvar(Usuario usuario){
        //validação dos dados
        if(usuario.getNome().isBlank() || !usuario.getNome().matches("^[a-zA-Z]*$")){
            return Result.fail("O nome cadastrado deve possuir apenas letras e não pode estar vazio");
        }
        
        //data n pode ser vazia, e nao pode ser futura
        if(usuario.getDataNascimento() == null || usuario.getDataNascimento().isAfter(LocalDateTime.now())){
            return Result.fail("A data não pode estar vazia e não pode ser uma data futura!");
        }
        //email deve conter o @.com  //procurar REGEX (Regular Expression)
        
        if(usuario.getEmail().isBlank() || !usuario.getEmail().matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
            return Result.fail("O email deve ser no formato padrão. Ex: exemplo@email.com");
        }
        
        if(usuario.getId() != 0)
            return dao.editar(usuario);
        else
            return dao.cadastrar(usuario);
    }

    public Usuario buscar(String email){    //apenas adm
        if(email.isBlank()){
            return null;
        }
        return dao.buscar(email);

    }

    public List<Usuario> listar(){          //apenas adm
        return dao.listar();
    }

    public Result excluir(Usuario usuario){ //apenas adm
        return dao.excluir(usuario);
    }
}

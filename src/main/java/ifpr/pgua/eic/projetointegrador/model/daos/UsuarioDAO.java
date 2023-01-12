package ifpr.pgua.eic.projetointegrador.model.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface UsuarioDAO {
    //crud
    Result cadastrar(Usuario usuario);     //tipo do retorno- nome do metodo(tipo do parametro) -> assinatura do método
    Usuario buscar(String email);
    List<Usuario> listar();
    Result editar(Usuario usuario);
    Result excluir(Usuario usuario);

}

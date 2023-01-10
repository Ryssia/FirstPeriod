package ifpr.pgua.eic.projetointegrador.model.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;

public interface UsuarioDAO {
    //crud
    boolean cadastrar(Usuario usuario);     //tipo do retorno- nome do metodo(tipo do parametro) -> assinatura do método
    Usuario buscar(String email);
    List<Usuario> listar();
    boolean editar(Usuario usuario);
    boolean excluir(Usuario usuario);

}

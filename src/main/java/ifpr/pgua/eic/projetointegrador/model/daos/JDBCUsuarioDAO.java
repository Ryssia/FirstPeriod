package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;

public class JDBCUsuarioDAO implements UsuarioDAO{

    private FabricaConexoes fabricaConexoes;

    public JDBCUsuarioDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public boolean cadastrar(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();   //declaraçao e inicializaçao

            String sql = "INSERT INTO tb_usuarios(nome_usuario, email_usuario, data_nascimento) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pstm.setDate(3, Date.valueOf(sdf.format(usuario.getDataNascimento())));

            return true;

        } catch (Exception e) {
            e.printStackTrace(); //printa erros
            return false;                      
        }
        
    }

    @Override
    public Usuario buscar(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Usuario> listar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean editar(Usuario usuario) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean excluir(Usuario usuario) {
        // TODO Auto-generated method stub
        return false;
    }
    
}

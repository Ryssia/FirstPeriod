package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCUsuarioDAO implements UsuarioDAO{

    private FabricaConexoes fabricaConexoes;

    public JDBCUsuarioDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result cadastrar(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();   //declaraçao e inicializaçao

            String sql = "INSERT INTO tb_usuarios(nome_usuario, email_usuario, data_nascimento) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setLong(3, usuario.getDataNascimento().toEpochDay());

            System.out.println(usuario.getDataNascimento().toEpochDay());

            pstm.executeUpdate();   //executa a query no banco de dados
            pstm.close();       //fecha a conexao para salvar as alteraçoes
            con.close();    //fecha para evitar que fique com muitas conexoes abertas

            return Result.success("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            return Result.fail(e.getMessage()); 
                                  
        } catch(Exception e){
            return Result.fail(e.getMessage());

        }
        
    }

    @Override
    public Usuario buscar(String email) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_usuarios tbu WHERE tbu.email_usuario = ?";   //comando que vai selecionar algum item da tabela 
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, email);

            ResultSet consulta = pstm.executeQuery();   //guarda o resultado da busca realizada
            pstm.close();
            con.close();

            if(consulta.next()){
                String nome = consulta.getString("nome_usuario");
                LocalDateTime dataNascimento = LocalDateTime.ofInstant(Instant.ofEpochMilli(consulta.getLong("data_nascimento")), null);
                Usuario usuario = new Usuario(nome, dataNascimento, email);

                return usuario;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();    //printa se houve erro ao executar a busca
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_usuarios";   //comando que vai puxar todos cadastrados 
            PreparedStatement pstm = con.prepareStatement(sql);
            List<Usuario> listaUsuarios = new ArrayList<>();

            ResultSet consulta = pstm.executeQuery();   //guarda o resultado da busca realizada
            pstm.close();
            con.close();

            while(consulta.next()){
                String nome = consulta.getString("nome_usuario");
                LocalDateTime dataNascimento = LocalDateTime.ofInstant(Instant.ofEpochMilli(consulta.getLong("data_nascimento")), null);
                String email = consulta.getString("email_usuario");
                Usuario usuario = new Usuario(nome, dataNascimento, email);
                listaUsuarios.add(usuario);
 
            }
            return listaUsuarios;
            

        } catch (SQLException e) {
            e.printStackTrace();    //printa se houve erro ao executar a busca
        }
        return null;
    }

    @Override
    public Result editar(Usuario usuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result excluir(Usuario usuario) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); formatar data do tipo Date
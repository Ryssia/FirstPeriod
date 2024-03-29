package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

            if(this.buscar(usuario.getEmail()) != null){
                return Result.fail("Email já cadastrado!");
            }

            String sql = "INSERT INTO tb_usuarios(nome_usuario, email_usuario, data_nascimento) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            //TIMESTAMP [yyyy-MM-dd HH:mm:ss]
            pstm.setTimestamp(3, Timestamp.valueOf(usuario.getDataNascimento()));

            pstm.executeUpdate();   //executa a query no banco de dados
            pstm.close();       //fecha a conexao para salvar as alteraçoes
            con.close();    //fecha para evitar que fique com muitas conexoes abertas

            return Result.success("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            return Result.fail(e.getMessage()); 
                                  
        } catch(Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());

        }
        
    }

    @Override
    public Usuario buscar(String email) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_usuarios WHERE email_usuario = ?";   //comando que vai selecionar algum item da tabela 
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, email);

            ResultSet consulta = pstm.executeQuery();   //guarda o resultado da busca realizada

            //TipoDeRetorno nomeMetodo(TipoDeParametro nomeDoParametro) { implementacao }
            //nomeMetodo(nomeParametro : Tipo) : TipoRetorno

            //chamada de metodo
            // nomeDoMetodo() // Call

            if(consulta.next()){
                int id = consulta.getInt("id_usuario");
                String nome = consulta.getString("nome_usuario");
                LocalDateTime dataNascimento = consulta.getTimestamp("data_nascimento").toLocalDateTime();
                Usuario usuario = new Usuario(id, nome, dataNascimento, email);

                return usuario;
            }

            pstm.close();
            con.close();

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
            

            while(consulta.next()){ //resultSet.next() sempre navega para o primeiro do ResultSet
                //montar um objeto Usuario com base nos dados vindos da consulta do Banco de Dados
                int id = consulta.getInt("id_usuario");
                String nome = consulta.getString("nome_usuario");
                LocalDateTime dataNascimento = consulta.getTimestamp("data_nascimento").toLocalDateTime(); //Converter dados em Programação se chama "Casting"
                String email = consulta.getString("email_usuario");
                Usuario usuario = new Usuario(id, nome, dataNascimento, email);
                
                //Adiciona o Usuario montado na lista de usuários
                listaUsuarios.add(usuario);
            }
            
            pstm.close();
            con.close();

            return listaUsuarios;
            

        } catch (SQLException e) {
            e.printStackTrace();    //printa se houve erro ao executar a busca
        }
        return null;
    }

    @Override
    public Result editar(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "UPDATE tb_usuarios SET nome_usuario = ?, email_usuario = ?, data_nascimento = ? WHERE id_usuario = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setTimestamp(3, Timestamp.valueOf(usuario.getDataNascimento()));
            pstm.setInt(4, usuario.getId());

            pstm.executeUpdate();
            pstm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Result excluir(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "DELETE FROM tb_usuarios WHERE id_usuario = ?"; 
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setInt(1, usuario.getId());

            pstm.executeUpdate();
            pstm.close();
            con.close();

            return Result.success("Removido com sucesso!");

        } catch (Exception e) {
            return Result.fail("Erro ao remover usuário");
        }
    
    }

    @Override
    public Usuario logar(String email, LocalDateTime dataNascimento) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_usuarios WHERE email_usuario = ? AND data_nascimento = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, email);
            pstm.setTimestamp(2, Timestamp.valueOf(dataNascimento));
            
            ResultSet consulta = pstm.executeQuery();

            if(consulta.next()){
                int id = consulta.getInt("id_usuario");
                String nome = consulta.getString("nome_usuario");
                Usuario usuario = new Usuario(id, nome, dataNascimento, email);
                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); formatar data do tipo Date


//COMANDOS SQL
//INSERT - inserir
//SELECT - selecionar
//UPDATE - atualizar
//DELETE - remover
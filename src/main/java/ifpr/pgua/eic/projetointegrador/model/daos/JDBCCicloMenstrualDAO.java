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
import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCCicloMenstrualDAO implements CicloMenstrualDAO{

    private FabricaConexoes fabricaConexoes;

    public JDBCCicloMenstrualDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result cadastrar(CicloMenstrual cicloMenstrual) {
        try {
            boolean temFluxo = false;
            boolean temComentario = false;
            if(!(cicloMenstrual.getTipoFluxo() == null)){
                if(!cicloMenstrual.getTipoFluxo().isBlank()){
                    temFluxo = true;
                }
            }
            if(!(cicloMenstrual.getComentarios() == null)){
                if(!cicloMenstrual.getComentarios().isBlank())
                    temComentario = true;
            }

            Connection con = fabricaConexoes.getConnection();   //declaraçao e inicializaçao
            String sql = "INSERT INTO tb_ciclos_menstruais(id_usuario, data_inicio, data_termino) VALUES (?,?,?)";          //inserir dados básicos minimos

            
            if(temFluxo && temComentario)
                sql = "INSERT INTO tb_ciclos_menstruais(id_usuario, data_inicio, data_termino, tipo_fluxo, comentarios) VALUES (?,?,?,?,?)";        //inserindo se houver fluxo e comentario
            else if(temFluxo)
                sql = "INSERT INTO tb_ciclos_menstruais(id_usuario, data_inicio, data_termino, tipo_fluxo) VALUES (?,?,?,?)";   //inserindo se houver informaçao de fluxo
            else if(temComentario)
                sql = "INSERT INTO tb_ciclos_menstruais(id_usuario, data_inicio, data_termino, comentarios) VALUES (?,?,?,?)";      //inserindo se houver informaçao de comentario
            
                
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setInt(1, cicloMenstrual.getIdUsuario());
            pstm.setTimestamp(2, Timestamp.valueOf(cicloMenstrual.getDataInicio()));
            pstm.setTimestamp(3, Timestamp.valueOf(cicloMenstrual.getDataTermino()));

            if(temFluxo == true && temComentario == true){
                pstm.setString(4, cicloMenstrual.getTipoFluxo());
                pstm.setString(5, cicloMenstrual.getComentarios());
            }
            else if(temFluxo == true){
                pstm.setString(4, cicloMenstrual.getTipoFluxo());
            }
            else if(temComentario == true){
                pstm.setString(4, cicloMenstrual.getComentarios());
            }

            pstm.executeUpdate();
            pstm.close();
            con.close();
            
            return Result.success("Ciclo registrado!");

        } catch (SQLException e) {
            e.printStackTrace();
            return Result.fail(e.getMessage()); 

                                  
        } catch(Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());

        }

    }

    @Override
    public List<CicloMenstrual> listar(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_ciclos_menstruais WHERE id_usuario = ?";   //comando que vai puxar todos cadastrados 
            PreparedStatement pstm = con.prepareStatement(sql);
            List<CicloMenstrual> listaCiclos = new ArrayList<>();

            pstm.setInt(1, usuario.getId());

            ResultSet consulta = pstm.executeQuery();   //guarda o resultado da busca realizada

            while(consulta.next()){ 
                int idCiclo = consulta.getInt("id_ciclo_menstrual");
                int idUsuario = consulta.getInt("id_usuario");
                LocalDateTime dataInicio = consulta.getTimestamp("data_inicio").toLocalDateTime();
                LocalDateTime dataTermino = consulta.getTimestamp("data_termino").toLocalDateTime(); //Converter dados em Programação se chama "Casting"
                String tipoFluxo = consulta.getString("tipo_fluxo");
                String comentarios = consulta.getString("comentarios");
                
                CicloMenstrual cicloMenstrual = new CicloMenstrual(idCiclo, idUsuario, dataInicio, dataTermino, tipoFluxo, comentarios);

                listaCiclos.add(cicloMenstrual);
 
            }
               
            pstm.close();
            con.close();
            return listaCiclos;
            
            
        } catch (Exception e) {
            e.printStackTrace();      //mostrar erro no console
            return null;
        }
    }

    @Override
    public List<CicloMenstrual> listar(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataTermino) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_ciclos_menstruais WHERE id_usuario = ? "+
                        "AND data_inicio BETWEEN ? AND ? "+
                        "AND data_termino BETWEEN ? AND ? ";   //vai selecionar conforme as datas cadastradas
            

            PreparedStatement pstm = con.prepareStatement(sql);
            List<CicloMenstrual> listaCiclos = new ArrayList<>();

            pstm.setInt(1, usuario.getId());

            ResultSet consulta = pstm.executeQuery();   //guarda o resultado da busca realizada

            while(consulta.next()){ 
                int idCiclo = consulta.getInt("id_ciclo_menstrual");
                int idUsuario = consulta.getInt("id_usuario");
                LocalDateTime dataIni = consulta.getTimestamp("data_inicio").toLocalDateTime();
                LocalDateTime dataTer = consulta.getTimestamp("data_termino").toLocalDateTime(); //Converter dados em Programação se chama "Casting"
                String tipoFluxo = consulta.getString("tipo_fluxo");
                String comentarios = consulta.getString("comentarios");
                
                CicloMenstrual cicloMenstrual = new CicloMenstrual(idCiclo, idUsuario, dataIni, dataTer, tipoFluxo, comentarios);

                listaCiclos.add(cicloMenstrual);
 
            }
               
            pstm.close();
            con.close();
            return listaCiclos;
            
            
        } catch (Exception e) {
            e.printStackTrace();      //mostrar erro no console
            return null;
        }
    }

    @Override
    public Result editar(CicloMenstrual cicloMenstrual) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "UPDATE tb_ciclos_menstruais SET data_inicio = ?, "+
                        "data_termino = ?, tipo_fluxo = ?, comentarios = ? "+
                        "WHERE id_ciclo_menstrual = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setTimestamp(1, Timestamp.valueOf(cicloMenstrual.getDataInicio()));
            pstm.setTimestamp(2, Timestamp.valueOf(cicloMenstrual.getDataTermino()));
            pstm.setString(3, cicloMenstrual.getTipoFluxo());
            pstm.setString(4, cicloMenstrual.getComentarios());
            pstm.setInt(5, cicloMenstrual.getIdCicloMenstrual());

            pstm.executeUpdate();
            pstm.close();
            con.close();

            return Result.success("Dados editados!");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.success("Erro ao editar dados");
        }
    }

    @Override
    public Result excluir(CicloMenstrual cicloMenstrual) {
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "DELETE FROM tb_ciclos_menstruais WHERE id_ciclo_menstrual = ?"; 
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setInt(1, cicloMenstrual.getIdCicloMenstrual());

            pstm.executeUpdate();
            pstm.close();
            con.close();

            return Result.success("Removido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("Erro ao remover ciclo selecionado");
        }
    }
    
}

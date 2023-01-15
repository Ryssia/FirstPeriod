package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
            return Result.fail(e.getMessage()); 
                                  
        } catch(Exception e){
            return Result.fail(e.getMessage());

        }

    }

    @Override
    public List<CicloMenstrual> listar(Usuario usuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CicloMenstrual> listar(Usuario usuario, LocalDate dataInicio, LocalDate dataTermino) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result editar(CicloMenstrual cicloMenstrual) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result excluir(CicloMenstrual cicloMenstrual) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

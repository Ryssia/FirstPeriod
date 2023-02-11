package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;

public class JDBCInfoDAO {

    private FabricaConexoes fabricaConexoes;

    public JDBCInfoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    public List<String> consultaInfo(String categoria){
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_informacoes WHERE categoria = ? ";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, categoria);

            ResultSet consulta = pstm.executeQuery();
            List<String> informacoes = new ArrayList<>();

            while(consulta.next()){
                String info = consulta.getString("informacoes");
                informacoes.add(info);
            }
            return informacoes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> buscarTodos(){
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT * FROM tb_informacoes";

            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet consulta = pstm.executeQuery(sql);
            List<String> informacoes = new ArrayList<>();
            while(consulta.next()){
                informacoes.add(consulta.getString("informacoes"));
            }
            return informacoes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<String> buscarCategorias(){
        try {
            Connection con = fabricaConexoes.getConnection();
            String sql = "SELECT DISTINCT categoria FROM tb_informacoes";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet consulta = pstm.executeQuery(sql);
            List<String> categorias = new ArrayList<>();

            while(consulta.next()){
                categorias.add(consulta.getString("categoria"));
            }

            return categorias;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
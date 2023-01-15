package ifpr.pgua.eic.projetointegrador.model.daos;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface CicloMenstrualDAO {
    //crud
    Result cadastrar(CicloMenstrual cicloMenstrual);
    List<CicloMenstrual> listar(Usuario usuario);
    List<CicloMenstrual> listar(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataTermino);
    Result editar(CicloMenstrual cicloMenstrual);
    Result excluir(CicloMenstrual cicloMenstrual);
    
}

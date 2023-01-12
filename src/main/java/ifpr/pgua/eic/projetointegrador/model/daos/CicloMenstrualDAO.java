package ifpr.pgua.eic.projetointegrador.model.daos;

import java.time.LocalDate;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface CicloMenstrualDAO {
    //crud
    Result cadastrar(CicloMenstrual CicloMenstrual);
    List<CicloMenstrual> listar(Usuario usuario);
    List<CicloMenstrual> listar(Usuario usuario, LocalDate dataInicio, LocalDate dataTermino);
    Result editar(CicloMenstrual cicloMenstrual);
    Result excluir(CicloMenstrual cicloMenstrual);
    
}

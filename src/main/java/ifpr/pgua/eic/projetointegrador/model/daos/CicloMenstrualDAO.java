package ifpr.pgua.eic.projetointegrador.model.daos;

import java.time.LocalDate;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;

public interface CicloMenstrualDAO {
    //crud
    boolean cadastrar(CicloMenstrual CicloMenstrual);
    List<CicloMenstrual> listar(Usuario usuario);
    List<CicloMenstrual> listar(Usuario usuario, LocalDate dataInicio, LocalDate dataTermino);
    boolean editar(CicloMenstrual cicloMenstrual);
    boolean excluir(CicloMenstrual cicloMenstrual);
    
}

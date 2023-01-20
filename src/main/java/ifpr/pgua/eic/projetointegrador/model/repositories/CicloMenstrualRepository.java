package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.CicloMenstrualDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.CicloMenstrual;
import ifpr.pgua.eic.projetointegrador.model.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class CicloMenstrualRepository {

    private CicloMenstrualDAO dao;

    public CicloMenstrualRepository(CicloMenstrualDAO dao) {
        this.dao = dao;
    }

    public Result cadastrar(CicloMenstrual cicloMenstrual){
        if(cicloMenstrual.getDataInicio() == null || cicloMenstrual.getDataTermino() == null){
            return Result.fail("As datas não podem ser nulas!");
        }
        else if(!cicloMenstrual.getDataInicio().isBefore(cicloMenstrual.getDataTermino()) || !cicloMenstrual.getDataTermino().isAfter(cicloMenstrual.getDataInicio())){
            return Result.fail("A data de início não pode ser depois da data de término e a data de término não pode ser antes da data de início");
        }
        else{
            return dao.cadastrar(cicloMenstrual);
        }
    }

    public List<CicloMenstrual> listar(Usuario usuario){
        return dao.listar(usuario);
    }

    public List<CicloMenstrual> lista(Usuario usuario, LocalDateTime dataInicio, LocalDateTime dataTermino){
        return dao.listar(usuario, dataInicio, dataTermino);
    }

    public Result editar(CicloMenstrual cicloMenstrual){
        return dao.editar(cicloMenstrual);
    }

    public Result excluir(CicloMenstrual cicloMenstrual){
        return dao.excluir(cicloMenstrual);
    }
}

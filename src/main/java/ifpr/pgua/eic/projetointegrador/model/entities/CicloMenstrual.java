package ifpr.pgua.eic.projetointegrador.model.entities;

import java.time.LocalDate;

public class CicloMenstrual {
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String tipoFluxo;
    private String comentarios;
    
    public CicloMenstrual(LocalDate dataInicio, LocalDate dataTermino, String tipoFluxo, String comentarios) {
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipoFluxo = tipoFluxo;
        this.comentarios = comentarios;
    }

    public CicloMenstrual(LocalDate dataInicio, LocalDate dataTermino, String tipoFluxo) {
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipoFluxo = tipoFluxo;
    }
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataTermino() {
        return dataTermino;
    }
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }
    public String getTipoFluxo() {
        return tipoFluxo;
    }
    public void setTipoFluxo(String tipoFluxo) {
        this.tipoFluxo = tipoFluxo;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}

package ifpr.pgua.eic.projetointegrador.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CicloMenstrual {
    private int idCicloMenstrual;
    private int idUsuario;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String tipoFluxo;
    private String comentarios;
    

    
    public CicloMenstrual(int idCicloMenstrual, int idUsuario, LocalDateTime dataInicio, LocalDateTime dataTermino,
            String tipoFluxo, String comentarios) {
        this.idCicloMenstrual = idCicloMenstrual;
        this.idUsuario = idUsuario;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipoFluxo = tipoFluxo;
        this.comentarios = comentarios;
    }

    public CicloMenstrual( int idUsuario, LocalDateTime dataInicio, LocalDateTime dataTermino,
        String tipoFluxo, String comentarios) {
        this.idUsuario = idUsuario;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipoFluxo = tipoFluxo;
        this.comentarios = comentarios;
    }

    public int getIdCicloMenstrual() {
        return idCicloMenstrual;
    }

    public void setIdCicloMenstrual(int idCicloMenstrual) {
        this.idCicloMenstrual = idCicloMenstrual;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDateTime getDataTermino() {
        return dataTermino;
    }
    public void setDataTermino(LocalDateTime dataTermino) {
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

    public String toString(){
        return  " DATA INICIO " + this.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                " DATA TERMINO " + this.getDataTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                " TIPO FLUXO " + this.getTipoFluxo() + "\n" +
                " COMENTARIOS " + this.getComentarios();
        }
}

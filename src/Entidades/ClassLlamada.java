/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class ClassLlamada {
    private int idPersonaSalida;
    private int idPersonaLlegada;
    private Date inicioDia;
    private Time inicioHora;
    private Date finDia;
    private Time finHora;
    private String tipoLlamada;
    private double costoLlamada;
    
    public ClassLlamada(int idPersonaSalida,int idPersonaLlegada,Date inicioDia,Time inicioHora,Date finDia,Time finHora,String tipoLlamada,double costoLlamada){
        this.idPersonaLlegada = idPersonaLlegada;
        this.idPersonaSalida = idPersonaSalida;
        this.inicioDia = inicioDia;
        this.inicioHora = inicioHora;
        this.finDia = finDia;
        this.finHora = finHora;
        this.tipoLlamada = tipoLlamada;
        this.costoLlamada = costoLlamada;
    }
    /**
     * @return the idPersonaSalida
     */
    public int getIdPersonaSalida() {
        return idPersonaSalida;
    }

    /**
     * @param idPersonaSalida the idPersonaSalida to set
     */
    public void setIdPersonaSalida(int idPersonaSalida) {
        this.idPersonaSalida = idPersonaSalida;
    }

    /**
     * @return the idPersonaLlegada
     */
    public int getIdPersonaLlegada() {
        return idPersonaLlegada;
    }

    /**
     * @param idPersonaLlegada the idPersonaLlegada to set
     */
    public void setIdPersonaLlegada(int idPersonaLlegada) {
        this.idPersonaLlegada = idPersonaLlegada;
    }

    /**
     * @return the inicioDia
     */
    public Date getInicioDia() {
        return inicioDia;
    }

    /**
     * @param inicioDia the inicioDia to set
     */
    public void setInicioDia(Date inicioDia) {
        this.inicioDia = inicioDia;
    }

    /**
     * @return the inicioHora
     */
    public Time getInicioHora() {
        return inicioHora;
    }

    /**
     * @param inicioHora the inicioHora to set
     */
    public void setInicioHora(Time inicioHora) {
        this.inicioHora = inicioHora;
    }

    /**
     * @return the finDia
     */
    public Date getFinDia() {
        return finDia;
    }

    /**
     * @param finDia the finDia to set
     */
    public void setFinDia(Date finDia) {
        this.finDia = finDia;
    }

    /**
     * @return the finHora
     */
    public Time getFinHora() {
        return finHora;
    }

    /**
     * @param finHora the finHora to set
     */
    public void setFinHora(Time finHora) {
        this.finHora = finHora;
    }

    /**
     * @return the tipoLlamada
     */
    public String getTipoLlamada() {
        return tipoLlamada;
    }

    /**
     * @param tipoLlamada the tipoLlamada to set
     */
    public void setTipoLlamada(String tipoLlamada) {
        this.tipoLlamada = tipoLlamada;
    }

    /**
     * @return the costoLlamada
     */
    public double getCostoLlamada() {
        return costoLlamada;
    }

    /**
     * @param costoLlamada the costoLlamada to set
     */
    public void setCostoLlamada(double costoLlamada) {
        this.costoLlamada = costoLlamada;
    }
}

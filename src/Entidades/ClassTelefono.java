/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Asus
 */
public class ClassTelefono {
     private int idPersona;
    private int numeroTelefono;
    private boolean esCelular;
    public ClassTelefono(int idPersona,int numeroTelefono,boolean esCelular){
        this.idPersona = idPersona;
        this.numeroTelefono = numeroTelefono;
        this.esCelular = esCelular;
    }

    /**
     * @return the idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the numeroTelefono
     */
    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * @param numeroTelefono the numeroTelefono to set
     */
    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * @return the esCelular
     */
    public boolean isEsCelular() {
        return esCelular;
    }

    /**
     * @param esCelular the esCelular to set
     */
    public void setEsCelular(boolean esCelular) {
        this.esCelular = esCelular;
    }
}

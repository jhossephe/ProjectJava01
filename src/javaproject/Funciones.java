/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javaproject.Funciones;
import java.sql.Statement;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Entidades.ClassPersona;
import Entidades.ClassTelefono;
import Entidades.ClassLlamada;
 /*
 * @author Asus
 */
public class Funciones {
    private static Conexion conexion;
    private Connection con;
    public String select()
    {
        String letras = "ID Llegada\tID Salida\tEMAIL Llegada\tEMAIL Salida\tINICIO LLAMADA\tFIN LLAMADA\tTIPO LLAMADA\tCOSTO DE LLAMADA\n";
        try {
            int id;
            int id2;
            String email;
            String email2;
            String inicio;
            String fin;
            String tipo;
            double costo;
            String res;
            String sql = "SELECT\n"
                        +"nuevadb.p1.IDPersona,\n"
                        +"nuevadb.p2.IDPersona,\n"
                        +"nuevadb.p1.email,\n"
                        +"nuevadb.p2.email,\n"
                        +"nuevadb.llamada.inicioLlamada,\n"
                        +"nuevadb.llamada.finLlamada,\n"
                        +"nuevadb.llamada.tipoLlamada,\n"
                        +"nuevadb.llamada.costoTotal\n"
                        +"FROM\n" 
                        +"nuevadb.persona p1,\n"
                        +"nuevadb.persona p2,\n"
                        +"nuevadb.llamada\n"
                        +"WHERE\n"
                        +"nuevadb.p1.IDPersona = nuevadb.llamada.IDPersonaLlegada AND nuevadb.p2.IDPersona = nuevadb.llamada.IDPersonaSalida";
                    con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next())
                    {   
                        id = rs.getInt(1);
                        id2 = rs.getInt(2);
                        email = rs.getString(3);
                        email2 = rs.getString(4);
                        inicio = rs.getString(5);
                        fin = rs.getString(6);
                        tipo = rs.getString(7);
                        costo = rs.getDouble(8);
                        letras += id+"\t"+id2+"\t"+email+"\t"+email2+"\t"+inicio+"\t"+fin+"\t"+tipo+"\t"+costo+"\n";
                    }
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
       return letras;
    }
    
    public DefaultTableModel select2()
    {
        ResultSet variablex = null;
        DefaultTableModel modelo = new DefaultTableModel();
        String letras = "ID\tNOMBRE\tEDAD\tTELEFONO\n";
        try {
            
            String sql = "SELECT\n"
                        +"nuevadb.p1.email,\n"
                        +"nuevadb.p2.email,\n"
                        +"nuevadb.llamada.inicioLlamada,\n"
                        +"nuevadb.llamada.finLlamada,\n"
                        +"nuevadb.llamada.tipoLlamada,\n"
                        +"nuevadb.llamada.costoTotal\n"
                        +"FROM\n" 
                        +"nuevadb.persona p1,\n"
                        +"nuevadb.persona p2,\n"
                        +"nuevadb.llamada\n"
                        +"WHERE\n"
                        +"nuevadb.p1.IDPersona = nuevadb.llamada.IDPersonaLlegada AND nuevadb.p2.IDPersona = nuevadb.llamada.IDPersonaSalida";
                   
                    con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    variablex = st.executeQuery(sql);
                   
                    modelo.setColumnIdentifiers(new Object[]{"Email Llegada","Email Salida", "Inicio Llamada", "Fin Llamada", "Tipo Llamada", "Costo llamada"});
                    try{
                        while(variablex.next()){
                            modelo.addRow(new Object[]{variablex.getString(1),variablex.getString(2),variablex.getString(3),variablex.getString(4),variablex.getString(5),variablex.getString(6)});
                        }   
                    } catch(Exception e){

                    }
                   
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
       return modelo;
    }
    
    public void insertarPersona(ClassPersona p){
    try {
        String sql = "INSERT INTO persona(IDPersona,Email)\n"
                + "VALUES (NULL , ?)";
                con = conexion.getInstance().getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setInt(1, p.getIdPersona());
                pstm.setString(2, p.getEmail());
                pstm.executeUpdate();
                pstm.close();
                
                con.commit();
                con.close();
        } catch (SQLException ex) {
        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarLlamada(ClassLlamada y){
    try {
        String sql = "INSERT INTO llamada(IDPersonaLlegada,IDPersonaSalida,tipoLlamada,costoTotal,inicioLlamada,finLlamada)\n"
                + "VALUES (?, ?, ?, ?, ?, ?)";
                con = conexion.getInstance().getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setInt(1, y.getIdPersonaLlegada());
                pstm.setInt(2, y.getIdPersonaSalida());
                pstm.setDate(3, y.getInicioDia());
                pstm.setTime(4, y.getInicioHora());
                pstm.setDate(5, y.getFinDia());
                pstm.setTime(6, y.getFinHora());
                pstm.setString(7, y.getTipoLlamada());
                pstm.setDouble(8, y.getCostoLlamada());
                pstm.executeUpdate();
                pstm.close();
                
                con.commit();
                con.close();
        } catch (SQLException ex) {
        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarTelefono(ClassTelefono t){
    try {
        String sql = "INSERT INTO telefono(IDPersona,numeroDeTelefono,esCelular)\n"
                + "VALUES (?, ?, ?)";
                con = conexion.getInstance().getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setInt(1, t.getIdPersona());
                pstm.setInt(2, t.getNumeroTelefono());
                pstm.setBoolean(3, t.isEsCelular());
                pstm.executeUpdate();
                pstm.close();
                
                con.commit();
                con.close();
        } catch (SQLException ex) {
        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

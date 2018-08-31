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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 /*
 * @author Asus
 */
public class Funciones {
    private static Conexion conexion;
    private Connection con;
    
    public DefaultTableModel selectLlamada()
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
    
    public DefaultTableModel selectUser()
    {
        ResultSet variablex = null;
        DefaultTableModel modelo = new DefaultTableModel();
        String letras = "ID\tNOMBRE\tEDAD\tTELEFONO\n";
        try {
            
            String sql = "SELECT\n"
                        +"nuevadb.persona.IDPersona,\n"
                        +"nuevadb.persona.Email\n"
                        +"FROM\n" 
                        +"nuevadb.persona\n";
                   
                    con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    variablex = st.executeQuery(sql);
                   
                    modelo.setColumnIdentifiers(new Object[]{"ID Persona","Email"});
                    try{
                        while(variablex.next()){
                            modelo.addRow(new Object[]{variablex.getString(1),variablex.getString(2)});
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
                pstm.setString(1, p.getEmail());
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
    
    public int[] Estadisticas(){
        int[] aux= new int[5];
            int cont1=0,cont2=0,cont3=0;
        try {
            String sql = "SELECT\n"
                        +"nuevadb.llamada.tipoLlamada\n"
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
                        if(rs.getString(1).equals("Local")) {
                            cont1++;
                        }
                        else
                        {
                            if(rs.getString(1).equals("Internacional")){
                                cont2++;
                            }
                            else{
                                if(rs.getString(1).equals("Celular")){
                                    cont3++;
                                }
                            }
                        }
                    }
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        aux[0]=cont1;
        aux[1]=cont2;
        aux[2]=cont3;
        return aux;
    }
    
    public void excelUsuarios(){
        String nombreArchivo="Usuarios.xlsx";
        String rutaArchivo= "C:\\Users\\LENOVO\\Downloads\\Ficherso Excel\\"+nombreArchivo;
        String hoja="Hoja1";
        String [][] document= new String [200][200];

        XSSFWorkbook libro= new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        //cabecera de la hoja de excel
        String [] header= new String[]{"IDPersona", "Email","Telefono","Es Celular:"};
        try {
            
            String sql =    "SELECT\n"
                            +"nuevadb.persona.IDPersona,\n"
                            +"nuevadb.persona.Email,\n"
                            +"nuevadb.telefono.numeroDeTelefono,\n"
                            +"nuevadb.telefono.esCelular\n"
                            +"FROM\n"
                            +"nuevadb.persona,\n"
                            +"nuevadb.telefono\n"
                            +"WHERE\n"
                            +"persona.IDPersona = telefono.IDPersona\n";
                        con = conexion.getInstance().getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        int i=0;
                        try{
                            while(rs.next())
                            {
                                document[i][0]=rs.getString(1);
                                document[i][1]=rs.getString(2);
                                document[i][2]=rs.getString(3);
                                document[i][3]=rs.getString(4);
                                i++;
                            }
                        }
                        catch(SQLException ex){}
                        //poner negrita a la cabecera
                            CellStyle style = libro.createCellStyle();
                            Font font = libro.createFont();
                            font.setBold(true);
                            style.setFont(font);
                        //generar los datos para el documento
                          for ( i = 0; i <= document.length; i++) {
                            XSSFRow row=hoja1.createRow(i);//se crea las filas
                            for (int j = 0; j <header.length; j++) {
                              if (i==0) {//para la cabecera
                                  XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                  cell.setCellStyle(style); // se añade el style crea anteriormente 
                                  cell.setCellValue(header[j]);//se añade el contenido          
                              }else{//para el contenido
                                XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                cell.setCellValue(document[i-1][j]); //se añade el contenido
                              }       
                            }
                          }
 
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File file;
                        file = new File(rutaArchivo);
                        try (FileOutputStream fileOuS = new FileOutputStream(file)){            
                          if (file.exists()) {// si el archivo existe se elimina
                            file.delete();
                            System.out.println("Archivo eliminado");
                          }
                          libro.write(fileOuS);
                          fileOuS.flush();
                          fileOuS.close();
                          System.out.println("Archivo Creado");

                        } catch (FileNotFoundException e) {
                          e.printStackTrace();
                        }catch (IOException e) {
                          e.printStackTrace();
                        }
    
    }
    
    public void excelllamada(){
        String nombreArchivo="llamadas.xlsx";
        String rutaArchivo= "C:\\Users\\LENOVO\\Downloads\\Ficherso Excel\\"+nombreArchivo;
        String hoja="Hoja1";
        String [][] document= new String [200][200];

        XSSFWorkbook libro= new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        //cabecera de la hoja de excel
        String [] header= new String[]{"Email Llegada", "Email Salida","Inicio Llamada","Fin Llamada","Tipo Llamada","Costo Llamada"};
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
                        ResultSet rs = st.executeQuery(sql);
                        int i=0;
                        try{
                            while(rs.next())
                            {
                                document[i][0]=rs.getString(1);
                                document[i][1]=rs.getString(2);
                                document[i][2]=rs.getString(3);
                                document[i][3]=rs.getString(4);
                                document[i][4]=rs.getString(5);
                                document[i][5]=rs.getString(6);
                                i++;
                            }
                        }
                        catch(SQLException ex){}
                        //poner negrita a la cabecera
                            CellStyle style = libro.createCellStyle();
                            Font font = libro.createFont();
                            font.setBold(true);
                            style.setFont(font);
                        //generar los datos para el documento
                          for ( i = 0; i <= document.length; i++) {
                            XSSFRow row=hoja1.createRow(i);//se crea las filas
                            for (int j = 0; j <header.length; j++) {
                              if (i==0) {//para la cabecera
                                  XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                                  cell.setCellStyle(style); // se añade el style crea anteriormente 
                                  cell.setCellValue(header[j]);//se añade el contenido          
                              }else{//para el contenido
                                XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                                cell.setCellValue(document[i-1][j]); //se añade el contenido
                              }       
                            }
                          }
 
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File file;
                        file = new File(rutaArchivo);
                        try (FileOutputStream fileOuS = new FileOutputStream(file)){            
                          if (file.exists()) {// si el archivo existe se elimina
                            file.delete();
                            System.out.println("Archivo eliminado");
                          }
                          libro.write(fileOuS);
                          fileOuS.flush();
                          fileOuS.close();
                          System.out.println("Archivo Creado");

                        } catch (FileNotFoundException e) {
                          e.printStackTrace();
                        }catch (IOException e) {
                          e.printStackTrace();
                        }
    
    }
}

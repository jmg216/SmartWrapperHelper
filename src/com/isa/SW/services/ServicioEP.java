/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.services;

import com.isa.SW.exceptions.SWException;
import com.isa.SW.utils.UtilesSWHelper;
import com.isa.SW.utils.XMLServiceGenerator;
import com.safelayer.trustedx.client.smartwrapper.SmartCountRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartCountResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartDeleteRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartDeleteResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartInsertRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartInsertResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartReadRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartReadResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartSearchRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartSearchResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartUpdateRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartUpdateResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.axis.AxisFault;

/**
 *
 * @author JMiraballes
 * 
 * Clase encargada de encaspular todos los llamados al los 
 * servicios EP-EntityProfiler, utilizando el api SmartWrapper.
 * 
 */
public class ServicioEP {
    
    /**
     * Método que inserta una entidad en trustedX.
     * @param artifact
     * @param xPath
     * @param data
     * @throws SWException
     */
    public void insert( String artifact, String xPath, String data ) throws SWException{
        try{
            SmartInsertRequest iReq = new SmartInsertRequest( UtilesSWHelper.getURLTrustedX() );
            
            iReq.setHeader( UtilesSWHelper.crearSmartHeader( artifact ));
            
            iReq.setData(data);
            iReq.setXPath(xPath);
            
            SmartInsertResponse iRes = iReq.send();
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(iRes.getResult())){
                System.out.println(UtilesSWHelper.OPERATION_OK);    	
            }
            else{
                System.out.println(UtilesSWHelper.OPERACION_FALLIDA);
                throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_EP_INSERT, iRes.getResult()); 
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar verificar: firma PKCS#7");  
        }         
        catch(Exception ex){
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: insertar entidad", ""); 
        }
    }
    
    
    
    
    /**
     * Método que consulta una entidad en trustedX. Si existe devuelve 
     * true sino devuelve false.
     * 
     * @param artifact
     * @param xPath
     * @return boolean
     * @throws SWException
     */
    public boolean existe(String artifact, String xPath ) throws SWException{
        
        try{
            SmartSearchRequest sReq = new SmartSearchRequest( UtilesSWHelper.getURLTrustedX() );
            
            sReq.setHeader(UtilesSWHelper.crearSmartHeader(artifact)); 
            sReq.setXPath(xPath);

            SmartSearchResponse sRes = sReq.send();
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(sRes.getResult())) {
                System.out.println( UtilesSWHelper.OPERATION_OK ); 
                System.out.println( "Entidad existente: " + sRes.getData()); 
                return sRes.getData();
            } 
            else {
               throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_EP_INSERT, sRes.getResult());    
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            ex.printStackTrace();
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al buscar una entidad.");  
        }        
        catch(Exception ex){
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al buscar una entidad.");         
        }
    }   
    

    /**
     * Método que devuelve la cantidad de entidades existentes en trustedX.
     * @param artifact
     * @param xPath
     * @return int
     * @throws SWException
     * 
     */
    public int count(String artifact, String xPath ) throws SWException{
        try{
            SmartCountRequest cReq = new SmartCountRequest( UtilesSWHelper.getURLTrustedX() );

            cReq.setHeader(UtilesSWHelper.crearSmartHeader( artifact )); 
            cReq.setXPath(xPath);        

            SmartCountResponse cRes = cReq.send();

            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(cRes.getResult())) {
                System.out.println(UtilesSWHelper.OPERATION_OK); 
                return cRes.getData(); 
            } 
            else{
                throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_EP_COUNT, cRes.getResult());                 
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error contando entidades.");  
        }        
        catch(Exception ex){ 
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error contando entidades.");           
        }
    }
    
    public boolean update(String artifact, String xPath, String data) throws SWException{
        
        try{
            SmartUpdateRequest sReq = new SmartUpdateRequest( UtilesSWHelper.getURLTrustedX() );
            
            sReq.setHeader( UtilesSWHelper.crearSmartHeader( artifact )); 
            sReq.setXPath(xPath); 
            sReq.setData(data);
            
            SmartUpdateResponse uRes = sReq.send();

            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(uRes.getResult())){
                System.out.println(" Operation OK");
                return true;                 
            }
            else {
               return false;
            }
        }
        catch(AxisFault ex){
            ex.printStackTrace();
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error actualizando una entidad.");  
        }        
        catch(Exception ex){ 
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error actualizando una entidad");           
        }        
    }
    
    public String read( String artifact, String xPath ) throws SWException{
        try{
            SmartReadRequest rReq = new SmartReadRequest( UtilesSWHelper.getURLTrustedX() ); 
            rReq.setHeader(UtilesSWHelper.crearSmartHeader( artifact )); 
            
            rReq.setXPath(xPath); 
            SmartReadResponse rRes = rReq.send(); 
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(rRes.getResult()) && 
                    rRes.getData() != null) { 
                System.out.println(" Read OK");
                return rRes.getData(); 
            }
            throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_EP_READ, rRes.getResult());         
        }
        catch(AxisFault ex){
            ex.printStackTrace();
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error actualizando una entidad.");  
        }        
        catch(Exception ex){ 
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error actualizando una entidad");           
        }        
    }
    
    public void eliminar(String artifact, String xPath ) throws SWException{
        try{
            SmartDeleteRequest rReq = new SmartDeleteRequest( UtilesSWHelper.getURLTrustedX() ); 
            rReq.setHeader(UtilesSWHelper.crearSmartHeader( artifact )); 
            
            rReq.setXPath(xPath); 
            SmartDeleteResponse rRes = rReq.send(); 
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(rRes.getResult())) { 
                System.out.println(" Eliminar OK");
            }
            else throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_EP_READ, rRes.getResult());                     
        
        }
        catch(AxisFault ex){
            ex.printStackTrace();
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error eliminado una entidad.");  
        }        
        catch(Exception ex){ 
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error eliminando una entidad");           
        }   
    }
    
    /**
     * Método para modificar el password de un usuario.
     * @param artifact
     * @param dn
     * @param password
     * @return 
     * @throws com.isa.SW.exceptions.SWException 
     */
    
    public boolean modificarPassword (String artifact, String dn, String password ) throws SWException{
        String xPath = XMLServiceGenerator.XPATH_USER + "/User[@dname='"+ dn +"']";
        String usuarioOriginal = read(artifact, xPath);
        String nuevoXmlUsuario = XMLServiceGenerator.actualizarUsuario(usuarioOriginal, XMLServiceGenerator.TAG_USUARIO_PASSWD, password);
        return update(artifact, xPath, nuevoXmlUsuario );
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.services;

import com.isa.SW.exceptions.SWException;
import com.isa.SW.utils.UtilesResources;
import com.isa.SW.utils.UtilesSWHelper;
import com.safelayer.trustedx.client.smartwrapper.Constants;
import com.safelayer.trustedx.client.smartwrapper.SmartAttributeQueryRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartAttributeQueryResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartAuthNRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartAuthNResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartHeader;
import com.safelayer.trustedx.client.smartwrapper.SmartLogoutRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartLogoutResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.axis.AxisFault;
import org.w3c.dom.Document;

/**
 *
 * @author JMiraballes
 */
public class ServicioAA {
    
    public static final String LOGIN_PARAM_METHOD = "urn:oasis:names:tc:SAML:1.0:am:password:clear";
    public static final String LOGIN_PARAM_POLICIY = "swHelperConfig.loginPolicy";
    public static final String LOGIN_USER_ADMIN = "swHelperConfig.adminUser";
    public static final String LOGIN_PASS_ADMIN = "swHelperConfig.adminPass";
    public static final String LOGIN_PARA_REQUEST_TYPE = "direct";
    
    
    /**
     * Login de un usuario en trustedX
     * @param user
     * @param password
     * @return 
     * @throws com.isa.SW.exceptions.SWException 
     */
    public String login( String user, String password ) throws SWException {
        try{
            SmartAuthNRequest aReq = new SmartAuthNRequest(UtilesSWHelper.getURLTrustedX() );
            aReq.setHeader( UtilesSWHelper.crearSmartHeader(user, password));
            aReq.setEntityUsername(user);
            aReq.setUsernameTokenUsername( user );
            aReq.setUsernameTokenPassword( password );
            aReq.setPolicy( UtilesResources.getProperty(LOGIN_PARAM_POLICIY));
            aReq.setMethod( LOGIN_PARAM_METHOD );
            aReq.setRequestType( LOGIN_PARA_REQUEST_TYPE );  
           
            aReq.setRespondWith( Constants.AA.RespondWith.ASSERTIONARTIFACT );
            SmartAuthNResponse aResp = aReq.send();
            String artifact = aResp.getAssertionArtifact();
            System.out.println("Login: " + artifact);
            System.out.println("Usuario: " + user);
            return artifact;
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                ex.printStackTrace();
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);
            }
            ex.printStackTrace();
            throw new SWException(SWException.ERROR_EN_SERVICIO, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);
        }         
        catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: login", ""); 
        }            
    }    
    
    
    /**
     * Logout de usuario en TrustedX
     * @param usuario
     * @param assertion
     * @throws com.isa.SW.exceptions.SWException
     */
    public void logut( String usuario, String assertion ) throws SWException{
        try{
            System.out.println("Logout: " + assertion);
            System.out.println("Usuario: " + usuario);
            SmartLogoutRequest iReq = new SmartLogoutRequest( UtilesSWHelper.getURLTrustedX() );
            iReq.setHeader( UtilesSWHelper.crearSmartHeader(assertion) );
            iReq.setEntityUsername(usuario);
            iReq.setAssertionArtifact(assertion);
            
            SmartLogoutResponse iRes = iReq.send();
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(iRes.getResult())){
                System.out.println(UtilesSWHelper.OPERATION_OK);    	
            }
            else{
                System.out.println(UtilesSWHelper.OPERACION_FALLIDA);
                throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_AA_LOGOUT, iRes.getResult()); 
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                ex.printStackTrace();
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            ex.printStackTrace();
        }         
        catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud de logout", ""); 
        }
    } 
    
    public String queryServiceAA( String usuario, String password ) throws SWException{
        try{
            SmartAttributeQueryRequest aqReq = new SmartAttributeQueryRequest(UtilesSWHelper.getURLTrustedX() ); 
            SmartHeader header = UtilesSWHelper.crearSmartHeader( usuario, password );
            aqReq.setHeader(header);    
            aqReq.setSubjectUsername(usuario);
            Document doc = null;
            
            SmartAttributeQueryResponse aqRes = aqReq.send(); 
            if ( "Success".equals(aqRes.getStatus()) ){ 
                System.out.println("Operation processed correctly.");
                System.out.println("Assertion attribute retrieved: "+ aqRes.getAssertionAttribute()); 
                return aqRes.getAssertionAttribute();
            }
            else
            {
                System.out.println("Error");
                return "NULL";
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            ex.printStackTrace();
            throw new SWException(SWException.ERROR_EN_SERVICIO, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);
        }         
        catch(Exception ex){
            ex.printStackTrace();
            Logger.getLogger(ServicioEP.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: insertar entidad", ""); 
        }
    }
    
    
}

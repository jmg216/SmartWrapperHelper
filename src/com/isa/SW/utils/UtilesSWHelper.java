/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.utils;

import com.safelayer.trustedx.client.smartwrapper.SmartHeader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author JMiraballes
 */
public class UtilesSWHelper {
        
    public static final String RESULTMAJOR_SUCCESS = "success";
    public static final String RESULTMAJOR_SUCCES_FIRMA = "urn:oasis:names:tc:dss:1.0:resultmajor:Success";
    public static final String RESULTMAJOR_SUCCESS_KM = "http://www.w3.org/2002/03/xkms#Success";
    public static final String SERVICE_POLICY_KM = "urn:safelayer:km:politica";
    public static final String SERVICE_POLICY_DS = "urn:safelayer:tws:policies:generation:igdoc";
    public static final String STATUS_VALID_KM = "http://www.w3.org/2002/03/xkms#Valid";
    public static final String RESULTMINOR_SUCCESS_VERIFY = "urn:oasis:names:tc:dss:1.0:resultminor:ValidSignature_OnAllDocuments";
    public static final String RESULTMINOR_SUCCESS_VERIFY_MULTISIGN_PDF = "urn:oasis:names:tc:dss:1.0:resultminor:ValidSignature_RevisionsInSomeDocuments";
    
    //Mensajes
    public static final String ERROR_AUTENTICACION_MSJ = "Se ha producido un error al autenticar el usuario.";
    public static final String ERROR_SERVICIO_FIRMA = "Se ha producido un error mientras se validaba una firma.";
    public static final String ERROR_SERVICIO_KM_PKCS12 = "Se ha producido un error mientras se insertaba un certificado.";
    public static final String ERROR_SERVICIO_KM_X509 = "Se ha producido un error mientras se generaba el certificado.";
    public static final String ERROR_SERVICIO_KM_PKCS10 = "Se ha producido un error mientras se generaba la clave pkcs10.";
    public static final String ERROR_SERVICIO_KM_INSERT_CERT = "Se ha producido un error mientras se insertaba un certificado.";
    
    public static final String ERROR_SERVICIO_EP_INSERT = "Se ha producido un error al insertar una entidad en EP.";
    public static final String ERROR_SERVICIO_EP_BUSCAR = "Se ha producido un error al buscar una entidad en EP.";
    public static final String ERROR_SERVICIO_EP_COUNT = "Se ha producido un error al contar entidades en EP.";
    public static final String ERROR_SERVICIO_EP_READ = "Se ha producido un error al leer entidad en EP.";
    
    public static final String ERROR_SERVICIO_AA_LOGOUT = "Se ha producido un error al autenticar el Usuario.";
    
    public static final String OPERATION_OK = "Operación Correcta.";
    public static final String OPERACION_FALLIDA = "Operación Fallida.";
    
    //Contraseña y password de usuario consumidor de ws.
    public static final String ADMIN_USUARIO = "super";
    public static final String ADMIN_PASSWORD = "super";
    
    public static final String ADMIN_VERIFY_USUARIO = "trustedx";
    public static final String ADMIN_VERIFY_PASSWORD = "trustedx";    
    
    private static URL codeBase;    
    
    public static void setCodeBase( URL codebase ){
        codeBase = codebase;
    }
    
    public static URL getCodeBase(){
        return codeBase;
    }
    
    public static boolean isNullOrEmpty(String str){
        return (str == null || str.isEmpty());
    }
    
    
    /**
     * Crea la cabecera del servicio de smartwrapper por usuario y contraseña.
     * @param usuario
     * @param password
     * @return SmartHeader
     * @throws java.lang.Exception 
    **/
    public static SmartHeader crearSmartHeader(String usuario, String password) throws Exception{
        
        SmartHeader smartHeader = new SmartHeader();
        smartHeader.setUsername( usuario );
        smartHeader.setPassword( password );
        
        return smartHeader;
    }
    
    /**
     * 
     * @param artifact
     * @return 
     * @throws java.lang.Exception
     * Obtiene la aserción SMAL
     */
    public static SmartHeader crearSmartHeader( String artifact ) throws Exception{
        SmartHeader header = new SmartHeader(); 
        header.setAssertionArtifact(artifact);
        return header;
    }
    
    public static String getURLTrustedX() throws IOException{
        return UtilesResources.getProperty("swHelperConfig.trustexURL");
    }
    
    public static String getAdminUsuario() throws IOException{
        
        if (isAdminFromProperties()){
            return UtilesResources.getProperty("swHelperConfig.adminUser");
        }
        else{
            return ADMIN_USUARIO;
        }
    }
    
    public static String getAdminPassword() throws IOException{
        
        if (isAdminFromProperties()){
            return UtilesResources.getProperty("swHelperConfig.adminPass");
        }
        else{
            return ADMIN_PASSWORD;
        }
    }    public static String getUserVerify() throws IOException{
        
        if (isAdminFromProperties()){
            return UtilesResources.getProperty("swHelperConfig.userVerify");
        }
        else{
            return ADMIN_VERIFY_USUARIO;
        }
    }
    
    public static String getPasswdVerify() throws IOException{
        
        if (isAdminFromProperties()){
            return UtilesResources.getProperty("swHelperConfig.passVerify");
        }
        else{
            return ADMIN_VERIFY_PASSWORD;
        }
    }
    
    
    
    public static boolean isAdminFromProperties() throws IOException{
        String adminFromProperties = UtilesResources.getProperty("swHelperConfig.adminFromProperties");
        return adminFromProperties.equals("true");
    }
}

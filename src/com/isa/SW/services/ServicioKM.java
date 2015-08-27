/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.services;

import com.isa.SW.exceptions.SWException;
import com.isa.SW.utils.UtilesSWHelper;
import com.safelayer.trustedx.client.smartwrapper.Constants;
import com.safelayer.trustedx.client.smartwrapper.SmartHeader;
import com.safelayer.trustedx.client.smartwrapper.SmartRegisterRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartRegisterResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JMiraballes
 */
public class ServicioKM {
    
    
    
    public void insertarContenedorPKCS12( String usuario, String password, String dn, String certRoot, String footPrint, String dataPKCS12, String pkPass ) throws SWException{
        
        try {
            SmartRegisterRequest rReq = new SmartRegisterRequest( UtilesSWHelper.getURLTrustedX() );
            SmartHeader header = UtilesSWHelper.crearSmartHeader(usuario, password);
            
            rReq.setHeader(header);
            
            rReq.setClaimedIdentity(dn);
            rReq.setServicePolicy(UtilesSWHelper.SERVICE_POLICY_KM);
            
            if (!UtilesSWHelper.isNullOrEmpty(certRoot)){
                rReq.setRootFingerPrint(footPrint);
                rReq.setRootCertificate( certRoot );
            }
            
            rReq.setPkcs12Data(dataPKCS12);
            rReq.setAuthenticationValue(pkPass);
            
            SmartRegisterResponse rRes = rReq.send();
            String strr = rRes.getPkcs10Data();
            
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS_KM.equals(rRes.getResultMajor()) && 
                    rRes.getResultMinor() == null && 
                        UtilesSWHelper.STATUS_VALID_KM.equals(rRes.getStatusValue())){
                System.out.print("Se ha insertado correctamente un certificado.");
            }
            else{
                throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_KM_PKCS12, rRes.getStatusValue());                 
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: insertando PKCS12");             
        }
    }
    
    
    public String generar509Certificado( String usuario, String password, String signerDN ) throws SWException {
        String cert;
        try {
            SmartRegisterRequest rReq = new SmartRegisterRequest( UtilesSWHelper.getURLTrustedX() ); 
            SmartHeader header = UtilesSWHelper.crearSmartHeader(usuario, password);
            rReq.setHeader(header); 
            rReq.setClaimedIdentity( signerDN ); 
            rReq.setServicePolicy( UtilesSWHelper.SERVICE_POLICY_KM ); 
            rReq.setRespondWith(Constants.KM.RespondWith.X509CERT); 
            rReq.setKeyUsage(Constants.KM.KeyUsage.SIGNATURE); 
            SmartRegisterResponse rRes = rReq.send(); 
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS_KM.equals(rRes.getResultMajor()) && 
                    rRes.getResultMinor() == null && 
                        UtilesSWHelper.STATUS_VALID_KM.equals(rRes.getStatusValue()) && 
                            rRes.getX509Certificate() != null) { 
                System.out.println("X509 Certificate retrieved"); 
                System.out.println(" Certificate: "+ rRes.getX509Certificate());
                cert = rRes.getX509Certificate();
            } 
            else throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_KM_X509, rRes.getResultMajor());           
        } 
        catch (Exception e) {
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, e);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: generar X509 certificado", "");             
        }
        return cert;
    }
    
    /**
     * Genera un par de claves pkcs10 de clave publica.
     * @param artifact
     * @param signerDN
     * @return String
     * @throws com.isa.SW.exceptions.SWException
     * 
     */
    public String generarPKCS10(String artifact, String signerDN ) throws SWException{
        
        String cert = "";
        try {
            SmartRegisterRequest rReq = new SmartRegisterRequest( UtilesSWHelper.getURLTrustedX() );
            SmartHeader header = UtilesSWHelper.crearSmartHeader( artifact );
            
            rReq.setHeader( header );
            rReq.setClaimedIdentity( signerDN ); 
            rReq.setServicePolicy(UtilesSWHelper.SERVICE_POLICY_KM); 
            rReq.setRespondWith(Constants.KM.RespondWith.PKCS10); 
            rReq.setKeyUsage( Constants.KM.KeyUsage.SIGNATURE );
            
            SmartRegisterResponse rRes = rReq.send();
            
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS_KM.equals(rRes.getResultMajor()) && 
                    rRes.getResultMinor() == null && 
                        UtilesSWHelper.STATUS_VALID_KM.equals(rRes.getStatusValue()) && 
                            rRes.getPkcs10Data() != null) {
                cert = rRes.getPkcs10Data();
                System.out.println("PKCS10 retrieved: " + cert);
            }      
            else throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_KM_PKCS10, rRes.getResultMajor()); 
        } 
        catch (Exception ex) {
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: generando pkcs10"); 
        }
        return cert;
    }
    
    
    public String generarX509( String usuario, String password, String signerDN ) throws SWException{
        String cert = "";
        try {
            SmartRegisterRequest rReq = new SmartRegisterRequest( UtilesSWHelper.getURLTrustedX() );
            SmartHeader header = UtilesSWHelper.crearSmartHeader(usuario, password);
            
            rReq.setHeader(header);
            rReq.setClaimedIdentity(signerDN); 
            rReq.setServicePolicy(UtilesSWHelper.SERVICE_POLICY_KM); 
            rReq.setRespondWith(Constants.KM.RespondWith.X509CERT); 
            rReq.setKeyUsage( Constants.KM.KeyUsage.SIGNATURE );
            
            SmartRegisterResponse rRes = rReq.send();
            
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS_KM.equals(rRes.getResultMajor()) && 
                    rRes.getResultMinor() == null && 
                        UtilesSWHelper.STATUS_VALID_KM.equals(rRes.getStatusValue()) && 
                            rRes.getPkcs10Data() != null) {
                cert = rRes.getPkcs10Data();
                System.out.println("PKCS10 retrieved: " + cert);
            }      
            else throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_KM_PKCS10, rRes.getResultMajor()); 
        } 
        catch (Exception ex) {
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(SWException.ERROR_EN_SERVICIO + " Mensaje: " + ex.getMessage());
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: generando pkcs10"); 
        }
        return cert;        
    }
    
    
    public void insertarCertificado(String artifact, String certCA, String thumbPrint, String certFirmado, String dn ) throws SWException{
        try{
            SmartRegisterRequest rReq = new SmartRegisterRequest( UtilesSWHelper.getURLTrustedX() );
            
            SmartHeader header = UtilesSWHelper.crearSmartHeader( artifact );  
            rReq.setHeader(header);
            
            rReq.setClaimedIdentity(dn);
            rReq.setServicePolicy( UtilesSWHelper.SERVICE_POLICY_KM );
            
            rReq.setX509Certificate(certFirmado);
            rReq.setRootCertificate(certCA);
            rReq.setRootFingerPrint(thumbPrint);
            SmartRegisterResponse rRes = rReq.send();
            
            if (UtilesSWHelper.RESULTMAJOR_SUCCESS_KM.equals(rRes.getResultMajor()) &&
                        UtilesSWHelper.STATUS_VALID_KM.equals(rRes.getStatusValue())) {
                System.out.println("Certificado insertado satisfactoriamente.");
            }      
            else throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_KM_INSERT_CERT, rRes.getResultMajor());             
        }
        catch(Exception ex){
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al enviar solicitud: insertando certificado.");            
        }
    }
    
}

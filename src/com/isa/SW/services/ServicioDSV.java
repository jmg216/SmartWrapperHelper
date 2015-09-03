/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.services;

import com.isa.SW.entities.VerifyResponse;
import com.isa.SW.exceptions.SWException;
import com.isa.SW.utils.UtilesSWHelper;
import com.safelayer.trustedx.client.smartwrapper.Constants;
import com.safelayer.trustedx.client.smartwrapper.SmartVerifyRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartVerifyResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.axis.AxisFault;

/**
 *
 * @author JMiraballes
 */
public class ServicioDSV {
    
    
    
    /**
     * Método que verifica una firma PKCS#7.
     * 
     * @param usuario
     * @param password
     * @param signatureBase64
     * @return 
     * @throws com.isa.SW.exceptions.SWException 
     */
    public String verificarPKCS7(String usuario, String password, String signatureBase64) throws SWException {
        
        try
        {
            SmartVerifyRequest vReq = new SmartVerifyRequest( UtilesSWHelper.getURLTrustedX() );
            vReq.setHeader( UtilesSWHelper.crearSmartHeader( usuario, password ));
            vReq.setProfile(Constants.Profile.CMSPKCS7);
            vReq.setSignatureBase64(signatureBase64);

            SmartVerifyResponse vResp = vReq.send();

            if (UtilesSWHelper.RESULTMAJOR_SUCCES_FIRMA.equals(vResp.getResultMajor())
                    && UtilesSWHelper.RESULTMINOR_SUCCESS_VERIFY.equals(vResp.getResultMinor())) {
                        return vResp.getDocumentBase64Data();       
            } 
            else{
                throw new SWException(SWException.ERROR_EN_RESPUESTA, UtilesSWHelper.ERROR_SERVICIO_FIRMA);
            }
        }
        catch(AxisFault ex){
            if (SWException.ERROR_DE_AUTENTICACION.equals(ex.getMessage())){
                throw new SWException(SWException.ERROR_DE_AUTENTICACION, UtilesSWHelper.ERROR_AUTENTICACION_MSJ);             
            }
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al verificar firma PKCS#7");  
        }         
        catch (Exception ex) {
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_EN_SERVICIO, "Error al verificar firma PKCS#7");             
        }  
    }    
    
    public VerifyResponse verificarXMLEnveloping(String artifact, String dataSigned) throws SWException {    
        try{
            
            SmartVerifyRequest iReq = new SmartVerifyRequest( UtilesSWHelper.getURLTrustedX() );
            iReq.setHeader( UtilesSWHelper.crearSmartHeader( artifact ));
            
            iReq.setProfile(Constants.Profile.XADES);
            iReq.setSignatureXml( dataSigned );
            iReq.setAddSignatureForm(true);
            iReq.setAddAdditionalInfoValues(true);
            iReq.setAddSignedAttributes(true);
            iReq.setAddOtherVerifyResponses(true);

            SmartVerifyResponse sResp = iReq.send();
            VerifyResponse vResp = new VerifyResponse();
            
            if (UtilesSWHelper.RESULTMAJOR_SUCCES_FIRMA.equals(sResp.getResultMajor()) && 
                    UtilesSWHelper.RESULTMINOR_SUCCESS_VERIFY.equals(sResp.getResultMinor())){
                vResp.setValida(true);
                if (sResp.getSignature(0) != null){
                    vResp.setCn( UtilesSWHelper.getCN( sResp.getSignature(0).getSignerIdentity()) );
                }
            }
            else{
                vResp.setValida(false);
                if (sResp.getSignature(0) != null){
                    vResp.setCn( UtilesSWHelper.getCN( sResp.getSignature(0).getSignerIdentity()) );
                }
            }
            return vResp;
        }
        catch(Exception ex){
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_VALIDAR_FIRMA, "Error al intentar validar la firma.");                         
        }
    }  
    
    
    public boolean verificarDocumentoPdf(String artifact, String dataSigned) throws SWException {
        try{
            SmartVerifyRequest iReq = new SmartVerifyRequest( UtilesSWHelper.getURLTrustedX() );
            iReq.setHeader( UtilesSWHelper.crearSmartHeader( artifact ));
            
            iReq.setProfile(Constants.Profile.PDF);
            iReq.setInputPdfBase64Data(dataSigned);
            
            SmartVerifyResponse iResp = iReq.send();
            return UtilesSWHelper.RESULTMAJOR_SUCCESS.equals(iResp.getResultMajor()) && 
                    (UtilesSWHelper.RESULTMINOR_SUCCESS_VERIFY.equals(iResp.getResultMinor()) ||
                    UtilesSWHelper.RESULTMINOR_SUCCESS_VERIFY_MULTISIGN_PDF.equals(iResp.getResultMinor()));
        }  
        catch(Exception ex){
            Logger.getLogger(ServicioKM.class.getName()).log(Level.SEVERE, null, ex);
            throw new SWException(SWException.ERROR_VALIDAR_FIRMA, "Error al intentar validar la firma.");                         
        }
        
    }
    
}

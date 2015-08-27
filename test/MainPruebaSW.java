/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.isa.SW.entities.User;
import com.isa.SW.exceptions.SWException;
import com.isa.SW.services.ServicioAA;
import com.isa.SW.services.ServicioEP;
import com.isa.SW.services.ServicioFirma;
import com.isa.SW.services.ServicioKM;
import com.isa.SW.utils.UtilesSWHelper;
import com.isa.SW.utils.XMLServiceGenerator;
import com.safelayer.trustedx.client.smartwrapper.SmartInsertRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartInsertResponse;
import com.safelayer.trustedx.client.smartwrapper.SmartReadRequest;
import com.safelayer.trustedx.client.smartwrapper.SmartReadResponse;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.axis.AxisFault;
import org.apache.axis.encoding.Base64;

/**
 *
 * @author JMiraballes
 */
public class MainPruebaSW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try 
        {
            //UtilesSWHelper.setCodeBase(new URL("http://desarrollo.isaltda.com.uy/apprequest/"));
//            ServicioFirma servF = new ServicioFirma();
//            String data = "Dos para firmar.at";
//            String sign =  servF.firmaAdjuntaPKCS7("Usuario Uno", "1111", data.getBytes());
//            System.out.println("Se ha firmado correctamente el documento: " + sign);
//            String verif = servF.verificarPKCS7("Usuario Uno", "1111", sign);           
//            System.out.println("Se ha verificado el la firma correctamente: " + verif);           
            
            //ServicioKM servKM = new ServicioKM();
            //servKM.generarPKCS10("Usuario Uno", "1112", "CN=Usuario Uno,OU=IGDOC");
            
            //ServicioEP serviEP = new ServicioEP();
            //serviEP.existe("Usuario Uno", "1111", "/TWS/EP/PU/User[@dname='CN=Usuario Uno,OU=IGDOC']");
            
           //ServicioFirma servF = new ServicioFirma();
           //String dn = "CN=DesarrolloCA, O=DesarrolloISA, C=UY";
           //String data = "\"<library Id='c123tpe4ryta6di24'>\" +\"<book Id='1'>\" +\" <title>The dark house</title>\" +\"</book>\" +\"<book Id='2'>\" +\" <title>The laundry</title>\" +\"</book>\" +\"</library>\";";
           //String sign =  servF.firmaXMLEnvelped("Usuario Uno", "1111", dn, data.getBytes()); 
           //System.out.println("Se ha firmado correctamente el documento: " + sign);

            
            //ServicioEP serviEP = new ServicioEP();
            //serviEP.modificarPassword("admin", "demodemo", "CN=Usuario Uno,OU=IGDOC", "7777");
            
            //ServicioKM servicioKM = new ServicioKM();
            //String usuario, String password, String certCA, String thumbPrint, String certFirmado, String dn
            //servicioKM.insertarCertificado("Usuario Uno", "", "fdsafdsa", "fdsafds", "fdsfdsa", "CN='fdsa'");
            
            
//            UtilesSWHelper.setCodeBase(new URL("http://dom01test.imm.gub.uy/Solicitud"));
             UtilesSWHelper.setCodeBase(new URL("http://localhost:8082/ISCertDemo1/resources/swHelper.properties"));
             ServicioAA serviAA = new ServicioAA();
             String artifact = serviAA.login("trustedx", "trustedx");
             
             ServicioFirma servF = new ServicioFirma();
             String data = "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6\">\n" +
"<ds:SignedInfo>\n" +
"<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
"<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
"<ds:Reference Id=\"xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-ref0\" Type=\"http://www.w3.org/2000/09/xmldsig#Object\" URI=\"#xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-object0\">\n" +
"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
"<ds:DigestValue>LV+OBXoBgGucoO6FuIaQseBFKMKkwiMuusUgdw4Paw8=</ds:DigestValue>\n" +
"</ds:Reference>\n" +
"<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-signedprops\">\n" +
"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
"<ds:DigestValue>EO3HhJ8dxJ3XLJuhPhEpt1Y/89nHny+CwPGDVU//jlE=</ds:DigestValue>\n" +
"</ds:Reference>\n" +
"</ds:SignedInfo>\n" +
"<ds:SignatureValue Id=\"xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-sigvalue\">\n" +
"A3J8yzda6SWEgMTl1SNJAlQGa0CspVIh4H6XezlZ8ObA8hrE4pG1Tc4tkR0TcBLr9xVn4OSzNsoq\n" +
"cXBMQaXU5DHF4I5J3ZQpM6zX1Ysdkv5JY795arvX3uTwGtWRLrKvwf8i2a5DQVAJIUM6yxkYw+kN\n" +
"apG2DWcaJkeu0SlXd5zU0OildubWInRSNnJaqhZDkr0fb9O9iStprg0fkdUokI0E29YVVobYE9kM\n" +
"tAZy/Ak5+MQj0cRPezviJ3M6QsIGbDEy6iA2CsYFI4esRNTsGZGboWeOY6N0oZpar33t4ubE7ZR0\n" +
"jQdNo3wv4hhZMFW6vDMDjN+dJ2w4DffXYykGmg==\n" +
"</ds:SignatureValue>\n" +
"<ds:KeyInfo>\n" +
"<ds:X509Data>\n" +
"<ds:X509Certificate>\n" +
"MIIHaDCCBVCgAwIBAgIQS2MtlBZw3pRUB5QNAwQRjzANBgkqhkiG9w0BAQsFADBPMRcwFQYDVQQF\n" +
"Ew5SVUMgODAwODAwOTktMDELMAkGA1UEBhMCUFkxETAPBgNVBAoMCFZJVCBTLkEuMRQwEgYDVQQD\n" +
"EwtDQS1WSVQgUy5BLjAeFw0xNDA5MDMyMjE5NTdaFw0xNjA5MDMyMjE5NTdaMIGUMQswCQYDVQQG\n" +
"EwJQWTEWMBQGA1UECgwNV0FMVEVSIFNVQVJFWjEXMBUGA1UECwwOUEVSU09OQSBGSVNJQ0ExHDAa\n" +
"BgNVBAMTE1dBTFRFUiBTVUFSRVogRklSTUExFDASBgNVBAUTC0NJIDE3NzA2MTY2MQ8wDQYDVQQE\n" +
"EwZTVUFSRVoxDzANBgNVBCoTBldBTFRFUjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\n" +
"AL3V/awxnTJJhao9Hdht56N4crD8NRDP/B+XXgwKqhfqaYN8VFUCTIL11ISqCUPC5yd8vBQM3aBf\n" +
"TP2kTuo62HJgM/yOdDKIkRIiP+L6OLmxFIanEcX+5iMpf2oD8/OmO8deNxecmIcQCb8KiUVumwqn\n" +
"zXbMKRkPUvZemtWnqT8mBR6+loLX2gI5PfVkifz6lsKTktrKcsLUrZT2KFLiGIs9YzjIQViqAeh2\n" +
"D0GWm7GwPtBafqYTc0SWZ1VgfmiB2NYU/Vc8NYqkN5DiMLZurTCjL7wUNKhIYgJAHCT4gyK6qLcm\n" +
"P1+c6+AIdEVl4iXeD2xVjlIP7NSq5Qle4hUHXLMCAwEAAaOCAvgwggL0MAwGA1UdEwEB/wQCMAAw\n" +
"DgYDVR0PAQH/BAQDAgZAMBMGA1UdJQQMMAoGCCsGAQUFBwMEMB0GA1UdDgQWBBQYvvejnm9diu1D\n" +
"W/nLzcOWk4weajAfBgNVHSMEGDAWgBQDY3yfbVpypVORtNvskfsDX3x8nTCCAegGA1UdIAEB/wSC\n" +
"AdwwggHYMIIB1AYMKwYBBAGC2UoBAQECMIIBwjAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5lZmlt\n" +
"YS5jb20ucHkvcmVwb3NpdG9yaW8wgc0GCCsGAQUFBwICMIHAGoG9RXN0ZSBlcyB1biBDZXJ0aWZp\n" +
"Y2FkbyBkZSBGaXJtYSBEaWdpdGFsIGN1eWEgY2xhdmUgcHJpdmFkYSBlc3ThIHNvcG9ydGFkYSBw\n" +
"b3IgdW4gZGlzcG9zaXRpdm8gY3JpcHRvZ3LhZmljbyBzZWd1cm8geSBjdXlvIPpuaWNvIG9iamV0\n" +
"aXZvIGVzIGVsIGRlIHNlciB1dGlsaXphZG8gcGFyYSBnZW5lcmFyIGZpcm1hcyBkaWdpdGFsZXMu\n" +
"MIG+BggrBgEFBQcCAjCBsRqBrlRoaXMgaXMgYW4gRGlnaXRhbCBTaWduYXR1cmUgQ2VydGlmaWNh\n" +
"dGUgd2hvc2UgcHJpdmF0ZSBrZXkgaXMgc3VwcG9ydGVkIGJ5IGEgc2VjdXJlIGNyeXB0b2dyYXBo\n" +
"aWMgZGV2aWNlIGFuZCBpdCBpcyBhaW1lZCB0byBiZSB1c2VkIGV4Y2x1c2l2ZWx5IHRvIGdlbmVy\n" +
"YXRlIERpZ2l0YWwgU2lnbmF0dXJlLjAYBggrBgEFBQcBAwQMMAowCAYGBACORgEBMDcGCCsGAQUF\n" +
"BwEBBCswKTAnBggrBgEFBQcwAYYbaHR0cDovL3d3dy5lZmlybWEuY29tLnB5L3ZhMEAGA1UdHwQ5\n" +
"MDcwNaAzoDGGL2h0dHA6Ly93d3cuZWZpcm1hLmNvbS5weS9yZXBvc2l0b3Jpby9lZmlybWEuY3Js\n" +
"MA0GCSqGSIb3DQEBCwUAA4ICAQBJYpia7SpIf49x1hue1+Mjei8d3fGme+ATV9IQhiD10PC5uRGq\n" +
"daxhYgar2rBJrcyx8GE7LvQDTnORZctZW652uA/N1v84ft1ofbQDr0zZv4jO05VwhT+CWgtNeWfx\n" +
"trMk2V4z4tAdufWZ9MVSVToTXUe0iuWqXuvMVX4Q5i2umHsHGi0aUt0c+Mq6kfjWuGrEB3Z6f9ur\n" +
"Twk/OZb5RZGlXGUQ6qIEYE5wns3mFhi25grSavdAG+I8DNhBvLFfYjdfVnPapq33akeR6OYqdqcw\n" +
"ddyB4KMDoEs7lNprD4fQt/e2ACJ8HE2QiCxuDANF6SHKglBEYxKjXYq7mUKL+y9wEFA0GeBM416Z\n" +
"m0Su6O7xiRS7G+as+82f9NqS0DZxpFUG1jv+Rt68yDgbBsoe/6SpnELafD+tremiOxiCuV1nJcQo\n" +
"63LsQ6L0P7/O56mEso6MK+Z1eXJWWi5vYq3lUBLM/5HhptBk2hlFNVDYbWIXSy3A8RkruaDeTS+V\n" +
"oxv2IGGg1q9HKf/+CnIRNdCEABB6gO99jeHuvKRzUbfioLBAcyP+JH0Cq2nLSD3t6K6lTTYETIKu\n" +
"+6e10AlDgxpk0VYOLecMWZr/Y9exvqlBOgHL1Ev038ZhvDSJGjppwihyfPih8usiZdYw4WmQX4Bk\n" +
"tqvmHbUILiHLvY+4dDxB/beizg==\n" +
"</ds:X509Certificate>\n" +
"</ds:X509Data>\n" +
"</ds:KeyInfo>\n" +
"<ds:Object Encoding=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-object0\" MimeType=\"text/plain\"><ds:Data Id=\"3EA2TCTIQ\">jklñjklñjklñ jklñ jklñ jklñ jkl</ds:Data></ds:Object>\n" +
"<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6\"><xades:SignedProperties Id=\"xmldsig-0afd7a82-c1c8-4d26-b043-29f0849f12d6-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2015-08-14T10:14:42.533-03:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>CAuUdJzY5cnYKvKF4UlsUhlS3jvA3l9taJklQS3RAfA=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=CA-VIT S.A.,O=VIT S.A.,C=PY,2.5.4.5=#130e5255432038303038303039392d30</ds:X509IssuerName><ds:X509SerialNumber>100207061514008975848699811085823512975</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>cp2z2IK6oTFMLo6UseDcyuvB184L5IF+OkOEs6030p8=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=Autoridad Certificadora Raíz del Paraguay,O=Ministerio de Industria y Comercio,C=PY</ds:X509IssuerName><ds:X509SerialNumber>123275885733825917770429423940545947919</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>Ucm6TO/N7Y7X9TBWoNbFRmFrUUv2E7o+CL/KeXRDT9w=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=Autoridad Certificadora Raíz del Paraguay,O=Ministerio de Industria y Comercio,C=PY</ds:X509IssuerName><ds:X509SerialNumber>16125737038158638989145826600995216549</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
"</ds:Signature>";
             servF.verificarXMLEnveloping(artifact, data);
             
             serviAA.logut("trustedx", artifact);
             
//            ServicioEP serviEP = new ServicioEP();
//            String result = serviEP.read("imm1", "3333", XMLServiceGenerator.getUserXPath("IMM1", "IGDOC"));
//            String nuevoXmlUsuario = XMLServiceGenerator.actualizarUsuario(result, "Passwd", "4444");
//            serviEP.update("imm1", "3333", XMLServiceGenerator.getUserXPath("IMM1", "IGDOC"), nuevoXmlUsuario);
            
//            String nuevoUsuario = serviEP.read("wsigdoc", "isa123", XMLServiceGenerator.getUserXPath("IMM1", "IGDOC"));
//            System.out.println("Usuario Nuevo: " + nuevoUsuario);
//            System.out.println("Usuario Nuevo: " + nuevoUsuario);
            
//            ServicioAA serviAA = new ServicioAA();
//            serviAA.logut("wsigdoc", "isa123");
            
            //***************************************************************************************
            
//            ServicioEP servEP = new ServicioEP();
//            ServicioAA servAA = new ServicioAA();
//            ServicioFirma servFirma = new ServicioFirma();
//            ServicioKM servKM = new ServicioKM();
            
            
//            String xPath = XMLServiceGenerator.getUserXPath("administrador igdoc", "IGDOC");
//            String xPath = XMLServiceGenerator.getUserXPath("IMM2", "IGDOC");
//            User usuInsert = new User();
            
//            usuInsert.setoNameOU("IGDOC");
//            usuInsert.setiName("Usuario seis");
//            usuInsert.setsNameUID("useis");
//            usuInsert.setSNamePasswd("useis");
//            usuInsert.setcNameFName("Usuario Seis");
            
            
//            String xPathInsert = XMLServiceGenerator.XPATH_USER;
//            String data = XMLServiceGenerator.generarUsuarioXML( usuInsert );   
//            String dataToSign = "para firmar";
            
//            String user = "wsigdoc";
//            String password = "igdoc";
            
//            String user = "wstest01";
//            String password = "wstest01";
            
            //"CN=IMM2, OU=IGDOC"
//            String usrstr = read(user, password, xPath);
//            System.out.println("Usuario: " + usrstr);
            
//            String artifact = servAA.login(user, (password));
//            System.out.println("Artifact: " + artifact);
//            servEP.existe(artifact, xPath);
//              insert(user, password, xPathInsert, data);
//            servEP.existe(artifact, xPath);
//            String sign =  servFirma.firmaAdjuntaPKCS7(artifact, dataToSign.getBytes());
//            System.out.println("Se ha firmado correctamente el documento: " + sign);
            
//            String dn = "CN=ucuatro,OU=IGDOC";
//            String pkcs10 = servKM.generarPKCS10(artifact, dn);
//            System.out.println("Clave generada: " + pkcs10);
            
//            servAA.logut(user, artifact);
           
            
//          servEP.insert("test01", "test01", xPathInsert, data);
            
//            String xPath2 = XMLServiceGenerator.getUserXPath("udos", "IGDOC");
//            servEP.eliminar("test01", "test01", xPath2);
            
//            ServicioKM servKM = new ServicioKM();
//            servKM.generarPKCS10("test01", "test01", "CN=test01,OU=IGDOC");
            
            
            
        } 
//        catch (SWException ex){
//            Logger.getLogger(MainPruebaSW.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("SWException tipo: " + ex.getTipo() );
//            System.out.println("SWException mensaje: " + ex.getMensaje() );
//        }
        catch (Exception ex) {
            Logger.getLogger(MainPruebaSW.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FALLO!!");
        }
        
    }  
    
    /*
    public static String convertToSHA256( String str ) throws NoSuchAlgorithmException{
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        sha.digest(str.getBytes());
        return org.apache.axis.encoding.Base64.encode(sha.digest(str.getBytes()));
    }  
    
    public static String read( String usuario, String password, String xPath ) throws SWException{
        try{
            SmartReadRequest rReq = new SmartReadRequest( UtilesSWHelper.getURLTrustedX() ); 
            rReq.setHeader(UtilesSWHelper.crearSmartHeader( usuario, password )); 
            
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
    
    public static void insert( String usuario, String contrasenia, String xPath, String data ) throws SWException{
        try{
            SmartInsertRequest iReq = new SmartInsertRequest( UtilesSWHelper.getURLTrustedX() );
            
            iReq.setHeader( UtilesSWHelper.crearSmartHeader( usuario, contrasenia ));
            
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
        }*/
//    }
    
    
    
    
    
    
    
    
}

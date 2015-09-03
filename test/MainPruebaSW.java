/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.isa.SW.entities.User;
import com.isa.SW.entities.VerifyResponse;
import com.isa.SW.exceptions.SWException;
import com.isa.SW.services.ServicioAA;
import com.isa.SW.services.ServicioDSV;
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
             UtilesSWHelper.setCodeBase(new URL("http://localhost:8080/ISCertDemo1/resources/swHelper.properties"));
             ServicioAA serviAA = new ServicioAA();
             String artifact = serviAA.login("trustedx", "trustedx");
             
             ServicioDSV servF = new ServicioDSV();
             String data = "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-f736d4ae-911b-422b-b20c-4ec456999171\">\n" +
"<ds:SignedInfo>\n" +
"<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
"<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
"<ds:Reference Id=\"xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-ref0\" Type=\"http://www.w3.org/2000/09/xmldsig#Object\" URI=\"#xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-object0\">\n" +
"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
"<ds:DigestValue>CIQYMd9w3DYcW8i/DN/BSHaftEB1eBq4QPseJHiLnp8=</ds:DigestValue>\n" +
"</ds:Reference>\n" +
"<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-signedprops\">\n" +
"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
"<ds:DigestValue>rd3xbTkPS8cHParg52HMo0gkCjCr8GAtqw2W35D1oxI=</ds:DigestValue>\n" +
"</ds:Reference>\n" +
"</ds:SignedInfo>\n" +
"<ds:SignatureValue Id=\"xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-sigvalue\">\n" +
"r5JT1CmvHjqEA8w+mozA4MUr9ObCE6y/mi4NHtvAHTodboGm4CbcI92uusmjIvxCZQM9ecQaMS66\n" +
"7sn1sx9yxDQ/gPoy7H/OBt2h8f2UC25eB6rChBfzVvN0/ffk+ugMm9hWqzzvQv8juFJVVrPz9RVR\n" +
"R/izQ34u5qfIYqMM2oMcFfnrd8n2Yr0Gwto779oIL68eOSJdonsdkc617+rqo1M0cZ9aU0lV6xRS\n" +
"AS3YcxgeN4eFueUE5yQMbuvULC5NfIhyw2wIQJeNwymdTU0C/dq88LitIphyAZ4ROER4Fn69SUEl\n" +
"xtCha0lan/df53lahOsvP38ojKKT24ilO83WtQ==\n" +
"</ds:SignatureValue>\n" +
"<ds:KeyInfo>\n" +
"<ds:X509Data>\n" +
"<ds:X509Certificate>\n" +
"MIIHlDCCBXygAwIBAgIQTgST08ccTyJVzlhgSQqmYDANBgkqhkiG9w0BAQsFADBPMRcwFQYDVQQF\n" +
"Ew5SVUMgODAwODAwOTktMDELMAkGA1UEBhMCUFkxETAPBgNVBAoMCFZJVCBTLkEuMRQwEgYDVQQD\n" +
"EwtDQS1WSVQgUy5BLjAeFw0xNTA4MTQyMTA2NDBaFw0xNzA4MTQyMTA2NDBaMIHAMQswCQYDVQQG\n" +
"EwJQWTElMCMGA1UECgwcSlVBTiBSQU1PTiBCRU5JVEVaIFJPRFJJR1VFWjEXMBUGA1UECwwOUEVS\n" +
"U09OQSBGSVNJQ0ExKzApBgNVBAMTIkpVQU4gUkFNT04gQkVOSVRFWiBST0RSSUdVRVogRklSTUEx\n" +
"EzARBgNVBAUTCkNJIDUwNjQzMTkxGjAYBgNVBAQTEUJFTklURVogUk9EUklHVUVaMRMwEQYDVQQq\n" +
"EwpKVUFOIFJBTU9OMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAszuvusEQM2lzGCmI\n" +
"uuLD4ctakIzQ0wgVY1hH/0JkDIJuI9rFl6837dujBpXrSxyjXxkuA+LNw+uluvzwScX9RQf3yLO8\n" +
"2E5IJOEuT5Ecx4/mSG5rOCGDzx0Far9ABG3lXkhqieMPb0NcE5iOb1BqEpRWy9XWgspQYQn1cbJD\n" +
"slhrQH0MNEheIWJ7tXIbBrLN8r0k5/HCSOx559TvnXaaVtRtLcZmppkLb1KTp5YN5pMMTgYWlM5J\n" +
"xOXpmmrwRdWRyHSmCk+nv/JKmr5wrKBWZhgLN3mKipE1RnsVODRm+HSdT15ut809tf07FgrheDC1\n" +
"dWjv5R9s+6JwEETkGHz7GQIDAQABo4IC+DCCAvQwDAYDVR0TAQH/BAIwADAOBgNVHQ8BAf8EBAMC\n" +
"BkAwEwYDVR0lBAwwCgYIKwYBBQUHAwQwHQYDVR0OBBYEFOVR/cSeZK/qQrWR5rVIgiWCHnKRMB8G\n" +
"A1UdIwQYMBaAFANjfJ9tWnKlU5G02+yR+wNffHydMIIB6AYDVR0gAQH/BIIB3DCCAdgwggHUBgwr\n" +
"BgEEAYLZSgEBAQIwggHCMC8GCCsGAQUFBwIBFiNodHRwOi8vd3d3LmVmaW1hLmNvbS5weS9yZXBv\n" +
"c2l0b3JpbzCBzQYIKwYBBQUHAgIwgcAagb1Fc3RlIGVzIHVuIENlcnRpZmljYWRvIGRlIEZpcm1h\n" +
"IERpZ2l0YWwgY3V5YSBjbGF2ZSBwcml2YWRhIGVzdOEgc29wb3J0YWRhIHBvciB1biBkaXNwb3Np\n" +
"dGl2byBjcmlwdG9ncuFmaWNvIHNlZ3VybyB5IGN1eW8g+m5pY28gb2JqZXRpdm8gZXMgZWwgZGUg\n" +
"c2VyIHV0aWxpemFkbyBwYXJhIGdlbmVyYXIgZmlybWFzIGRpZ2l0YWxlcy4wgb4GCCsGAQUFBwIC\n" +
"MIGxGoGuVGhpcyBpcyBhbiBEaWdpdGFsIFNpZ25hdHVyZSBDZXJ0aWZpY2F0ZSB3aG9zZSBwcml2\n" +
"YXRlIGtleSBpcyBzdXBwb3J0ZWQgYnkgYSBzZWN1cmUgY3J5cHRvZ3JhcGhpYyBkZXZpY2UgYW5k\n" +
"IGl0IGlzIGFpbWVkIHRvIGJlIHVzZWQgZXhjbHVzaXZlbHkgdG8gZ2VuZXJhdGUgRGlnaXRhbCBT\n" +
"aWduYXR1cmUuMBgGCCsGAQUFBwEDBAwwCjAIBgYEAI5GAQEwNwYIKwYBBQUHAQEEKzApMCcGCCsG\n" +
"AQUFBzABhhtodHRwOi8vd3d3LmVmaXJtYS5jb20ucHkvdmEwQAYDVR0fBDkwNzA1oDOgMYYvaHR0\n" +
"cDovL3d3dy5lZmlybWEuY29tLnB5L3JlcG9zaXRvcmlvL2VmaXJtYS5jcmwwDQYJKoZIhvcNAQEL\n" +
"BQADggIBAIrpwzDA5mbR9bIdKErUAaDLy/HsCex1IJVAZ3am37DHTarhHKKdqtZTH6jhlB/6TuHE\n" +
"brd6fNBP0As2IPruxUowiIlqrMG6YHeU6L5+XnkWjqONE4FH+IxOc4MJyWO6C26S4Nl+Xg5rTHqO\n" +
"NpltEMBhMl2Boel0WRVg/zXqGHPET2CD9+CvMSbywfmUMrJtD68w3jXt4k52h222WMClVMCAugQs\n" +
"Oz4nnqPf9fGFFscm+E3U++Dci281jah4P8V4Nyn1xLZ5Ltyar38JAbelAIlt4EEpUPxdxlX5AzIT\n" +
"XsmOu6mzQq1bHtlkrNEN7HZohVnooYe3Sqvtuirx53UeLHEmU6AdrbcMhSSV+UKGGcKu+KI+yDv3\n" +
"G9/PXDzxqNcAwuH201MmFqwmaY7HetXjdsPC9oLKN3HBBgeFACc8wRhLQsxHofg/3aoqKk2vxJvD\n" +
"EmgDmbBBZluVo9mu0qApve83RKRRXoO1JdRb6Tn+TzoBy0xOhe2QUjSXf5S6GMrX3TbQLqV+bUcN\n" +
"tFT244RUKivwNnESz4wH2JnphwpZ733hZqmBg6rPjqgBIV13hnA8BbrQemD2iHdN8/D8X1J9/HvO\n" +
"n/hocTPVwUSfxlfbeAoyqiHdpokeTUZatxJaEKjc88lm8DWAgzkKv5Mg5cMBDtQUz2O+HmCO/V19\n" +
"nLfP7vqJ\n" +
"</ds:X509Certificate>\n" +
"</ds:X509Data>\n" +
"</ds:KeyInfo>\n" +
"<ds:Object Encoding=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-object0\" MimeType=\"text/plain\"><ds:Data Id=\"2BGF931UX\">Documento a firmar.</ds:Data></ds:Object>\n" +
"<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-f736d4ae-911b-422b-b20c-4ec456999171\"><xades:SignedProperties Id=\"xmldsig-f736d4ae-911b-422b-b20c-4ec456999171-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2015-09-01T18:36:29.048-03:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>exrSJeGmCDdA1Mh0OMQeqzIVBQYCNeysyQr4OGEOFyQ=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=CA-VIT S.A.,O=VIT S.A.,C=PY,2.5.4.5=#130e5255432038303038303039392d30</ds:X509IssuerName><ds:X509SerialNumber>103703551151633345280059925181107971680</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
"</ds:Signature>";
             VerifyResponse vr = servF.verificarXMLEnveloping(artifact, data);
             
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

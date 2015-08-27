/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.utils;

import com.isa.SW.entities.User;
import com.isa.SW.exceptions.SWException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author JMiraballes
 */
public class XMLServiceGenerator {
    
    public static String XPATH_USER = "/TWS/EP/PU";
    public static String XPATH_ALMACEN_CLAVES = "/TWS/EP/TE/Entity";
    public static String TAG_USUARIO_PASSWD = "Passwd";
    
    public static String getUserXPath( String cn, String ou ){
        String xPath = XPATH_USER + "/User[@dname=\"CN="+ cn + ",OU=" + ou + "\"]";
        return xPath;
    }    
    
    public static String getAlmacenClavesXPath( String cn, String ou ){
        String xPath = XPATH_ALMACEN_CLAVES + "[@dname=\"CN="+ cn + ",OU=" + ou + "\"]";
        return xPath;        
    }
    
    public static String generarUsuarioXML( User user ){
    String xmlUser = 
"      	   	<User dname=\"CN="+ user.getsNameUID() + ",OU="+ user.getoNameOU() + "\">" +
"  			<Description>"+ user.getDescription() +"</Description>" +
"  			<IName>"+ user.getiName() +"</IName>" +
"  			<Language>"+user.getLanguage()+"</Language>" +
"  			<CName>" +
"    				<Title>" + user.getcNameTitle() + "</Title>" +
"    				<FName>" + user.getcNameFName() + "</FName>" +
"    				<Surname>"+ user.getcNameFName() +"</Surname>" +
"  			</CName>" +
"  			<OName>" +
"    				<Title>"+ user.getoNameTitle() +"</Title>" +
"    				<OU>"+ user.getoNameOU() +"</OU>" +
"    				<O>" + user.getoNameO() + "</O>" +
"  			</OName>" +
"  			<SName>" +
"    				<UID>" + user.getsNameUID() + "</UID>" +
"    				<Passwd>" + user.getSNamePasswd() + "</Passwd>" +
" 			</SName>" +
"  			<Contacts>" +
"    				<Work>" +
"      				<Mail>"+ user.getContactsWorkMail() + "</Mail>" +
"      				<Phone>"+ user.getContactsWorkPhone() + "</Phone>" +
"    				</Work>" +
"    				<Home></Home>" +
"  			</Contacts>" +
"  			<Certificates></Certificates>" +
"		</User>"; 
        
        return xmlUser;
    }
    
    public static String actualizarUsuario(String xml, String tagName, String valor) throws SWException {
        try {
            //DOMParser parser = new DOMParser();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new InputSource(new StringReader(xml)));
            Node passwd = doc.getElementsByTagName(tagName).item(0);
            
            passwd.setTextContent(valor);
            String str = convertDocumentToString( doc );
            System.out.println("XML modificado: " + str);
            return str;
            
        } catch (SAXException  e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        } catch (TransformerException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");         
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        }        
    }
    
    public static String getAtributoEntity( String xmlUsuario, String tagName ) throws SWException{
        try {
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new InputSource(new StringReader(xmlUsuario)));
            Node passwd = doc.getElementsByTagName(tagName).item(0);
            return passwd.getTextContent();
            
        } catch (SAXException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        } 
        catch (ParserConfigurationException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        } 
        catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(XMLServiceGenerator.class.getName()).log(Level.SEVERE, null, e);
            throw  new SWException( SWException.ERROR_EN_SERVICIO, "Error parseando xml.");
        }         
    }
        
    public static String convertDocumentToString(Document doc) throws TransformerException{
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(domSource, result);
        
        return writer.toString();
    }    
}

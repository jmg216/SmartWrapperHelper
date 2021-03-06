/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JMiraballes
 * 
 * Clase que encapsula el mecanismo de acceso a un archivo de properties que
 * se encuentra en la la web donde se embebe el applet.
 * 
 */
public class UtilesResources {
    
    private static UtilesResources instance;
    private Properties appProperties = null;
    
    private UtilesResources() throws IOException{
        try{
            appProperties = new Properties();
            URL url = (URL) AccessController.doPrivileged(new PrivilegedAction(){
                    @Override
                    public Object run() {
                        try{
                            return new URL(UtilesSWHelper.getCodeBase().toString());
                        }
                        catch (MalformedURLException e){
                            e.printStackTrace();
                            return null;
                        }
                    }  
                });
            appProperties.load(( url ).openStream());
            
        }
        catch(IOException ex){
            Logger.getLogger(UtilesResources.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;            
        }
    }
    
    private static UtilesResources getInstance() throws IOException{
        if (instance == null){
            instance = new UtilesResources();
        }
        return instance;
    }
     
    public static String getProperty(String key) throws IOException{
        return getInstance().getProperties().getProperty(key);
    }
    
    private Properties getProperties(){
        return this.appProperties;
    }
}

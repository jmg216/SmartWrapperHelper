/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW;

import com.isa.SW.services.ServicioAA;
import com.isa.SW.services.ServicioDSV;
import com.isa.SW.services.ServicioEP;
import com.isa.SW.services.ServicioFirma;
import com.isa.SW.services.ServicioKM;

/**
 *
 * @author JMiraballes
 * 
 * Clase encargada de encapsular todas las creaciones
 * de los objetos Servicio, que contienen las llamadas
 * a los diferentes métodos expuestos por los servicios
 * web de TrustedX
 * 
 */
public class SWHelperFactory {
    
    /**
     * Crea el servicio de gestión de usuarios (EP - Entity Profiler).
     * @return  ServicioEP
     */
    public static ServicioEP createServiceEP(){
        return new ServicioEP();
    }

    
    /**
     * Crea el servicio de gestion de claves (KM)
     * @return ServicioKM
     */
    public static ServicioKM createServiceKM(){
        return new ServicioKM();
    }
    
    /**
     * Crea el servicio de firma 
     * @return ServicioFirma
     */
    public static ServicioFirma createServiceFirma(){
        return new ServicioFirma();
    }

    /**
     * Crea el servicio de autenticación AA
     * @return ServicioAA
     */    
    public static ServicioAA createServiceAA(){
        return new ServicioAA();
    }
    
    
    /**
     * Crea el servicio de verificación 
     * de firma DSV
     * @return 
     */
    public static ServicioDSV createServiceDSV(){
        return new ServicioDSV();
    }
    
}

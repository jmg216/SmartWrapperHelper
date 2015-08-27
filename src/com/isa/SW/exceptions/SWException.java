/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.exceptions;


/**
 *
 * @author JMiraballes
 */
public class SWException extends Exception {
    
    public static String ERROR_EN_RESPUESTA = "Error en la respuesta del servicio";
    public static String ERROR_EN_SERVICIO = "Error al conectar con servicio";
    public static String ERROR_DE_AUTENTICACION = "Message not authenticated";
    public static String FIRMA_NO_VALIDA = "Firma NO valida";
    public static String ERROR_VALIDAR_FIRMA = "Error validar firma";
    
    private String tipo;
    private String mensaje;
    private String resultado;
    private String stacktrace;

    public SWException(String tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    
    public SWException(String tipo, String mensaje, String resultado) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.resultado = resultado;
    }

    public SWException(String tipo, String mensaje, String resultado, String stacktrace) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.resultado = resultado;
        this.stacktrace = stacktrace;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}

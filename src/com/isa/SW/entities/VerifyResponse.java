/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isa.SW.entities;

/**
 *
 * @author JMiraballes
 */
public class VerifyResponse {
    private boolean valida;
    private String cn;

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }
    
    
}

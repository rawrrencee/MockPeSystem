/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Lawrence
 */
public class ModuleNotFoundException extends Exception {
    
    public ModuleNotFoundException() {
    }
    
    public ModuleNotFoundException(String msg) {
        super(msg);
    }
}

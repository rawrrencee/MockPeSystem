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
public class ModuleExistException extends Exception {

    public ModuleExistException() {
    }
    
    public ModuleExistException(String msg) {
        super(msg);
    }
}

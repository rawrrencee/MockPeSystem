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
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
    
}

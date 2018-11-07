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
public class StudentExistException extends Exception {

    public StudentExistException() {
    }

    public StudentExistException(String message) {
        super(message);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockpesystemclient;

import ejb.session.stateless.ModuleSessionBeanRemote;
import ejb.session.stateless.StudentSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Lawrence
 */
public class Main {

    @EJB
    private static StudentSessionBeanRemote studentSessionBeanRemote;

    @EJB
    private static ModuleSessionBeanRemote moduleSessionBeanRemote;
    
    public static void main(String[] args) {
        
        MainApp mainApp = new MainApp(moduleSessionBeanRemote, studentSessionBeanRemote);
        mainApp.run();
    }
    
}

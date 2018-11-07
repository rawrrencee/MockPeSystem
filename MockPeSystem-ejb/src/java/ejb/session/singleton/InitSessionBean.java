/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.ModuleSessionBeanLocal;
import entity.Module;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import util.exception.ModuleNotFoundException;

/**
 *
 * @author Lawrence
 */
@Singleton
@LocalBean
@Startup
public class InitSessionBean {

    @EJB
    private ModuleSessionBeanLocal moduleSessionBeanLocal;

    @PostConstruct
    public void PostConstruct() {
        try {
           moduleSessionBeanLocal.retrieveModuleByModuleId(1l);
        } catch (ModuleNotFoundException e) {
            initialiseData();
        }
    }
    
    private void initialiseData() {
        try {
            moduleSessionBeanLocal.createNewModule(new Module("IS2103", "Enterprise Systems Server-side Design and Development"));
            moduleSessionBeanLocal.createNewModule(new Module("IS3106", "Enterprise Systems Interface Design and Development"));
            moduleSessionBeanLocal.createNewModule(new Module("IS4103", "Information Systems Capstone Project"));
            moduleSessionBeanLocal.createNewModule(new Module("IS4151", "Pervasive Technology Solutions and Development"));
        } catch (Exception e) {
            System.err.println("DataInitSessionBean.initialiseData(): An error has occurred while loading initial test data: " + e.getMessage());
        }
    }
}

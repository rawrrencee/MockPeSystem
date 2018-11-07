/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Module;
import entity.ModuleGrade;
import entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.xml.ws.WebServiceRef;
import util.exception.GeneralException;
import util.exception.ModuleExistException;
import util.exception.ModuleNotFoundException;
import util.exception.StudentNotFoundException;
import ws.client.ModuleGradeWebService.IncompleteGradeException_Exception;
import ws.client.ModuleGradeWebService.ModuleGradeWebService_Service;

/**
 *
 * @author Lawrence
 */
@Stateless
@Remote(ModuleSessionBeanRemote.class)
@Local(ModuleSessionBeanLocal.class)
public class ModuleSessionBean implements ModuleSessionBeanRemote, ModuleSessionBeanLocal {

    @WebServiceRef(wsdlLocation = "http://tanwk.southeastasia.cloudapp.azure.com/ModuleGradeWebService/ModuleGradeWebService?wsdl")
    private ModuleGradeWebService_Service service;

    @EJB
    private StudentSessionBeanLocal studentSessionBeanLocal;

    @PersistenceContext(unitName = "MockPeSystem-ejbPU")
    private EntityManager em;
 
    public ModuleSessionBean() {
    }
    
    @Override
    public List<Module> retrieveAllModules() {
        Query query = em.createQuery("SELECT m FROM Module m");
        
        return query.getResultList();
    }
    
    @Override
    public Module retrieveModuleByModuleId(Long moduleId) throws ModuleNotFoundException {
        Module module = em.find(Module.class, moduleId);
        
        if (module != null) {
            return module;
        } else {
            throw new ModuleNotFoundException("Module ID " + moduleId + " does not exist!");
        }
    }
    
    @Override
    public Module retrieveModuleByModuleCode(String moduleCode) throws ModuleNotFoundException {
        Query query = em.createQuery("SELECT m FROM Module m WHERE m.moduleCode = :inModuleCode");
        query.setParameter("inModuleCode", moduleCode);
        
        try {
            return (Module)query.getSingleResult();
        } catch (NoResultException ex) {
            throw new ModuleNotFoundException("Module code " + moduleCode + " does not exist!");
        }
    }
    
    @Override
    public Module createNewModule(Module newModule) throws ModuleExistException, GeneralException {
        try {
            em.persist(newModule);
            em.flush();

            return newModule;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
                throw new ModuleExistException("A Module with the provided particulars already exists!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }
    
    @Override
    public String enterModuleGrade(String studentNumber, String moduleCode) throws StudentNotFoundException, ModuleNotFoundException {
        ModuleGrade moduleGrade;
        String finalLetterGrade;
        
        Query query = em.createQuery("SELECT mg FROM ModuleGrade mg WHERE mg.student.studentNumber = :inStudentNumber AND mg.module.moduleCode = :inModuleCode");
        query.setParameter("inStudentNumber", studentNumber);
        query.setParameter("inModuleCode", moduleCode);
        
        try {
            moduleGrade = (ModuleGrade)query.getSingleResult();
        } catch (NoResultException ex) {
            moduleGrade = new ModuleGrade();
            
            try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentNumber(studentNumber);
            Module module = retrieveModuleByModuleCode(moduleCode);
            
            moduleGrade.setModule(module);
            moduleGrade.setStudent(student);
            
            student.getModuleGrades().add(moduleGrade);
            module.getModuleGrades().add(moduleGrade);
            em.persist(moduleGrade);
            } catch (StudentNotFoundException | ModuleNotFoundException e) {
                System.out.println("Student or Module does not exist!");
            }
        }
        
        try {
            finalLetterGrade = retrieveStudentModuleGrade("fakeAPIkey", studentNumber, moduleCode);
        } catch (IncompleteGradeException_Exception ex) {
            finalLetterGrade = "IC";
        } 
        
        moduleGrade.setFinalLetterGrade(finalLetterGrade);
        
        return moduleGrade.getFinalLetterGrade();
    }

    private String retrieveStudentModuleGrade(java.lang.String apiKey, java.lang.String studentNumber, java.lang.String moduleCode) throws IncompleteGradeException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.client.ModuleGradeWebService.ModuleGradeWebService port = service.getModuleGradeWebServicePort();
        return port.retrieveStudentModuleGrade(apiKey, studentNumber, moduleCode);
    }
}

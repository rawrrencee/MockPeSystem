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
import util.exception.GeneralException;
import util.exception.ModuleGradeNotFoundException;
import util.exception.ModuleNotFoundException;
import util.exception.StudentAlreadyRegisteredException;
import util.exception.StudentExistException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author Lawrence
 */
@Stateless
@Remote(StudentSessionBeanRemote.class)
@Local(StudentSessionBeanLocal.class)
public class StudentSessionBean implements StudentSessionBeanRemote, StudentSessionBeanLocal {

    @PersistenceContext(unitName = "MockPeSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private ModuleSessionBeanLocal moduleSessionBeanLocal;

    public Student createNewStudent(Student newStudent) throws StudentExistException, GeneralException {
        try {
            em.persist(newStudent);
            em.flush();
            
            return newStudent;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
                throw new StudentExistException("An employee with the provided particulars already exists!");
            }
            else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }
    
    @Override
    public List<Student> retrieveAllStudents() {
        Query query = em.createQuery("SELECT s FROM Student s");
        
        return query.getResultList();
    }
    
    @Override
    public Student retrieveStudentByStudentId(Long studentId) throws StudentNotFoundException{
        Student student = em.find(Student.class, studentId);
        
        if (student != null) {
            return student;
        } else {
            throw new StudentNotFoundException("Student ID " + student.getStudentId() + " does not exist!");
        }
    }
    
    @Override
    public Student retrieveStudentByStudentNumber(String studentNumber) throws StudentNotFoundException {
        Query query = em.createQuery("SELECT s FROM Student s WHERE s.studentNumber = :inStudentNumber");
        query.setParameter("inStudentNumber", studentNumber);
        
       try {
            return (Student)query.getSingleResult();
        } catch (NoResultException ex) {
            throw new StudentNotFoundException("Student with Student Number: " + studentNumber + " does not exist!");
        }
    }
    
    @Override
    public List<ModuleGrade> retrieveModuleGradesByStudentNumber(String studentNumber) throws ModuleGradeNotFoundException {
        Query query = em.createQuery("SELECT mg FROM ModuleGrade mg WHERE mg.student.studentNumber = :inStudentNumber");
        query.setParameter("inStudentNumber", studentNumber);
        
        if (!query.getResultList().isEmpty()) {
            return query.getResultList();
        } else {
            throw new ModuleGradeNotFoundException("Module Grade of Student " + studentNumber + " does not exist!");
        }
    }
    
    @Override
    public void registerStudentForModule(String studentNumber, String moduleCode) throws StudentAlreadyRegisteredException {
        try {
        Module module = moduleSessionBeanLocal.retrieveModuleByModuleCode(moduleCode);
        Student student = retrieveStudentByStudentNumber(studentNumber);
        
        if (!student.getModules().contains(module)) {
            student.getModules().add(module);
            module.getStudents().add(student);
        } else {
            throw new StudentAlreadyRegisteredException("Student " + student.getFullName() + " already registered for Module " + module.getModuleCode() + "!\n");
        }
        } catch (ModuleNotFoundException | StudentNotFoundException ex) {
            throw new StudentAlreadyRegisteredException(ex.getMessage());
        }
    }
}

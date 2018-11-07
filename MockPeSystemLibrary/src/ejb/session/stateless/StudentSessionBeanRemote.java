/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ModuleGrade;
import entity.Student;
import java.util.List;
import util.exception.GeneralException;
import util.exception.ModuleGradeNotFoundException;
import util.exception.StudentAlreadyRegisteredException;
import util.exception.StudentExistException;
import util.exception.StudentNotFoundException;

public interface StudentSessionBeanRemote {

    public Student createNewStudent(Student newStudent) throws StudentExistException, GeneralException;

    public List<Student> retrieveAllStudents();
    
    public Student retrieveStudentByStudentId(Long studentId) throws StudentNotFoundException;

    public void registerStudentForModule(String studentNumber, String moduleCode) throws StudentAlreadyRegisteredException;
    
    public Student retrieveStudentByStudentNumber(String studentNumber) throws StudentNotFoundException;

    public List<ModuleGrade> retrieveModuleGradesByStudentNumber(String studentNumber) throws ModuleGradeNotFoundException;
    
}

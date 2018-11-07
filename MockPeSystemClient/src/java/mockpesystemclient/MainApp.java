/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockpesystemclient;

import ejb.session.stateless.ModuleSessionBeanRemote;
import ejb.session.stateless.StudentSessionBeanRemote;
import entity.ModuleGrade;
import entity.Student;
import java.util.List;
import java.util.Scanner;
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
public class MainApp {

    private ModuleSessionBeanRemote moduleSessionBeanRemote;
    private StudentSessionBeanRemote studentSessionBeanRemote;

    public MainApp() {
    }

    public MainApp(ModuleSessionBeanRemote moduleSessionBeanRemote, StudentSessionBeanRemote studentSessionBeanRemote) {
        this.moduleSessionBeanRemote = moduleSessionBeanRemote;
        this.studentSessionBeanRemote = studentSessionBeanRemote;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        Integer response = 0;

        while (true) {
            System.out.println("*** Welcome to MockPeSystemClient :: Main Page ***\n");
            System.out.println("1. Create Student");
            System.out.println("2. View All Students");
            System.out.println("3. Register Student for Module");
            System.out.println("4. Enter Module Grade");
            System.out.println("5. View Student Results");
            System.out.println("6. Exit\n");
            response = 0;

            while (response < 1 || response > 6) {
                System.out.print("> ");
                input = sc.nextLine().trim();

                try {
                    response = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a numerical value.");
                }

                switch (response) {
                    case 1:
                        createStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        registerStudentForModule();
                        break;
                    case 4:
                        enterModuleGrade();
                        break;
                    case 5:
                        viewStudentResults();
                        break;
                    case 6:
                        return;
                    default:
                        break;
                }
            }
        }
    }

    public void createStudent() {
        Scanner sc = new Scanner(System.in);
        String input;
        Student newStudent = new Student();

        System.out.println("*** MockPeSystemClient :: Create New Student ***");

        System.out.print("Enter Student Number> ");
        input = sc.nextLine().trim();
        newStudent.setStudentNumber(input);
        System.out.print("Enter Full Name> ");
        input = sc.nextLine().trim();
        newStudent.setFullName(input);

        try {
            studentSessionBeanRemote.createNewStudent(newStudent);
            System.out.println("Student Number " + newStudent.getStudentNumber() + " and Full Name " + newStudent.getFullName() + " created!");
        } catch (StudentExistException | GeneralException ex) {
            System.out.println("An error occurred: " + ex.getMessage() + "!\n");
        }
    }

    public void viewAllStudents() {
        List<Student> students = studentSessionBeanRemote.retrieveAllStudents();

        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println("Student ID: " + student.getStudentId() + " | Student Number : " + student.getStudentNumber() + " | Student Full Name: " + student.getFullName());
            }
        } else {
            System.out.println("No Students exist in the system.");
        }
        System.out.println();
    }

    public void registerStudentForModule() {
        Scanner sc = new Scanner(System.in);
        String studentNumber, moduleCode;

        System.out.println("*** MockPeSystemClient :: Register Student for Module ***");
        System.out.println("----------List of Students");
        viewAllStudents();

        System.out.print("Enter Student Number> ");
        studentNumber = sc.nextLine().trim();
        System.out.print("Enter Module Code> ");
        moduleCode = sc.nextLine().trim();

        try {
            studentSessionBeanRemote.registerStudentForModule(studentNumber, moduleCode);
            System.out.println("Student " + studentNumber + " registered for Module " + moduleCode + "!");
        } catch (StudentAlreadyRegisteredException ex) {
            System.out.println("An error has occured: " + ex.getMessage());
        }
    }

    public void enterModuleGrade() {
        Scanner sc = new Scanner(System.in);
        String studentNumber, moduleCode;
        String finalLetterGrade = "IC";

        System.out.println("*** MockPeSystemClient :: Enter Module Grade ***");
        System.out.print("Enter Student Number> ");
        studentNumber = sc.nextLine().trim();
        System.out.print("Enter Module Code> ");
        moduleCode = sc.nextLine().trim();

        try {
            finalLetterGrade = moduleSessionBeanRemote.enterModuleGrade(studentNumber, moduleCode);
        } catch (StudentNotFoundException | ModuleNotFoundException ex) {
            System.out.println("An error has occurred: " + ex.getMessage());
        }
        System.out.println("Final Letter Grade: " + finalLetterGrade);
    }

    public void viewStudentResults() {
        Scanner sc = new Scanner(System.in);
        String studentNumber;
        Student student;
        List<ModuleGrade> moduleGrades;

        System.out.println("*** MockPeSystemClient :: View Student Results ***");
        System.out.print("Enter Student Number> ");
        studentNumber = sc.nextLine().trim();
        System.out.println();

        try {
            student = studentSessionBeanRemote.retrieveStudentByStudentNumber(studentNumber);
            System.out.println("----------Retrieving records for Student: " + student.getFullName());
            moduleGrades = studentSessionBeanRemote.retrieveModuleGradesByStudentNumber(studentNumber);
            for (ModuleGrade moduleGrade : moduleGrades) {
                System.out.println("Module Name: " + moduleGrade.getModule().getModuleName() + " | Module Grade: " + moduleGrade.getFinalLetterGrade());
            }
        } catch (ModuleGradeNotFoundException | StudentNotFoundException ex) {
            System.out.println("An error has occurred: " + ex.getMessage());
        }
        System.out.println();
    }
}

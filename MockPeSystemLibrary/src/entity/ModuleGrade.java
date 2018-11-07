/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lawrence
 */
@Entity
public class ModuleGrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleGradeId;
    
    @Column(length = 2)
    private String finalLetterGrade;
    
    @ManyToOne
    private Student student;
    @ManyToOne
    private Module module;

    public Long getModuleGradeId() {
        return moduleGradeId;
    }

    public void setModuleGradeId(Long moduleGradeId) {
        this.moduleGradeId = moduleGradeId;
    }

    public ModuleGrade() {
    }

    public ModuleGrade(String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleGradeId != null ? moduleGradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the moduleGradeId fields are not set
        if (!(object instanceof ModuleGrade)) {
            return false;
        }
        ModuleGrade other = (ModuleGrade) object;
        if ((this.moduleGradeId == null && other.moduleGradeId != null) || (this.moduleGradeId != null && !this.moduleGradeId.equals(other.moduleGradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ModuleGrade[ id=" + moduleGradeId + " ]";
    }

    /**
     * @return the finalLetterGrade
     */
    public String getFinalLetterGrade() {
        return finalLetterGrade;
    }

    /**
     * @param finalLetterGrade the finalLetterGrade to set
     */
    public void setFinalLetterGrade(String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the module
     */
    public Module getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
    }
    
}

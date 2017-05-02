/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rimpl
 */
public class Answer implements Serializable{
    
    private String email;
    private int choice;
    private Date submissionDate;
    private String StudyCode;

    public String getStudyCode() {
        return StudyCode;
    }

    public void setStudyCode(String StudyCode) {
        this.StudyCode = StudyCode;
    }
    public Answer() {
        email="";
        choice=0;
        submissionDate=null;
    }

    
     public Answer(String email, int choice) {
        this.email = email;
        this.choice = choice;
       
     }
     
    public Answer(String email, int choice, Date submissionDate) {
        this.email = email;
        this.choice = choice;
        this.submissionDate = submissionDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
    
    
}

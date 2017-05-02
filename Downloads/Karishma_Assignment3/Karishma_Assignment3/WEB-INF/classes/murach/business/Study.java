/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.business;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rimpl
 */
public class Study implements Serializable{
    
    private String studyName;
    private String studyCode;
    private Date dateCreated;
    private String username;
    private String email;
    private String question;
    private int requestedParticipants;
    private int numofParticipants;
    private double minimum;
    private double maximum;
    private double average;
    private double sd;
    private String description;
    private String status;
    private String answerType;
    private String getImageURL;
    private ArrayList<Answer> answerList;
    private ArrayList<String> ansList;
    private Date reportDate;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
    private String reportStatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public String getGetImageURL() {
        return getImageURL;
    }

    public void setGetImageURL(String getImageURL) {
        this.getImageURL = getImageURL;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

    public ArrayList<String> getAnsList() {
        return ansList;
    }

    public void setAnsList(ArrayList<String> ansList) {
        this.ansList = ansList;
    }
    

    public Study() {
        studyName = "";
        studyCode = "";
        dateCreated = null;
        email = "";
        question = "";
        requestedParticipants = 0;
        numofParticipants = 0;
        description = "";
        status = "";
        answerType = "";
        getImageURL = "";
        minimum = 0.0;
        maximum = 0.0;
        average = 0.0;
        sd = 0.0;
        
        
    }
 public Study(String studyName, String studyCode, String email, String question, 
            int requestedParticipants, String description, String status) {
        this.studyName = studyName;
        this.studyCode = studyCode;
       // this.dateCreated = dateCreated;
        this.email = email;
        this.question = question;
        this.requestedParticipants = requestedParticipants;
     //   this.numofParticipants = numofParticipants;
        this.description = description;
        this.status = status;
      //  this.answerType = answerType;
       // this.answers = answers;
        //this.getImageURL = getImageURL;
       // this.minimum = minimum;
       // this.maximum = maximum;
        //this.average = average;
        //this.sd = sd;
    }
    
 
    
    
    
    public Study(String studyName, String studyCode,String email, String question, int numofParticipants, ArrayList<String> ansList, String description,String status) {
        this.studyName = studyName;
        this.studyCode = studyCode;
    //    this.dateCreated = dateCreated;
        this.email = email;
        this.question = question;
       // this.requestedParticipants = requestedParticipants;
      this.ansList=ansList;
        this.numofParticipants = numofParticipants;
        this.description = description;
     this.status = status;

    }
    

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }

    public int getNumofParticipants() {
        return numofParticipants;
    }

    public void setNumofParticipants(int numofParticipants) {
        this.numofParticipants = numofParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public ArrayList<Answer> getAnswers() {
        return answerList;
    }

    public void setAnswers(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }
    
    public void setgetImageURL(String getImageURL){
        
        this.getImageURL = getImageURL;
    }
    
    public String getgetImageURL()
    {
        return getImageURL;
    }
    
    public void setgetMinimum(double minimum){
        this.minimum = minimum;
    }
    
    public double getgetMinimum(){
        
        return minimum;
    }
    
    public void setgetMaximum(double maximum){
        this.maximum = maximum;
    }
    
    public double getgetMaximum(){
        
        return maximum;
    }
    
    public void setgetAverage(double average){
        this.average = average;
    }
    
    public double getgetAverage(){
        
        return average;
    }
    
    /**
     *
     * @param sd
     */
    public void setgetSD(double sd){
        this.sd = sd;
    }
    
    public double getgetSD(){
        
        return sd;
    }
    
    public void setAnswerObj(String email,int choice)
    {
        Answer ans=new Answer(email,choice);
        answerList.add(ans);
    }
    
    
}

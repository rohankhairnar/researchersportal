/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.business;

import java.io.Serializable;

/**
 *
 * @author rimpl
 */
public class User implements Serializable{
    private String name;
    private String email;
    private String password; 

     private int participants;

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }
    private String type;
    private int numCoins;
    private int numPostedStudies;
    private int numParticipation;

    public User() {
        name="";
        email="";
        type="";
        numCoins=0;
        numPostedStudies=0;
        numParticipation=0;
        
    }
    
     public User(String name, String email)       
    {
        
        this.email=email;
        this.name=name;
        this.numCoins=0;
        this.numPostedStudies=0;
        this.numParticipation=0;
        this.type = type;
    }
    

    public User(String name, String email, String type, int numCoins, int numPostedStudies, int numParticipation) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.numCoins = numCoins;
        this.numPostedStudies = numPostedStudies;
        this.numParticipation = numParticipation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
 public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getNumCoins() {
        return numCoins;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public int getNumPostedStudies() {
        return numPostedStudies;
    }

    public void setNumPostedStudies(int numPostedStudies) {
        this.numPostedStudies = numPostedStudies;
    }

    public int getNumParticipation() {
        return numParticipation;
    }

    public void setNumParticipation(int numParticipation) {
        this.numParticipation = numParticipation;
    }
    
    
     
}

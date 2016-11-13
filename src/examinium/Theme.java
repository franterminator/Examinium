/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinium;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Fran
 */
public class Theme implements Serializable{
    public String name;
    public int id;
    public ArrayList<String> questions;
    private transient int n;                        //count the question we want
    
    /**
     * Theme constructer
     * @param name
     * @param id 
     */
    public Theme (String name, int id) {
        this.name = name;
        this.id = id;
        this.questions = new ArrayList<String>();
        
        this.n=0;
    }

    /** 
     * Add questions to the theme
     * @param question 
     */
    public void addQuestion(String question) {
        questions.add(question);
    }
    
    /**
     * Generate the name of the ser file
     * @return 
     */
    public String getFileName () {
        return String.format("%d_%s.ser", id,name);
    }

    /**
     * Get a question until we reach the last one
     * @return 
     */
    public String getQuestion () {
        if(n<questions.size()) {
            return questions.get(n++);
        }
        else
            return null;
    }

    public void shuffle() {
        Collections.shuffle(questions);
    }
    
    /* Normal getter and setter */
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
    public void setN (int n) {
        this.n = n;
    }
}

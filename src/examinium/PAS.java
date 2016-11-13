/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class PAS {
    /* PAS = Parse and Serialization */

    /**
     * Parse a file and get the theme
     * @param f
     * @param name
     * @param id
     * @return 
     */
    public static Theme parseFile (File f, String name, int id) {
        Theme theme = new Theme(name,id);
        StringBuilder question = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(f),StandardCharsets.ISO_8859_1));
            
            String line;
            while((line=br.readLine())!=null){
                if(line.startsWith("*")){
                    if(question.length() != 0)
                        theme.addQuestion(question.toString());
                    question.setLength(0);
                }
                else {
                    question.append(line).append("\n");
                }
            }
            if(question.length() != 0)
                theme.addQuestion(question.toString());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error finding the file to parse",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error reading the file to parse",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        }
        
        
        return theme;
    }
    
    /**
     * Read a theme serializated
     * @param file
     * @return 
     */
    public static Theme inFile(String file) {
        FileInputStream fis = null;
        Theme theme = null;
        try {
            fis = new FileInputStream("./themes/"+file);
            ObjectInputStream input = new ObjectInputStream(fis);
            
            theme = (Theme)input.readObject();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error finding the themes folder",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } catch (IOException io) {
            io.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error with some themes format input",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error finding the ser file",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(PAS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        return theme;
    }
    
    /**
     * Output the theme object in the themes folder
     * @param theme 
     */
    public static void outFile(Theme theme) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./themes/"+theme.getFileName());
            ObjectOutputStream out = new ObjectOutputStream(fos);
            
            out.writeObject(theme);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error finding the themes folder",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null,"Error with some themes format output",
                    "WARNING!",JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(PAS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

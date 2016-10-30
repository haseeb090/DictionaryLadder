/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apassignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

/**
 *
 * @author Haseeb Khizar
 */
public class DictionaryLadder. {


    static Set<String> dict = new HashSet<>();
    
    static class node{ //node for linked list to link ladder
        String word;
        int numSteps;
 
        public node(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
        }
    }
    
    public int ladderLength(String start, String end, Set<String> hdict) {
        Set<String> dict = hdict;
        if (start==null||end==null||dict==null||start.length()==0||start.length()!=end.length()){
            return 0;
        }
        
    
        
        Queue<String> qToOffer=new LinkedList<String>();
        Queue<String> qToPoll=new LinkedList<String>();
        
        int i=0;
        
        
        qToPoll.offer(start);
        while (!qToPoll.isEmpty()||!qToOffer.isEmpty()){
            
           while(!qToPoll.isEmpty()){    
               String current=qToPoll.poll();
           
               char[] sChars=current.toCharArray();
            
               for (int j=0; j<sChars.length; j++){
                   
                   char original=sChars[j];
                   
                     for (char c='A'; c<='Z'; c++){
                         if (c==sChars[j]){
                             continue;
                         }
                         
                         sChars[j]=c;
                         String tempStr=String.copyValueOf(sChars);
                         
                         if (tempStr.equals(end)){
                             return i+1;
                         }
                         
                        if (dict.contains(tempStr)){
                            qToOffer.offer(tempStr);
                            // if not remove tempStr, a loop may happened
                            dict.remove(tempStr);
                        }
                        
                   }
                   
                   // don't forget to recover the sChars to orginal sChars
                   sChars[j]=original;
             }
          }
         // level++
          i++;
          Queue<String> tempQ=qToPoll;
          qToPoll=qToOffer;
          qToOffer=tempQ;
          
        }
        
        return 0;
    
    }
 
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    
    
   
    
    public static void populate(Set<String> dict){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("pg29765.txt"))) {//buffered reader to parse csv file
            line = br.readLine();
            while ((line = br.readLine()) != null) {//loop to parse through lines of the dict
                String[] words = line.split("[^A-Z]");//splitting the whole line into a string array
                for(int i=0; i<words.length;i++){
                    if(words[i].length()<3){
                        continue;
                    }
                    dict.add(words[i]);                      
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void solve(Set<String> dict){
        String[] s = dict.toArray(new String[dict.size()]);
        for(int i = 0; i < s.length;i++){
            for(int j =0;j<s.length;j++){
                if (j == i){
                    continue;
                }
                try (FileWriter fw = new FileWriter("wordsteps.csv", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    int l = 0;
                    if(s[i].length() == s[j].length()){
                        l = ladderLength(s[i], s[j], dict);
                    } else {
                        continue;
                    }
                    //System.out.println(l);
                    if(l!=0){
                     out.println(s[i] + "," + s[j] + "," + l);	
                    }
                } catch (Exception e) { 
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        populate(dict);
        APAssignment1 a = new APAssignment1();
        a.solve(dict);
    }
    
}

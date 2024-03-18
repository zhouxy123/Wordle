import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Title      : WordleFunctions.java
 * Description: This class contains some useful functions in the game.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class WordleFunctions{
    /** This method can locate the content at certain line in a file.
     *  @param fileName The file's name you want to search.
     *  @param lineNumber The number of a certain line in the file.
     *  @return String  The content you want.
     */
    public static String locateWord(String fileName, int lineNumber) throws IOException { 
        BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
        String word = reader.readLine();
        if (lineNumber <= 0 || lineNumber > getTotalLines(fileName)){ 
            return null;
            }
        int num = 0;
        while (word != null){ 
            if (lineNumber == ++num) {
                return word;
            }
            word = reader.readLine();
        }
        reader.close(); 
        return word;
    }

    /** This method can tell you how many lines a file has in total.
     *  @param fileName The file's name you want to search.
     *  @return int  The number of lines of the file.
     */
    public static int getTotalLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        LineNumberReader reader = new LineNumberReader(br);
        String s = reader.readLine();
        int lines = 0;
        while (s != null){
            lines++;
            s = reader.readLine(); 
        }
        reader.close(); 
        br.close();
        return lines;
    }

    /** This method can get the content at a line randomly in a file.
     *  @param fileName The file's name you want to search.
     *  @return String  The content at a random line in a file.
     */
    public static String getRandomWord(String fileName) throws IOException{
        Random rand = new Random();
        String target = locateWord(fileName,rand.nextInt(getTotalLines(fileName))+1);
        System.out.println("Answer:"+target);
        return target;
    }
    
    /** This method can compare two strings: your input and answer, then 
     *  response the result on the game interface.
     *  There are three colours that the cells containing the letters can be:
     *  1. Green means the letter is contained in the word and is in that position.
     *  2. Yellow means the letter is contained in the word but not in that position.
     *  3. Grey means the letter is not contained in the word.
     *  @param input     The word you input.
     *  @param target    The target word, or the answer.
     *  @param textfield The region you can input letters.
     *  @param line      The certain line of textfield that the program needs to handle.
     *  @return boolean  Whether the input and answer same. "True" means "same", or you win.
     */
    public static boolean strCompare(String input,String target,JTextField[][] textfield,int line){
        StringBuffer sbInput = new StringBuffer(input);
        StringBuffer sbTarget = new StringBuffer(target);
        int i,j;
        int[] status = {0,0,0,0,0};
        for1:for(i = 0; i<input.length();i++){
            for2:for(j = 0; j<target.length();j++){
                if(sbInput.charAt(i)==sbTarget.charAt(j)){
                    if(i==j){
                        status[i] = 2;
                        continue for1;
                    }
                    else {
                        status[i] = 1;
                    }
                }
            }
        }
        for(i = 0; i<input.length();i++){
            switch(status[i]){
                case 0:
                    textfield[line][i].setBackground(Color.gray);
                    break;
                case 1:
                    textfield[line][i].setBackground(Color.yellow);
                    break;
                case 2:
                    textfield[line][i].setBackground(Color.green);
                    break;     
            }
            if(line<5){
                textfield[line][i].setEditable(false);
                textfield[line+1][i].setEditable(true);
            }
        }   
        if(line <5){
            line++;
        }
        if(input.equals(target))
        return true;
        else return false;
    }

    /** This method can lock all the textfields, make them uneditable.
     *  @param textfield The textfield array that needs to be locked.
     */
    public static void lock(JTextField[][] textfield){
        for(int i=0;i<6;i++){
            for(int j=0;j<5;j++){
                textfield[i][j].setEditable(false);
            }
        }
    }

    /** This method can judge that whether a word exists.
     *  @param str The word to be judged.
     *  @return boolean  "True" means "exists", "false" means "does not exists".
     */
    public static boolean findWord(String str) throws IOException{
        boolean ifExist = false;
        for(int i=1;i<=getTotalLines("wordlist.txt");i++){
            if(str.equals(locateWord("wordlist.txt",i))){
                ifExist = true;
                return ifExist;
            }
        }
        return ifExist;
    }

    /** This method can judge that whether a word contains invalid characters.
     *  @param str The word to be judged.
     *  @return boolean  "True" means the word only contains lower English letters, 
     *                   "false" means the word contains invalid characters.
     */
    public static boolean judgeValid(String str){
        boolean ifValid = true;
        StringBuffer sb = new StringBuffer(str);
        for(int i=0;i<str.length();i++){
            if(sb.charAt(i)<'a'||sb.charAt(i)>'z'){
                ifValid = false;
                break;
            }
        }
        return ifValid;
    }

    /** This method can judge that whether a word has 5 letters.
     *  @param str The word to be judged.
     *  @return boolean  "True" means the word has 5 letters,
     *                   "false" means the word is not a 5-letter word.
     */
    public static boolean judgeLength(String str){
        if(str.length()==5){
            return true;
        }
        return false;
    }
}    

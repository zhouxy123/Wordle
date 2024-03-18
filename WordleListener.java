import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Title      : WordleListener.java
 * Description: This class contains the event handler of game interface.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class WordleListener implements ActionListener{
    // Declaration of instance variables.
    Wordle myWordle = new Wordle(); //Used for passing parameters.
    private int line = 0;            //Line number.

    /** This is the constructor for the event handler.
     *  @param main The main interface that is related to this event handler.
     */
    public WordleListener(Wordle myWordle){
        this.myWordle = myWordle;
    }

    JTextField[] input = new JTextField[5];

    /** This method is to read the input into this event handler.
     *  @param input The textfield array that need to be pass into this event handler.
     */
    public void readInput(JTextField[] input){
        this.input=input;
    }

    /** This is the getter of the line you are handling.
     *  @return int  The line number.
     */
    public int getLine(){
        return this.line;
    }

    /** This is the event-handling method. 
     *  @param event The event that needs to be handled.
     */
    public void actionPerformed(ActionEvent event){
    switch(event.getActionCommand()){
    /** If you click the ENTER button, you can handle what you input as a string.
     *  Then you can get the response.
     */
    case "ENTER":
        String inputWord = "";
        for(int i=0;i<5;i++){
            String inputChar = myWordle.input[line][i].getText();
            inputWord += inputChar;
        }
        
        WordleFunctions func = new WordleFunctions();
        Dialogs reminder = new Dialogs();

        /** If your input is invalid, it will not be compared with the answer, and you
         *  should input it again.
         */
        int error = 0;
        if(func.judgeLength(inputWord)==false){
            reminder.lengthError();
            error=1;
        }
        else if(func.judgeValid(inputWord)==false){
            reminder.invalidChar();
            error=1;
        }
        else{
            try{
                if(func.findWord(inputWord)==false){
                    reminder.notExistWord();
                    error=1;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //If your input is valid, it will be compared with the answer.
        if(error == 0){
            boolean win = func.strCompare(inputWord,myWordle.answer,myWordle.input,line);
            if(win){
                func.lock(myWordle.input);
                reminder.success();
            }
            if(win == false && line == 5){
                func.lock(myWordle.input);
                reminder.lose(myWordle.answer);
            }
            if(this.line<5)
            this.line++;
        }
        break;

    /** If you click the EXIT button, you can return to the main interface.
     *  And your input will not be saved.   
     */ 
    case "EXIT":
        try{
            new MainInterface().initMain();
        }catch(IOException e){
            e.printStackTrace();
        }
        myWordle.setVisible(false);
        break;

    //If you click the RESTART button, you can restart the game.
    case "RESTART":
        try{
            new Wordle().init();
            this.line = 0;
        }catch(IOException e){
            e.printStackTrace();
        }
        myWordle.setVisible(false);
        break;    
        }
    }    
}

//Some dialogs.
class Dialogs extends JDialog{
    /** This method can generate a dialog reminds you that the number of the 
     *  letters of the word you just inputed is wrong(not a 5-letter word).
     */
    public void lengthError(){
        setBounds(100,100,500,100);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel text = new JLabel("The word you input must contains 5 letters! Please input again!");
        text.setSize(500,100);
        container.add(text);
        this.setTitle("Warning");
        this.setVisible(true);
    }

    /** This method can generate a dialog reminds you that the word you just inputed 
     *  cantains invalid characters.
     */
    public void invalidChar(){
        setBounds(100,100,500,100);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel text = new JLabel("The word you input can only contain lower letters! Please input again!");
        text.setSize(500,100);
        container.add(text);
        this.setTitle("Warning");
        this.setVisible(true);
    }

    /** This method can generate a dialog reminds you that the word you just inputed 
     *  does not exist. Maybe your spelling is wrong.
     */
    public void notExistWord(){
        setBounds(100,100,500,100);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel text = new JLabel("The word you input doesn't exist! Please check your spelling and input again!");
        text.setSize(500,100);
        container.add(text);
        this.setTitle("Warning");
        this.setVisible(true);
    }

    /** This method can generate a dialog reminds you that you win the game.
     */
    public void success(){
        setBounds(100,100,500,100);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel text = new JLabel("Congratulations! You win! Click RESTART to play again!");
        text.setSize(500,100);
        container.add(text);
        this.setTitle("You win!");
        this.setVisible(true);
    }

    /** This method can generate a dialog reminds you that you lose the game and 
        tells you the right answer.
        @param answer The right answer.
     */
    public void lose(String answer){
        setBounds(100,100,500,100);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel text = new JLabel("You lose! The answer is "+answer+" . Click RESTART to play again!");
        text.setSize(500,100);
        container.add(text);
        this.setTitle("You lose!");
        this.setVisible(true);
    }
}

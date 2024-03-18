import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

/**
 * Title      : Wordle.java
 * Description: This class contains the definition and initializing method of the game interface 
                and automatically jumping method between two adjacent textfields.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class Wordle extends JFrame{
    // Declaration of instance variables.
    static String answer;   //The answer of a wordle, which is generated randomly.
    static JTextField[][] input = new JTextField[6][5]; //Textfields that can get input.

    /** This method is used for initializing the game interface.
     */
    public void init() throws IOException{
        WordleListener wordleListener = new WordleListener(this);
        setTitle("MyWordle");
        setSize(600,800);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //Buttons.
        JButton enter = new JButton("ENTER"); 
        JButton restart = new JButton("RESTART");
        JButton exit = new JButton("EXIT");
        enter.setBounds(200,600,200,100);
        restart.setBounds(480,20,100,20);
        exit.setBounds(20,20,100,20);
        enter.addActionListener(wordleListener);
        restart.addActionListener(wordleListener);
        exit.addActionListener(wordleListener);
        add(enter);
        add(restart);
        add(exit);

        //The background under all textfields.
        JPanel words = new JPanel();
        words.setBounds(50,50,500,500);
        words.setBackground(Color.black);
        words.setLayout(new GridLayout(6,5));
        getContentPane().add(words);
        
        //Initialize textfields.
        for(int i=0;i<6;i++){
            for(int j=0;j<5;j++){
                input[i][j] = new JTextField(1);
                input[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                input[i][j].setFont(new Font("Calibri",Font.BOLD,40));
                words.add(input[i][j]);
            }
        }
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                input[i][j].setEditable(false);
            }
        }
        textfieldJump(input);
        wordleListener.readInput(input[wordleListener.getLine()]);

        //Generate the answer.
        WordleFunctions func = new WordleFunctions();
        answer = func.getRandomWord("wordlist.txt");
    }

    /** This method makes the focus can jump to the next textfield automatically
     *  after inputing a letter.
     *  @param textField You should add the method to it.
     */
    public static void textfieldJump(JTextField[][] textField) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int i_final = i;
                int j_final = j + 1;
                textField[i][j].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (j_final < 5 && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                            textField[i_final][j_final].grabFocus();
                        }
                    }
                });
            }
        }
    }
}

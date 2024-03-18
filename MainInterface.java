import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Title      : MainInterface.java
 * Description: This class contains the definition of the main interface 
                and initializing methods of main interface and game interface.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class MainInterface extends JFrame{

    /** This method is used for initializing the main interface.
     */
    public void initMain() throws IOException{
        setLayout(null);
        setSize(600,800);
        setTitle("MyWordle");
        MainListener mainListener = new MainListener(this);

        JLabel title = new JLabel("MyWordle");
        title.setFont(new Font("Calibri",Font.BOLD,40));
        title.setBounds(200,100,250,100);

        JButton start = new JButton("START");
        JButton help = new JButton("HELP");
        start.setBounds(200,300,200,100);
        help.setBounds(200,500,200,100);

        start.addActionListener(mainListener);
        help.addActionListener(mainListener);

        add(title);
        add(start);
        add(help);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    /** This method is used for initializing the game interface.
     */
    public void initWordle() throws IOException{
        Wordle wordle = new Wordle();
        wordle.init();
    }
}
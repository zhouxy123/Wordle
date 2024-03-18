import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Title      : MainListener.java
 * Description: This class contains the event handler of main interface.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class MainListener implements ActionListener{
    //Used for passing parameters.
    MainInterface main = new MainInterface();

    /** This is the constructor for the event handler.
     *  @param main The main interface that is related to this listener.
     */
    public MainListener(MainInterface main){
        this.main = main;
    }

    /** This is the event-handling method. 
     *  @param event The event that needs to be handled.
     */
    public void actionPerformed(ActionEvent event){
        switch(event.getActionCommand()){
        //If you click the START button, you can get into the game interface.    
        case "START":
            try{
                new MainInterface().initWordle();
            }catch(IOException e){
                e.printStackTrace();
            }
            main.setVisible(false);
            break;

        //If you click the HELP button, you can see the "help" dialog.    
        case "HELP":
            new Help();
        }
    }
}

//The defination of "help" dialog.
class Help extends JDialog{
    public Help(){
        setBounds(100,100,500,500);
        Container container = this.getContentPane();
        container.setLayout(null);
        JLabel help = new JLabel("help");
        help.setSize(200,200);
        container.add(help);
        this.setTitle("Help");
        this.setVisible(true);
    }
}

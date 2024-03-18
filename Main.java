import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Title      : Main.java
 * Description: This class is the main class of the game, which can initialize a main interface.
 * Copyright  : Copyright (c) 2006-2022
 * @author      Xiangyi Zhou
 * @version     1.0
 */
public class Main{
    public static void main(String[] args) throws IOException{
        MainInterface mainInterface = new MainInterface();
        mainInterface.initMain();
    }
}
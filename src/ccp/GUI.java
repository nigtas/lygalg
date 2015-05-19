import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.io.*;


public class GUI {

    public JLabel labelList[][];

    public GUI(int[][] image) {
        final JFrame f = new JFrame("Frame Test");

        JPanel panel = new JPanel(new GridLayout(20, 20, 1, 1));

        labelList = new JLabel[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for(int j=0; j < image[0].length; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                labelList[i][j] = l;
                l.setBackground(Color.lightGray);
                // switch(image[i][j]) {
                //     case 2: 
                //         l.setBackground(Color.red);
                //         break;
                //     case 3: 
                //         l.setBackground(Color.yellow);
                //         break;
                //     case 4:
                //         l.setBackground(Color.blue);
                //         break;
                //     case 5 :
                //         l.setBackground(Color.green);
                //         break;
                //     case 6 :
                //         l.setBackground(Color.cyan);
                //         break;
                //     case 7 :
                //         l.setBackground(Color.black);
                //         break;
                //     case 8 :
                //         l.setBackground(Color.orange);
                //         break;
                // }
                l.setOpaque(true);
                panel.add(l);
            }
        }


        f.setContentPane(panel);
        f.setSize(750, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void update(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for(int j=0; j < image[0].length; j++) {
                // JLabel l = new JLabel("", JLabel.CENTER);
                JLabel l = labelList[i][j];
                switch(image[i][j]) {
                    case 2: 
                        l.setBackground(Color.red);
                        break;
                    case 3: 
                        l.setBackground(Color.yellow);
                        break;
                    case 4:
                        l.setBackground(Color.blue);
                        break;
                    case 5 :
                        l.setBackground(Color.green);
                        break;
                    case 6 :
                        l.setBackground(Color.cyan);
                        break;
                    case 7 :
                        l.setBackground(Color.black);
                        break;
                    case 8 :
                        l.setBackground(Color.orange);
                        break;
                    case 9 :
                        l.setBackground(Color.magenta);
                        break;
                    case 10 :
                        l.setBackground(Color.pink);
                        break;
                    case 11 :
                        l.setBackground(Color.gray);
                        break;
                    case 12 :
                        l.setBackground(Color.darkGray);
                        break;
                    case 13 :
                        l.setBackground(Color.white);
                        break;
                    case 14 :
                        l.setBackground(new Color(0.2f, 0.5f, 0.7f));
                        break;
                    case 15 :
                        l.setBackground(new Color(0.7f, 0.5f, 0.7f));
                        break;
                    case 16 :
                        l.setBackground(new Color(0.5f, 0.2f, 0.7f));
                        break;
                    case 17 :
                        l.setBackground(new Color(0.8f, 0.3f, 0.7f));
                        break;
                    case 18 :
                        l.setBackground(new Color(0.24f, 0.55f, 0.4f));
                        break;
                    case 19 :
                        l.setBackground(new Color(0.224f, 0.77f, 0.5f));
                        break;
                    case 20 :
                        l.setBackground(new Color(0.8f, 0.45f, 0.2f));
                        break;
                    case 21 :
                         l.setBackground(new Color(0.1f, 0.35f, 0.7f));
                        break;
                    case 22 :
                         l.setBackground(new Color(0.4f, 0.75f, 0.249f));
                        break;
                    case 23 :
                     l.setBackground(new Color(0.99f, 0.15f, 0.333f));
                        break;
                    case 24 :
                     l.setBackground(new Color(0.246f, 0.74f, 0.71f));
                        break;
                    default :
                        l.setBackground(Color.lightGray);
                        break;
                }
            }
        }
    }
}
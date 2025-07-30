 package notepad;

import javax.swing.*;
import java.awt.*;
 import java.awt.event.*;

public class About extends JFrame  implements ActionListener{

    About() {
        setTitle("About Notepad");
        setBounds(400, 100, 600, 500);
        setLayout(null);

        // ✅ Corrected image path with leading slash
        ImageIcon i1 = new ImageIcon(getClass().getResource("/notepad/ions/windows.png"));

         //✅ Proper scaling of the image
        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // ✅ Set scaled icon to label
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(70, 40, 400, 80); // Use the scaled width and height
        add(headerIcon);
 
        ImageIcon i4 =new ImageIcon(ClassLoader.getSystemResource("notepad/ions/notes.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel icon = new JLabel(i6);
        icon.setBounds(50,180,70,70);
        add(icon);
        JLabel text = new JLabel("<html>code for Interview<br>Version 0.1.0 (OS Build Java)<br>Code for Interview .All rights reserved</html");
        text.setBounds(150,100,500,300);
        text.setFont( new Font("SAN_SERIF",Font.PLAIN,16));
        add(text);
        JButton b1 = new JButton("OK");
        b1.setBounds(150,350,120,25);
        add(b1);
        
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new About();
    }
}

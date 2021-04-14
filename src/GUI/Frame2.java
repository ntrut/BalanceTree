package GUI;

import Application.FinalAwnser;
import Application.Similiarities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame2 extends JFrame implements ActionListener
{

    final JTextField textField;
    final JTextField textFieldClusterIndex;
    final JTextField forK;
    final JButton button;
    final JPanel panel4;
    final JTextArea output;

    public Frame2() throws HeadlessException
    {

        JLabel text = new JLabel();
        text.setText("Business Name: ");
        text.setFont(new Font("Courier", Font.BOLD, 15));
        text.setBounds(67,15,200,75);

        JLabel textk = new JLabel();
        textk.setText("Value k: ");
        textk.setFont(new Font("Courier", Font.BOLD, 15));
        textk.setBounds(120,80,200,75);

        JLabel textCluster = new JLabel();
        textCluster.setText("Cluster Index: ");
        textCluster.setFont(new Font("Courier", Font.BOLD, 15));
        textCluster.setBounds(70,155,200,75);
        //text.setVerticalAlignment(JLabel.TOP);



        JLabel text2 = new JLabel();
        text2.setText("Similarities: ");
        text2.setBounds(0,0,50,75);
        text2.setHorizontalAlignment(JLabel.RIGHT);

        /*Textfield for the input*/
        textFieldClusterIndex = new JTextField();
        textFieldClusterIndex.setFont(new Font("Courier", Font.PLAIN, 18));

        textField = new JTextField();
        textField.setFont(new Font("Courier", Font.PLAIN, 18));

        forK = new JTextField();
        forK.setFont(new Font("Courier", Font.PLAIN, 18));

        /*The text field where the output will be displayed, cant type it in*/
        output = new JTextArea();
        output.setFont(new Font("Courier", Font.PLAIN, 14));
        output.setEditable(false);
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        /*business name panel*/
        JPanel panel = new JPanel();
        panel.setBounds(0,0,200,250);
        //panel.setBackground(Color.BLUE);
        panel.setLayout(new BorderLayout());
        panel.setLayout(null);

        /*Textfield panel*/
        JPanel panel2 = new JPanel();
        panel2.setBounds(200,35,400,40);
        panel2.setLayout(new BorderLayout());

        /*Textfield panel*/
        JPanel panel5 = new JPanel();
        panel5.setBounds(200,100,400,40);
        panel5.setBackground(Color.DARK_GRAY);
        panel5.setLayout(new BorderLayout());

        /*Textfield panel*/
        JPanel panel6 = new JPanel();
        panel6.setBounds(200,175,400,40);
        panel6.setBackground(Color.PINK);
        panel6.setLayout(new BorderLayout());


        /*Text output panel*/
        JPanel panel3 = new JPanel();
        panel3.setBounds(0,250,200,50);
        panel3.setLayout(new BorderLayout());

        /*output for all of the businesses names panel*/
        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.setBounds(200,250,550,300);
        panel4.setLayout(new BorderLayout());


        /*Button for submit*/
        button = new JButton("Submit");
        button.setBounds(620,100,100,40);
        button.addActionListener(this);

        /*default*/
        panel.add(text);
        panel.add(textk);
        panel.add(textCluster);
        panel2.add(textField);
        panel3.add(text2);
        panel4.add(output);
        panel5.add(forK);
        panel6.add(textFieldClusterIndex);
        this.setTitle("365 Assignment 1");
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,600);
        this.setVisible(true);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button)
        {
            output.selectAll();
            output.replaceSelection("");
            String inputText = textField.getText();
            int clusterIndex = Integer.parseInt(textFieldClusterIndex.getText());
            int valueK = Integer.parseInt(forK.getText());
            Similiarities test = new Similiarities();

            System.gc();

        }
    }

    public void set_Text()
    {
        output.setText("Searching for similarity...\n");
    }
}



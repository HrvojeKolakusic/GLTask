package task.gui;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GLMainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 162972389684534069L;
	
	private JLabel l1, l2, l3;
	public JTextArea output;

	public GLMainWindow() {
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("L O G I C task");
		setLocationRelativeTo(null);
		
		l1 = new JLabel("Input type:");
		l1.setBounds(25, 25, 100, 30);
		add(l1);
		
		JRadioButton rb1 = new JRadioButton("Direct string input");
		JRadioButton rb2 = new JRadioButton("Full path to .txt file");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		rb1.setSelected(true);
		
		rb1.setBounds(35, 65, 200, 30);
		rb2.setBounds(35, 105, 200, 30);
		
		add(rb1);
		add(rb2);
		
		l2 = new JLabel("Input:");
		l2.setBounds(25, 140, 50, 30);
		add(l2);
		
		JTextField input = new JTextField();
		input.setBounds(85, 140, 380, 30);
		add(input);
		
		l3 = new JLabel("Output:");
		l3.setBounds(25, 180, 50, 30);
		add(l3);
		
		output = new JTextArea();
		output.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(15, 220, 450, 300);
		add(scroll);
		
		
		setLayout(null);
		setVisible(true);
	}

	
}

package task.gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import task.algorithm.LogicTask;

public class GLMainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 162972389684534069L;
	
	private JLabel l1, l2, l3;
	private JTextArea outputArea;
	private JButton start;
	private JTextField input;
	private JRadioButton rb1, rb2;

	public GLMainWindow() {
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("L O G I C task");
		setLocationRelativeTo(null);
		
		l1 = new JLabel("Input type:");
		l1.setBounds(25, 25, 100, 30);
		add(l1);
		
		rb1 = new JRadioButton("Direct string input");
		rb2 = new JRadioButton("Full path to .txt file");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		rb1.setSelected(true);
		
		rb1.setBounds(35, 55, 200, 30);
		rb2.setBounds(35, 85, 200, 30);
		
		add(rb1);
		add(rb2);
		
		l2 = new JLabel("Input:");
		l2.setBounds(25, 115, 50, 30);
		add(l2);
		
		input = new JTextField();
		input.setBounds(85, 115, 380, 30);
		add(input);
		
		start = new JButton("Start");
		start.setBounds(25, 150, 100, 30);
		start.addActionListener(e -> {
			String output = LogicTask.start(rb1.isSelected(), input.getText());
			outputArea.setText(output);
			
		});
		add(start);
		
		l3 = new JLabel("Output:");
		l3.setBounds(25, 180, 50, 30);
		add(l3);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);		
		JScrollPane scroll = new JScrollPane(outputArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(15, 210, 450, 300);
		add(scroll);
				
		setLayout(null);
		setVisible(true);
	}

	
}

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
	
	private JLabel l1, l2, l3, l4;
	private JTextArea outputArea;
	private JButton start;
	private JTextField input, charList;
	private JRadioButton rb1, rb2;

	public GLMainWindow() {
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GlobalLogic task");
		setLocationRelativeTo(null);
		
		l1 = new JLabel("Input type:");
		l1.setBounds(25, 25, 100, 30);
		add(l1);
		
		l4 = new JLabel("List of characters to look for:");
		l4.setBounds(200, 25, 200, 30);
		add(l4);
		
		charList = new JTextField();
		charList.setBounds(200, 55, 200, 30);
		charList.setToolTipText("use space separator");
		charList.setText("l o g i c");
		add(charList);
				
		rb1 = new JRadioButton("Direct string input");
		rb2 = new JRadioButton("Full path to .txt file");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		rb1.setSelected(true);
		rb1.setBounds(35, 55, 150, 30);
		rb2.setBounds(35, 85, 150, 30);
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
			outputArea.setText(LogicTask.start(rb1.isSelected(), input.getText(), charList.getText()));
			
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

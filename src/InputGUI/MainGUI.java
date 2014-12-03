package InputGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import fourvariable.*;
import fivevariable.*;

import javax.swing.JScrollBar;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainGUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() throws IOException{
		initialize();
		
		FileOutputStream cleartext = null;
		try {
			cleartext = new FileOutputStream("./result.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			cleartext.write(new String("").getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	String s="";
	String v="";
	String f[]={"0,0,0,0,0","0,0,0,1,0","0,0,1,0,0","0,0,1,1,0",
			"0,1,0,0,0","0,1,0,1,0","0,1,1,0,0","0,1,1,1,0",
			"1,0,0,0,0","1,0,0,1,0","1,0,1,0,0","1,0,1,1,0",
			"1,1,0,0,0","1,1,0,1,0","1,1,1,0,0","1,1,1,1,0"};
	String g[]={"0,0,0,0,0,0","0,0,0,0,1,0","0,0,0,1,0,0","0,0,0,1,1,0",
			"0,0,1,0,0,0","0,0,1,0,1,0","0,0,1,1,0,0","0,0,1,1,1,0",
			"0,1,0,0,0,0","0,1,0,0,1,0","0,1,0,1,0,0","0,1,0,1,1,0",
			"0,1,1,0,0,0","0,1,1,0,1,0","0,1,1,1,0,0","0,1,1,1,1,0",
			"1,0,0,0,0,0","1,0,0,0,1,0","1,0,0,1,0,0","1,0,0,1,1,0",
			"1,0,1,0,0,0","1,0,1,0,1,0","1,0,1,1,0,0","1,0,1,1,1,0",
			"1,1,0,0,0,0","1,1,0,0,1,0","1,1,0,1,0,0","1,1,0,1,1,0",
			"1,1,1,0,0,0","1,1,1,0,1,0","1,1,1,1,0,0","1,1,1,1,1,0"};
	String fr[]={"0,0,0,0,0","0,0,0,1,0","0,0,1,0,0","0,0,1,1,0",
			"0,1,0,0,0","0,1,0,1,0","0,1,1,0,0","0,1,1,1,0",
			"1,0,0,0,0","1,0,0,1,0","1,0,1,0,0","1,0,1,1,0",
			"1,1,0,0,0","1,1,0,1,0","1,1,1,0,0","1,1,1,1,0"};
	String gr[]={"0,0,0,0,0,0","0,0,0,0,1,0","0,0,0,1,0,0","0,0,0,1,1,0",
			"0,0,1,0,0,0","0,0,1,0,1,0","0,0,1,1,0,0","0,0,1,1,1,0",
			"0,1,0,0,0,0","0,1,0,0,1,0","0,1,0,1,0,0","0,1,0,1,1,0",
			"0,1,1,0,0,0","0,1,1,0,1,0","0,1,1,1,0,0","0,1,1,1,1,0",
			"1,0,0,0,0,0","1,0,0,0,1,0","1,0,0,1,0,0","1,0,0,1,1,0",
			"1,0,1,0,0,0","1,0,1,0,1,0","1,0,1,1,0,0","1,0,1,1,1,0",
			"1,1,0,0,0,0","1,1,0,0,1,0","1,1,0,1,0,0","1,1,0,1,1,0",
			"1,1,1,0,0,0","1,1,1,0,1,0","1,1,1,1,0,0","1,1,1,1,1,0"};
	
	int ir,jr;
	
	
	private void initialize() throws IOException{
		frame = new JFrame();
		frame.setBounds(450, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setBounds(30, 44, 228, 19);
		textField.setColumns(10);
		
		
		
		
		
		JButton btnNewButton_1 = new JButton("A");
		btnNewButton_1.setBounds(30, 93, 50, 25);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText(textField.getText()+"A");
				s=s+"1";
			}
		});
		
		JButton btnNewButton_2 = new JButton("A~");
		btnNewButton_2.setBounds(30, 124, 50, 25);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"A~");
				s=s+"2";
			}
		});
		
		JButton btnNewButton_3 = new JButton("B");
		btnNewButton_3.setBounds(87, 93, 50, 25);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"B");
				s=s+"3";
			}
		});
		
		JButton btnNewButton_4 = new JButton("B~");
		btnNewButton_4.setBounds(87, 124, 50, 25);
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"B~");
				s=s+"4";
			}
		});
		
		JButton btnNewButton_5 = new JButton("C");
		btnNewButton_5.setBounds(144, 93, 50, 25);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"C");
				s=s+"5";
			}
		});
		
		JButton btnNewButton_6 = new JButton("C~");
		btnNewButton_6.setBounds(144, 124, 50, 25);
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"C~");
				s=s+"6";
			}
		});
		
		JButton btnNewButton_7 = new JButton("D");
		btnNewButton_7.setBounds(201, 93, 50, 25);
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"D");
				s=s+"7";
			}
		});
		
		JButton btnNewButton_8 = new JButton("D~");
		btnNewButton_8.setBounds(201, 124, 50, 25);
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"D~");
				s=s+"8";
			}
		});
		
		JButton btnNewButton_9 = new JButton("E");
		btnNewButton_9.setBounds(258, 93, 50, 25);
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"E");
				s=s+"9";
			}
		});
		
		JButton btnNewButton_10 = new JButton("E~");
		btnNewButton_10.setBounds(258, 124, 50, 25);
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"E~");
				s=s+"0";
			}
		});
		
		JLabel label = new JLabel("variable:");
		label.setBounds(39, 10, 49, 25);
		

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(93, 13, 46, 19);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				s="";
				for(ir=0;ir<16;ir++)
				{
					f[ir]=fr[ir];
				}
				for(jr=0;jr<32;jr++)
				{
					g[jr]=gr[jr];
				}
				FileOutputStream cleartext = null;
				try {
					cleartext = new FileOutputStream("./result.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					cleartext.write(new String("").getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(comboBox.getSelectedItem().toString()=="4")
				{
					v="4";
					btnNewButton_9.setVisible(false);
					btnNewButton_10.setVisible(false);			
				}
				else if(comboBox.getSelectedItem().toString()=="5")
				{
					v="5";
					btnNewButton_9.setVisible(true);
					btnNewButton_10.setVisible(true);
				}
				else
				{
					v="0";
				}
			}
		});
		comboBox.addItem("");
		comboBox.addItem("4");
		comboBox.addItem("5");
		
		JButton btnNewButton_11 = new JButton("+");
		btnNewButton_11.setBounds(347, 41, 65, 25);
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"+");
				s=s+"+";
			}
		});
		
		JButton btnNewButton_12 = new JButton("\u2190");
		btnNewButton_12.setBounds(347, 72, 65, 25);
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().equals(""))
				{
					textField.setText("");
				}
				else if(textField.getText().substring(textField.getText().length()-1,textField.getText().length()).equals("~"))
				{
					textField.setText(textField.getText().substring(0,textField.getText().length()-2));
					s=s.substring(0,s.length()-1);
				}
				else
				{
					textField.setText(textField.getText().substring(0,textField.getText().length()-1));
					s=s.substring(0,s.length()-1);
				}
			}
		});
		
		JButton btnNewButton_13 = new JButton("Clear");
		btnNewButton_13.setBounds(347, 103, 65, 25);
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				s="";
				for(ir=0;ir<16;ir++)
				{
					f[ir]=fr[ir];
				}
				for(jr=0;jr<32;jr++)
				{
					g[jr]=gr[jr];
				}
				FileOutputStream cleartext = null;
				try {
					cleartext = new FileOutputStream("./result.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					cleartext.write(new String("").getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
									
			}
		});
		
		//************************************************************************************************************
				JButton btnNewButton = new JButton("=");
				btnNewButton.setBounds(347, 134, 65, 25);
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						textField.setText(textField.getText()+"=");
						
						String[] sa=s.split("\\+");
						int i;
						if(v=="4")
						{
							for(i=0;i<sa.length;i++)
							{
								if(sa[i].length()==1)
								{
									One one=new One(f,sa[i]);
								}
								else if(sa[i].length()==2)
								{
									Two two=new Two(f,sa[i]);
								}
								else if(sa[i].length()==3)
								{
									Three three=new Three(f,sa[i]);
								}
								else if(sa[i].length()==4)
								{
									Four four=new Four(f,sa[i]);
								}
							}
							FileOutputStream fos=null;
							try {
								fos = new FileOutputStream("./result.txt",true);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    	OutputStreamWriter osw=new OutputStreamWriter(fos);
					    	try {
								osw.write("A,B,C,D,S");
								osw.write(13);
						        osw.write(10);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							for(i=0;i<16;i++)
							{
								try {
									osw.write(f[i]);
									osw.write(13);
							        osw.write(10);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try {
								osw.close();
								fos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
						}
						else if(v=="5")
						{
							for(i=0;i<sa.length;i++)
							{
								if(sa[i].length()==1)
								{
									OOne oone=new OOne(g,sa[i]);
								}
								else if(sa[i].length()==2)
								{
									TTwo ttwo=new TTwo(g,sa[i]);
								}
								else if(sa[i].length()==3)
								{
									TThree tthree=new TThree(g,sa[i]);
								}
								else if(sa[i].length()==4)
								{
									FFour ffour=new FFour(g,sa[i]);
								}
								else if(sa[i].length()==5)
								{
									FFive ffive=new FFive(g,sa[i]);
								}
							}
							FileOutputStream fos=null;
							try {
								fos = new FileOutputStream("./result.txt",true);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    	OutputStreamWriter osw=new OutputStreamWriter(fos);
					    	try {
								osw.write("A,B,C,D,E,S");
								osw.write(13);
						        osw.write(10);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							for(i=0;i<32;i++)
							{
								try {
									osw.write(g[i]);
									osw.write(13);
							        osw.write(10);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try {
								osw.close();
								fos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Please select the variable !!!",null,1);
							textField.setText(textField.getText().substring(0,textField.getText().length()-1));
						}
					}
				});
		panel.setLayout(null);
		panel.add(label);
		panel.add(comboBox);
		panel.add(btnNewButton_2);
		panel.add(btnNewButton_4);
		panel.add(btnNewButton_6);
		panel.add(btnNewButton_8);
		panel.add(btnNewButton_10);
		panel.add(btnNewButton_1);
		panel.add(btnNewButton_3);
		panel.add(btnNewButton_5);
		panel.add(btnNewButton_7);
		panel.add(btnNewButton_9);
		panel.add(textField);
		panel.add(btnNewButton_12);
		panel.add(btnNewButton_11);
		panel.add(btnNewButton_13);
		panel.add(btnNewButton);
	}
}
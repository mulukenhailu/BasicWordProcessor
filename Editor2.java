package nureProject1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Editor extends JFrame implements ActionListener{
	private JFrame frame=new JFrame();
	private JTextArea textarea=new JTextArea();
	
	private JScrollPane scroll=new JScrollPane();
	private JMenuBar menubar=new JMenuBar();
	private JMenu fileMenu=new JMenu("File");
	//////////////////////////////File menu items
	private JMenuItem openItem=new JMenuItem("Open");
	private JMenuItem saveItem=new JMenuItem("Save");
	private JMenuItem newItem=new JMenuItem("New");
	private JMenuItem exitItem=new JMenuItem("Exit");
	
	private JMenu editMenu=new JMenu("Edit");
	///////////////////////////Edit menu items
	private JMenuItem cutItem=new JMenuItem("Cut");
	private JMenuItem copyItem=new JMenuItem("Copy");
	private JMenuItem pasteItem=new JMenuItem("Paste");
	private JMenuItem selectItem=new JMenuItem("Select");
	
	private JMenu themeMenu= new JMenu("Themes");
	private JMenuItem darkTheme,yellowTheme,greenTheme,defaultTheme;
	private String [] fontsizes= GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private JComboBox fontTypeBox ;
	private JSpinner spinner=new JSpinner();
	private JLabel fontLabel;
	private JLabel fontLabel1;
	private JButton button1;
	
	Editor(){
		super();
		frame.setTitle("Editor");
		frame.setLocation(25,0 );
		frame.setLayout(new FlowLayout());
		frame.setSize(400,700);
	    frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//////////////////////////
		textarea=new JTextArea();
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(new Font(Font.SERIF,Font.BOLD,15));
		
		/////////////////////////////
	    /////////////////////////////////
	    JScrollPane scroll=new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(350,700));
	  
		
		/////////////////////////////////////
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(newItem);
		fileMenu.add(selectItem);
		fileMenu.add(exitItem);
        menubar.add(fileMenu);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        openItem.setMnemonic(KeyEvent.VK_O);
        newItem.setMnemonic(KeyEvent.VK_N);
        saveItem.setMnemonic(KeyEvent.VK_S);
        selectItem.setMnemonic(KeyEvent.VK_A);
        
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        newItem.addActionListener(this);
        selectItem.addActionListener(this);
        exitItem.addActionListener(this);
        
        editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectItem);
		menubar.add(editMenu);
		editMenu.setMnemonic(KeyEvent.VK_E);
		cutItem.setMnemonic(KeyEvent.VK_X);
		copyItem.setMnemonic(KeyEvent.VK_C);
		pasteItem.setMnemonic(KeyEvent.VK_P);
		selectItem.setMnemonic(KeyEvent.VK_A);
		
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		selectItem.addActionListener(this);
		
		darkTheme=new JMenuItem("DarkTheme");
		yellowTheme=new JMenuItem("YellowTheme");
		greenTheme=new JMenuItem("GreenTheme");
		defaultTheme=new JMenuItem("DefaultTheme");
		themeMenu.add(defaultTheme);
		themeMenu.add(darkTheme);
		themeMenu.add(yellowTheme);
		themeMenu.add(greenTheme);
		menubar.add(themeMenu);
		darkTheme.addActionListener(this);
		greenTheme.addActionListener(this);
		yellowTheme.addActionListener(this);
		defaultTheme.addActionListener(this);
		
		
		
		fontLabel=new JLabel("FontStyle");
		fontLabel.setForeground(Color.BLACK);
		fontLabel.setSize(50,50);
		fontLabel1=new JLabel("FontSize");
		fontLabel1.setSize(50,50);
        //////////////////////////// button
		button1=new JButton("Color");
		button1.setBackground(Color.PINK);
		button1.addActionListener(this);
		
		//////////////////////////spinner
	
	    fontTypeBox=new JComboBox(fontsizes);
		
		fontTypeBox.setSelectedItem("ETHIOPIC");
		fontTypeBox.addActionListener(this);
		
		spinner=new JSpinner();
		spinner.setPreferredSize(new Dimension(100,20));
		spinner.setValue(15);
		spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				
				textarea.setFont(new Font(textarea.getFont().getFamily(),Font.PLAIN,(int)spinner.getValue()));
				
			}});
			
	   
	    frame.add(button1);
	    frame.setJMenuBar(menubar);
        frame.add(fontLabel);
        frame.add(fontTypeBox);
        frame.add(fontLabel1);
        frame.add(spinner);
        frame.add(scroll);
		frame.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fontTypeBox) {
	    textarea.setFont(new Font((String)fontTypeBox.getSelectedItem(),Font.PLAIN,textarea.getFont().getSize()));
			
	
		}
		if(e.getSource()==cutItem) {
		textarea.cut();
		}
		if(e.getSource()==copyItem) {
			textarea.copy();
			}
		if(e.getSource()==pasteItem) {
			textarea.paste();
			}
		if(e.getSource()==selectItem) {
			textarea.selectAll();
			}
		if(e.getSource()==button1) {
			JColorChooser colorChooser=new JColorChooser();
			Color colerChoosed=JColorChooser.showDialog(null, "Colors", Color.red);
			textarea.setForeground(colerChoosed);
			
		}
		if(e.getSource()==saveItem) {
			JFileChooser fileChooser=new JFileChooser();
			
			fileChooser.setCurrentDirectory(new File("."));
		
			int accepter=fileChooser.showSaveDialog(null);
			if(accepter==JFileChooser.APPROVE_OPTION) {
				File file ;
				PrintWriter writer=null;
				
				file=new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					writer=new PrintWriter(file);
					writer.println(textarea.getText());
					
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					
				}
				finally {
					writer.close();
				}
				
			}
		
		}
		if(e.getSource()==openItem) {
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int accepter=fileChooser.showOpenDialog(null);
			if (accepter==JFileChooser.APPROVE_OPTION) {
				File file =new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileScanner=null;
				if(file.isFile()) {
					try {
						fileScanner=new Scanner(file);
						while(fileScanner.hasNext()) {
							String line =fileScanner.nextLine();
							textarea.append(line);
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println(e1.getMessage());
					}
					finally {
						fileScanner.close();
					}}}
			
		}
		if(e.getSource()==newItem) {
			String stringAccepter=textarea.getText();
			if(!stringAccepter.equals("")) {
				textarea.setText("");
			}
			
			
		}
		if (e.getSource()==exitItem) {
			System.exit(0);
			
		}
		if(e.getSource()==darkTheme) {
			textarea.setBackground(Color.DARK_GRAY);
		}
		if(e.getSource()==yellowTheme) {
			textarea.setBackground(Color.YELLOW);
			
		}
		if (e.getSource()==greenTheme) {
			textarea.setBackground(Color.GREEN);
		}
		if(e.getSource()==defaultTheme) {
			textarea.setBackground(Color.WHITE);
			
		}
		}
	

	
		
	
	}

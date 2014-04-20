package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.MouseAdapter;

public class MainFrame extends JFrame {
	
	/**
	 * Constants
	 */
	public static final int WIDTH = 1350;
	public static final int HEIGHT = 750;
	private final int LARGE_POLL_SIZE = 15;
	private final int SMALL_POLL_SIZE = 10;
	private final int GROUD_POLL_SIZE = 15;
	private final Color LARGE_POLL_COLOR = Color.RED;
	private final Color SMALL_POLL_COLOR = Color.GREEN;
	private final Color GROUD_POLL_COLOR = Color.ORANGE;
	private final int ROBOT_SIZE = 200;

	private JPanel contentPane;
	private Robot robot;
 	private JTextField textField;
	private JButton btnSubmit;
	private ArrayList<Scan> scans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		scans = new ArrayList<Scan>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		textField = new JTextField();
		menuBar.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				performCommand(textField.getText());
				
			}
		});
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				performCommand(textField.getText());
				
			}
		});
		menuBar.add(btnSubmit);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		robot = new Robot(getWidth()/2 - ROBOT_SIZE/2, getHeight() - ROBOT_SIZE/2, ROBOT_SIZE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(robot.getColor());
		g.fillArc(robot.getX(), robot.getY(), robot.getSize(), robot.getSize(), 0, 180);
		if(scans.size() != 0){
			for(SpaceObject o : scans.get(scans.size() - 1).getDetectedObjects()){
				g2d.setColor(LARGE_POLL_COLOR);
				g.fillRect(o.getX(), o.getY(), o.getSize(), o.getSize());
			}
		}
	}
	private void performCommand(String text) {
		textField.setText("");
		Scanner scan = new Scanner(text);
		while(scan.hasNext()){
			String command = scan.next();
			if(command.equals("move")){
				if(scan.hasNextInt()){
					int distance = scan.nextInt();
				}
			}
			else if(command.equals("rotate")){
				if(scan.hasNextInt()){
					int degrees = scan.nextInt();
				}
			}
			else if(command.equals("scan")){
				Random rand = new Random();
				int randX = rand.nextInt(WIDTH);
				int randY = rand.nextInt(HEIGHT);
				SpaceObject obj = new SpaceObject(randX, randY, LARGE_POLL_SIZE, LARGE_POLL_COLOR);
				Scan currScan = new Scan();
				currScan.addObject(obj);
				scans.add(currScan);
			}
		}
		scan.close();
		repaint();	
	}
}

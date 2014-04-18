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
	 * Frame Dimensions
	 */
	public static final int WIDTH = 1350;
	public static final int HEIGHT = 750;
	
	/**
	 * Poll constants
	 */
	private final int LARGE_POLL_SIZE = 15;
	private final int SMALL_POLL_SIZE = 10;
	private final int GROUD_POLL_SIZE = 15;
	private final Color LARGE_POLL_COLOR = Color.RED;
	private final Color SMALL_POLL_COLOR = Color.GREEN;
	private final Color GROUD_POLL_COLOR = Color.ORANGE;
	
	

	private JPanel contentPane;
	private Robot robot;
	private ArrayList<SpaceObject> polls;
	private ArrayList<Wall> walls;
 	private JTextField textField;
	private JButton btnSubmit;

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
		polls = new ArrayList<SpaceObject>();
		walls = new ArrayList<Wall>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		
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
				addWall();
				repaint();
			}
		});
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		robot = new Robot(getWidth()/2, getHeight()/2, 20);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(robot.getColor());
		g2d.fillOval(robot.getX(), robot.getY(), robot.getSize(), robot.getSize());
		g2d.setColor(Color.YELLOW);
		g.fillArc(robot.getX(), robot.getY(), robot.getSize(), robot.getSize(), 45 - robot.getDegrees(), 90);
		for(SpaceObject obj : polls){
			g2d.setColor(obj.getColor());
			g2d.fillOval(obj.getX(), obj.getY(), obj.getSize(), obj.getSize());
		}
		g2d.setColor(Color.BLACK);
		for(Wall obj : walls){
			g2d.drawLine(obj.getCoords1()[0], obj.getCoords1()[1], obj.getCoords2()[0], obj.getCoords2()[1]);
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
					robot.move(distance);
				}
			}
			else if(command.equals("rotate")){
				if(scan.hasNextInt()){
					int degrees = scan.nextInt();
					robot.rotate(degrees);
				}
			}
		}
		scan.close();
		repaint();
		
	}
	public void addLargePole(int x, int y){
		polls.add(new SpaceObject(x, y, LARGE_POLL_SIZE, LARGE_POLL_COLOR));
	}
	public void addSmallPole(int x, int y){
		polls.add(new SpaceObject(x, y, SMALL_POLL_SIZE, SMALL_POLL_COLOR));
	}
	public void addGroundPole(int x, int y){
		polls.add(new SpaceObject(x, y, GROUD_POLL_SIZE, GROUD_POLL_COLOR));
	}
	public void addWall(){
		walls.add(new Wall(robot.getX(), robot.getY(), robot.getDegrees()));
	}
}



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class EasterEgg {

	public EasterEgg() {
		GraphicWorld launch = new GraphicWorld("Game of Life", 1000, 600);
	}
	
}

class GraphicWorld extends JPanel implements MouseListener, ActionListener {

	JFrame myFrame;
	private final int rows=25, columns=40;
	private int	frameWidth, frameHeight;
	JButton[] button;
	private static final String[] BUTTON_STR = {"New Game", "Randomize", "Next"};
	private final String[] MENU_STR = {"File", "Rules"};
	private static final String[] RULE_STR={"Conway's","HighLife","B6/S16"};
	private JComboBox ruleSelector;
	private final int nbCells=rows*columns;
	private int ruleSelected = 0;
	private boolean checkStart=false;
	GameCell[] cell = new GameCell[nbCells];

	/**
	 *  This class constructor provides a set of methods, functions and variables for the designing a Graphic-Grid-World, 
	 *  for the initialization of the cells and their neighbors and implementation of GUI for visual presentation of 
	 *  these.
	 *  @param theTitle name of the window
	 *  @param theWidth width of the window
	 *  @param theHeight height of the window
	 *  */
	public GraphicWorld(String theTitle, int theWidth, int theHeight) {
		super();
		layoutSetup(theTitle, theWidth, theHeight);
		addMouseListener(this);
		// Initialization of cells
		int k=0;
		for (int i=0;i<rows;i++){                     
			for(int j=0;j<columns;j++){				  
				cell[k] = new GameCell (i,j);
//				System.out.println("Cell:"+k+" row:"+i+" column:"+j);
				k++;
			}
		}

		// Initialization of Neighbors
		for (int i=0;i<rows;i++){                     
			for(int j=0;j<columns;j++){				  
				int index = i*columns+j;
				/* Bound Check */
				// NW
				if(i>0 && j>0){
					cell[index].setNW(cell[(i-1)*columns+(j-1)]);
				}
				// N
				if(i>0){
					cell[index].setN(cell[(i-1)*columns+(j)]);
				}
				// NE
				if(i>0 && j<columns-1){
					cell[index].setNE(cell[(i-1)*columns+(j+1)]);
				}
				// W
				if(j>0){
					cell[index].setW(cell[i*columns+(j-1)]);
				}
				// E
				if(j<columns-1){
					cell[index].setE(cell[i*columns+(j+1)]);
				}
				// SW
				if(i<rows-1 && j>0){
					cell[index].setSW(cell[(i+1)*columns+(j-1)]);
				}
				// S
				if(i<rows-1){
					cell[index].setS(cell[(i+1)*columns+(j)]);
				}
				// SE
				if(i<rows-1 && j<columns-1){
					cell[index].setSE(cell[(i+1)*columns+(j+1)]);
				}
			}
		}
		myFrame.setVisible(true);
	}
	
	/**
	 * This method creates the actual World where cells can draw themselves 
	 * depending on the boolaen-state of the cells. 
	 * @param g paintComponent
	 *  */
	public void paintComponent(Graphics g) {

		// Painting Board
		g.setColor(Color.GRAY);
		int myWidth = getWidth();
		int myHeight = getHeight(); 
		int cellWidth = myWidth / columns;
		int cellHeight = myHeight / rows;
		if ((myWidth != frameWidth)||(myHeight != frameHeight)){
			frameWidth = columns*cellWidth;
			frameHeight = rows*cellHeight;
			setSize(frameWidth, frameHeight);
		}
		// Painting Rows & Columns
		g.fillRect(0, 0, frameWidth, frameHeight);
		g.setColor(getBackground());
		// draw rows.
		for (int k=0;k<rows;k++){
			g.drawLine(0, k*cellHeight, myWidth, k*cellHeight);
		}
		// draw columns.
		for (int k=0;k<columns;k++){
			g.drawLine(k*cellWidth, 0, k*cellWidth, frameHeight);
		} 
		// Draw the cells after identifying if they are alive or not.
		for(int k=0;k<cell.length;k++){
			if(cell[k].isAlive()){
				cell[k].draw(g, cellWidth, cellHeight);
			}
		}
	}
	
	/**
	 * This method is the layout setup that determines the size (based on width and height) and location of the menu,
	 * combo box, and panels of the window.
	 * @param theTitle The name of the window
	 * @param theWidth the width of the window
	 * @param theHeight the height of the window
	 *  */
	public void layoutSetup(String theTitle,int theWidth, int theHeight) {

		myFrame = new JFrame(theTitle) {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				paintComponents(g);
			}
		};
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(this, BorderLayout.CENTER);
		JPanel controlPanel = new JPanel(new GridLayout(8, 1));

		button = new JButton[BUTTON_STR.length];

		ruleSelector=new JComboBox(RULE_STR);
		controlPanel.add(ruleSelector);
		ruleSelector.addActionListener(this);

		for (int i=0; i<BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			controlPanel.add(button[i]);
		}
		
		// Control Panel to the right of the window.
		myFrame.add(controlPanel, BorderLayout.EAST); 

		JMenuBar menuBar = new JMenuBar();
		for (int i=0; i<MENU_STR.length; i++) {
			JMenu menu = new JMenu(MENU_STR[i]);
			if (i==0)
				menuBar.add(menu);
		}
		
		myFrame.add(menuBar, BorderLayout.NORTH); // add menuBar to the upper section of the BorderLayout(Window).
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	/**
	 * This method determines the input done by the user on the window and its panels.
	 * @param e Action event performed by the user input.
	 *  */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		boolean noButtonHit = true;
		for (int i=0; i<button.length; i++) {
			if (obj == button[i]) {
				// Start Button : Same as Randomize Button
				if(i==0){
					checkStart=true;
					for(int n=0;n<cell.length;n++){
						int randNum=(int)(Math.random()*8);
						if(randNum==1||randNum==2){
							cell[n].setAlive();
						}
						else
							cell[n].setDead();
					}
					myFrame.repaint();
				}
				//	Randomize Button
				if (i==1&&checkStart==true) {
					for(int n=0;n<cell.length;n++){
						int randNum=(int)(Math.random()*8);
						if(randNum==1||randNum==2){
							cell[n].setAlive();
						}
						else
							cell[n].setDead();
					}
					myFrame.repaint();
				}
				// "Next" Button
				else if (i==2&&checkStart==true) {
					for(int k=0;k<cell.length;k++){
						cell[k].computeNextState(ruleSelected);
					}
					for(int k=0;k<cell.length;k++){
						cell[k].updateState();
					}
					myFrame.repaint();					
				}
				noButtonHit = false;
			}
		}
		if (noButtonHit) {
			if (obj == ruleSelector) {
				ruleSelected = ruleSelector.getSelectedIndex();
			}
		}
	}
}

class GameCell{
	private int nbRow;
	private int nbCol;
	private boolean isAlive;
	private boolean wasAlive;
	private boolean nextAlive;
	private GameCell NW=null,N=null,NE=null,W=null,E=null,SW=null,S=null,SE=null;

	// Constructor
	public GameCell(int row, int col){
		nbRow=row;
		nbCol=col;
		isAlive=false;
	} 
	public void setAlive(){
		wasAlive = isAlive;
		isAlive=true;
	}
	public void setDead(){
		wasAlive=isAlive;
		isAlive=false;
	}
	public boolean isAlive(){
		return isAlive; 
	}
	public boolean wasAlive(){
		return wasAlive;
	}
	public void setNW(GameCell g){
		NW=g;
	}
	public void setN(GameCell g){
		N=g;
	}
	public void setNE(GameCell g){
		NE=g;
	}
	public void setW(GameCell g){
		W=g;
	}
	public void setE(GameCell g){
		E=g;
	}
	public void setSW(GameCell g){
		SW=g;
	}
	public void setS(GameCell g){
		S=g;
	}
	public void setSE(GameCell g){
		SE=g;
	}
	public boolean getNW(){
		if(NW==null){
			return false;
		}
		return NW.isAlive();
	}
	public boolean getN(){
		if(N==null){
			return false;
		}
		return N.isAlive();
	}
	public boolean getNE(){
		if(NE==null){
			return false;
		}
		return NE.isAlive();
	}
	public boolean getW(){
		if(W==null){
			return false;
		}
		return W.isAlive();
	}
	public boolean getE(){
		if(E==null){
			return false;
		}
		return E.isAlive();
	}
	public boolean getSW(){
		if(SW==null){
			return false;
		}
		return SW.isAlive();
	}
	public boolean getS(){
		if(S==null){
			return false;
		}
		return S.isAlive();
	}
	public boolean getSE(){
		if(SE==null){
			return false;
		}
		return SE.isAlive();
	}
	public GameCell[] getNeighbors(){
		GameCell[] neighbor = {NW,N,NE,E,SE,S,SW,W};
		return neighbor;
	}
	public void computeNextState(int rule) {
		/*Standard Rule*/
		if (rule == 0) {
			int count = 0;
			if (getW())
				count++;
			if (getNW())
				count++;
			if (getN())
				count++;
			if (getNE())
				count++;
			if (getE())
				count++;
			if (getSE())
				count++;
			if (getS())
				count++;
			if (getSW())
				count++;

			if ((count == 3)) {
				nextAlive = true;
			}
			else if(count==2||count==3)
				nextAlive = isAlive;
			else 
				nextAlive=false;
		}
		/*HighLife Rule*/
		if (rule == 1){
			int count = 0;
			if (getW())
				count++;
			if (getNW())
				count++;
			if (getN())
				count++;
			if (getNE())
				count++;
			if (getE())
				count++;
			if (getSE())
				count++;
			if (getS())
				count++;
			if (getSW())
				count++;

			if ((count==3) || (count==6)) {
				nextAlive = true;
			}
			else if(count==2||count==3)
				nextAlive = isAlive;
			else 
				nextAlive=false;
		}
		/*(B6/S16)*/
		if(rule==2){
			int count = 0;
			if (getW())
				count++;
			if (getNW())
				count++;
			if (getN())
				count++;
			if (getNE())
				count++;
			if (getE())
				count++;
			if (getSE())
				count++;
			if (getS())
				count++;
			if (getSW())
				count++;

			if (count==6) {
				nextAlive = true;
			}
			else if(count==1||count==6)
				nextAlive = isAlive;
			else 
				nextAlive=false;
		}
	}
	public void updateState() {
		wasAlive = isAlive;
		isAlive = nextAlive;
	}
	public void draw(Graphics g, int cellWidth, int cellHeight){
		if(isAlive){
			g.setColor(Color.BLUE);
			g.fillRect((cellWidth*nbCol+1), cellWidth*nbRow+ 1, cellWidth-1, cellHeight-1);
		}
		else {
		}
	}
}// End GameCell

package ̰����;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false;

	public static final int Rows=30;
	public static final int Cols=30;
	public static final int Block_Size=15;
	private Font fontGameOver = new Font("����", Font.BOLD, 50);
	private int score = 0;
	
	Snake s=new Snake(this);
	Egg e = new Egg();
	
	Image offScreenImage = null;
	public void launch(){
		this.setLocation(200,200);
		this.setSize(Cols*Block_Size,Rows*Block_Size);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}


		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		new Thread(paintThread).start();
	}
	
	public static void main(String[] args) {
		new Yard().launch();

	}
	
	public void stop() {
		gameOver=true;
	}
	
	@Override
	public void paint(Graphics g) {
	
			
				Color c = g.getColor();
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, Cols*Block_Size,Rows*Block_Size);
				g.setColor(Color.DARK_GRAY);
				//��������
				for(int i=1; i<Rows; i++) {
					g.drawLine(0, Block_Size * i, Cols*Block_Size,Block_Size* i);
				}
				for(int i=1; i<Cols; i++) {
					g.drawLine(Block_Size * i, 0, i*Block_Size,Rows*Block_Size);
				}
				
				g.setColor(Color.YELLOW);
				g.drawString("score:" + score, 10, 60);
				if(gameOver){
				g.setFont(fontGameOver);
				g.drawString("��Ϸ����", 120, 180);
				paintThread.gameOver();
			}	
				
			g.setColor(c);	
			s.eat(e);	
			e.draw(g);	
			s.draw(g);
			
			
	}
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Cols * Block_Size, Rows * Block_Size);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,  null);
	}

	
	private class PaintThread implements Runnable{
		private boolean running=true;
		public void run(){
			while(running){
			 repaint();
				try{
					Thread.sleep(100);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}

		
		public void gameOver() {
			running = false;
		}
	}
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			s.keyPressed(e);
		}
		}
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;		
	}
}
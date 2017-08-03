package Ã∞≥‘…ﬂ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	
	int row, col;
	int w = Yard.Block_Size;
	int h = Yard.Block_Size;
	private static Random r = new Random();
	private Color color = Color.GREEN;
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public Egg() {
		this(r.nextInt(Yard.Rows-2)+2, r.nextInt(Yard.Cols));
	}
	
	public void reAppear() {
		this.row = r.nextInt(Yard.Rows-2)+2;
		this.col = r.nextInt(Yard.Cols);
	}
	
	public Rectangle getRect() {
		return new Rectangle(Yard.Block_Size * col, Yard.Block_Size * row, w, h);
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillOval(Yard.Block_Size*col,Yard.Block_Size*row,w,h );
   	 g.setColor(c);
   	if(color == Color.GREEN) color = Color.RED;
	else color = Color.GREEN;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
}

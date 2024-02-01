import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Ball {
	private int x = 0, y = 0;
	private Color col = null;
	private Graphics g;
	private int[] dx = {1, 1, -1, -1, -1, 0, -1, 0, 1, -1, -1, 1};
	private int[] dy = {0, 1, 0, -1, -1, -1, 1, 1, -1, 1, -1, 1};
	
	/*private int[] dx = {1, -1, -1, 1};
	private int[] dy = {-1, 1, -1, 1};*/
	private int direction;
	
	public Ball(int x, int y, Color col, int direction, Graphics g) {
		this.x = x;
		this.y = y;
		this.col = col;
		this.g = g;
		this.direction = direction;
	}

	public void drawMe() {
		this.g.setColor(this.col);
		float Cx = I.x_ecart + this.x*I.c, Cy = I.y_ecart + this.y*I.c;
		this.g.drawOval(Cx, Cy, I.cc, I.cc);
		this.g.fillOval(Cx, Cy, I.cc, I.cc);
	}
	
	public void move(Case[][] cases) {
		int t_x = this.x + this.dx[this.direction], t_y = this.y + this.dy[this.direction];
		
		if(t_x > cases[0].length - 1 || t_y > cases.length - 1 || t_y < 0 || t_x < 0) {
			this.direction = this.getBestDirection(cases);
		}else if(cases[t_y][t_x].sameColorWith(this)) {
			this.direction = this.getBestDirection(cases);
			cases[t_y][t_x].setColor(this.col == Color.white ? Color.black : Color.white);
		}
		
		this.x += this.dx[this.direction];
		this.y += this.dy[this.direction];
	}
	
	public int getBestDirection(Case[][] cases) {
		List<Integer> possibleDirections = new ArrayList<Integer>();
		
		for(int i = 0; i < this.dx.length; i++) {
			if(this.canGoHere(i, cases))
				possibleDirections.add(i);
		}
		System.out.println("#####################      " + possibleDirections.size());
		possibleDirections.forEach(e -> System.out.print(" == " + e));
		
		int randomGoodDirection = 0;
		if(possibleDirections.size() != 1)
			randomGoodDirection = new Random().nextInt(possibleDirections.size() - 1);
		return possibleDirections.get(randomGoodDirection);
	}
	
	private boolean canGoHere(int direct, Case[][] cases) {
		int t_x = this.x + this.dx[direct], t_y = this.y + this.dy[direct];
	
		if(t_x > cases[0].length - 1 || t_y > cases.length - 1 || t_y < 0 || t_x < 0) {
			return false;
		}else if(cases[t_y][t_x].sameColorWith(this)) {
			return false;
		}
	
		return true;
	}

	public boolean hasColor(Color col) {
		return this.col.equals(col);
	}
	
}

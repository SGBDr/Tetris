import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Case {
	private int x = 0, y = 0;
	private int cc = I.cc;
	private Color col = null;
	private Graphics g;
	
	public Case(int x, int y, Color col, Graphics g) {
		this.x = x;
		this.y = y;
		this.col = col;
		this.g = g;
	}

	public void drawMe() {
		this.g.setColor(this.col);
		float Cx = I.x_ecart + this.x*I.c, Cy = I.y_ecart + this.y*I.c;
		this.g.fillRoundRect(Cx, Cy, cc, cc, 0);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean sameColorWith(Ball ball) {
		return ball.hasColor(this.col);
	}

	public void setColor(Color col) {
		this.col = col;
	}
}

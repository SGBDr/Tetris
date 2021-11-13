import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Case {
	private int x = 0, y = 0;
	private int cc = I.cc;
	private Color col = null;
	Color[] chooseCol = {Color.black, Color.red, Color.yellow, Color.blue, Color.green, Color.orange, Color.pink};
	private boolean mustMove;
	private Graphics g;
	private boolean isGroup;
	private boolean left;
	private boolean right;
	
	public Case(int x, int y, Graphics g) {
		this.x = x;
		this.y = y;
		col = chooseCol[(int)(Math.random()*chooseCol.length)];
		this.mustMove = true;
		this.isGroup = true;
		this.left = true;
		this.right = true;
		this.g = g;
	}

	public void drawMe() {
		this.g.setColor(this.col);
		double Cx = this.x*I.c + I.c/2, Cy = this.y*I.c + I.c/2;
		this.g.fillRoundRect((float)(Cx - (int)I.cc/2), (float)(Cy - I.cc/2), cc, cc, 0);
	}
	
	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
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

	public boolean isMustMove() {
		return mustMove;
	}

	public void setMustMove(boolean mustMove) {
		this.mustMove = mustMove;
	}
	
	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean getRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
}

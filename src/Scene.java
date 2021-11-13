import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Scene {
	private Graphics g = null;
	private Case[][] cases = new Case[I.height/I.c][I.width/I.c];
	
	public Scene(Graphics g) {
		this.g = g;
	}
	
	public void MoveAllPossibleDown() {
		boolean stopMove = false;
		for(int y = I.height/I.c - 2; y >= 0; y--) {
			for(int x = 0; x < I.width/I.c; x++) {
				if(cases[y][x] != null) {
					if(cases[y][x].isMustMove()) {
						cases[y + 1][x] = cases[y][x];
						cases[y + 1][x].setY(cases[y + 1][x].getY() + 1);
						cases[y][x] = null;
						if(y + 1 == I.height/I.c - 1) {
							stopMove = true;
						}else if(cases[y + 2][x] != null) {
							if(!cases[y + 2][x].isMustMove())stopMove = true;
						}
					}
				}
			}
		}
		if(stopMove)makeAllNotMove();
	}
	
	public void MoveAllPossibleLeft() {
		boolean stopMove = false;
		for(int y = I.height/I.c - 2; y >= 0; y--) {
			for(int x = 0; x < I.width/I.c; x++) {
				if(cases[y][x] != null) {
					if(cases[y][x].isMustMove() && cases[y][x].getLeft() && canMoveLeft()) {
						cases[y][x - 1] = cases[y][x];
						cases[y][x - 1].setX(cases[y][x - 1].getX() - 1);
						cases[y][x] = null;
						cases[y][x - 1].setRight(true);
						if(x - 1 == 0) {
							stopMove = true;
						}else if(cases[y][x - 2] != null) {
							if(!cases[y][x - 2].isMustMove())stopMove = true;
						}
					}
				}
			}
		}
		if(stopMove)makeAllNotMoveLeft();
	}
	
	public void rotateCase() {
		Case[] tab = new Case[4];
		int i = 0;
		for(Case[] line : cases)
			for(Case c : line)
				if(c != null)
					if(c.isMustMove()) {
						tab[i] = c;
						i++;
						cases[c.getY()][c.getX()] = null;
					}
		
		int xB = 0, yB = 0;
		int min = 10;
		for(Case c : tab) {
			if(c != null) {
				int u = 0;
				for(Case cc : tab)
					if(cc != null)
						u += Math.abs(cc.getX() - c.getX()) + Math.abs(cc.getY() - c.getY());
				if(u <= min) {
					min = u;
					xB = c.getX();
					yB = c.getY();
				}
			}
		}
		System.out.println(xB + " ## " + yB);
		for(Case c : tab) {
			if(c != null) {
				int xx = -c.getY() + yB + xB;
				int yy = c.getX() - xB + yB;
				System.out.println(c.getX() + " ******** " + c.getY() + " -> " + xx + " ******** " + yy);
				cases[yy][xx] = c;
			}
		}
	}
	
	public void MoveAllPossibleRight() {
		boolean stopMove = false;
		for(int y = I.height/I.c - 2; y >= 0; y--) {
			for(int x = I.width/I.c - 1; x >= 0 ; x--) {
				if(cases[y][x] != null) {
					if(cases[y][x].isMustMove() && cases[y][x].getRight() && canMoveRight()) {
						cases[y][x + 1] = cases[y][x];
						cases[y][x + 1].setX(cases[y][x + 1].getX() + 1);
						cases[y][x] = null;
						cases[y][x + 1].setLeft(true);
						if(x + 1 == I.width/I.c - 1) {
							stopMove = true;
						}else if(cases[y][x + 2] != null) {
							if(!cases[y][x + 2].isMustMove())stopMove = true;
						}
					}
				}
			}
		}
		if(stopMove)makeAllNotMoveRight();
		
	}

	public void drawAllCase() {
		for(Case[] line : cases)for(Case cas : line)if(cas != null)cas.drawMe();
	}
	
	public void drawScene(){
		boolean dWhat = true;
		for(int y = 0; y < I.height; y += I.c) {
			for(int x = 0; x < I.width ; x += I.c) {
				if(dWhat) {
					g.setColor(Color.gray);
					dWhat = false;
				}else {
					g.setColor(Color.white);
					dWhat = true;
				}
				g.fillRoundRect(x, y, I.c, I.c, 0);
			}
			dWhat = !dWhat;
		}
	}
	
	public boolean canMoveLeft() {
		for(int y = 0; y < I.height/I.c; y++)
			for(int x = 0; x < I.width/I.c ; x++)
				if(cases[y][x] != null)
					if(cases[y][x].isMustMove())
						if(x - 1 >= 0)
							if(cases[y][x - 1] != null)
								if(!cases[y][x - 1].isMustMove())
									return false;
		return true;		
	}
	
	public boolean canMoveRight() {
		for(int y = 0; y < I.height/I.c; y++)
			for(int x = 0; x < I.width/I.c ; x++)
				if(cases[y][x] != null)
					if(cases[y][x].isMustMove())
						if(x + 1 <= I.height/I.c - 1)
							if(cases[y][x + 1] != null)
								if(!cases[y][x + 1].isMustMove())
									return false;
		return true;		
	}
	
	public void makeAllNotMove() {
		for(int y = 0; y < I.height/I.c; y++)
			for(int x = 0; x < I.width/I.c ; x++)
				if(cases[y][x] != null)
					cases[y][x].setMustMove(false);
	}
	
	public void makeAllNotMoveLeft() {
		for(int y = 0; y < I.height/I.c; y++)
			for(int x = 0; x < I.width/I.c ; x++)
				if(cases[y][x] != null)
					cases[y][x].setLeft(false);
	}
	
	public void makeAllNotMoveRight() {
		for(int y = 0; y < I.height/I.c; y++)
			for(int x = 0; x < I.width/I.c ; x++)
				if(cases[y][x] != null)
					cases[y][x].setRight(false);
	}

	public int verifyWin() {
		int isThereLine = WinningLine();
		int compteur = 0;
		while(isThereLine != -1) {
			for(int y = isThereLine; y >= 0; y--) {
				for(int x = 0; x < I.width/I.c; x++) {
					if(cases[y][x] != null) {
						if(!cases[y][x].isMustMove()) {
							cases[y + 1][x] = cases[y][x];
							cases[y + 1][x].setY(cases[y + 1][x].getY() + 1);
							cases[y][x] = null;
						}
					}
				}
			}
			compteur++;
			isThereLine = WinningLine();
		}
		return compteur;
	}
			
	public int WinningLine() {
		for(int y = I.height/I.c - 1; y >= 0; y--) {
			boolean temp = true;
			for(Case c : cases[y])if(c == null)temp = false;
			if(temp) {
				cases[y] = new Case[I.width/I.c];
				return y;
			}
		}
		return -1;
	}

	public void InjectObject() {
		Case a[] = {new Case(2, 0, this.g), new Case(3, 0, this.g), new Case(3, 1, this.g), new Case(4, 0, this.g)};
		Case b[] = {new Case(2, 0, this.g), new Case(2, 1, this.g), new Case(2, 2, this.g), new Case(2, 2, this.g)};
		Case c[] = {new Case(2, 0, this.g), new Case(2, 1, this.g), new Case(2, 2, this.g), new Case(1, 1, this.g)};
		Case d[] = {new Case(2, 0, this.g), new Case(3, 0, this.g), new Case(4, 0, this.g), new Case(5, 0, this.g)};
		Case e[] = {new Case(2, 0, this.g), new Case(3, 0, this.g), new Case(3, 1, this.g), new Case(2, 1, this.g)};
		Case[][] chooses = {a, b, c, d, e};
		if(canInjectObject()) {
			Case[] temp = chooses[(int)(Math.random()*5)];
			for(Case t : temp) {
				cases[t.getY()][t.getX()] = t;
			}
		}
	}
	
	public boolean canInjectObject() {
		boolean can = true;
		for(Case[] cas : cases)
			for(Case c : cas)
				if(c != null)
					if(c.isMustMove())
						can = false;
		return can;
	}
}

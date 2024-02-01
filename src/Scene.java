import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Scene {
	private Graphics g = null;
	private Case[][] cases = new Case[I.height/I.c][I.width/I.c];
	private List<Ball> balls;
	
	public Scene(Graphics g) {
		this.g = g;
	}
	
	public void initScene() {
		for(int i = 0; i < this.cases[0].length/2; i++)
			for(int j = 0; j < this.cases.length; j++)
				this.cases[j][i] = new Case(i, j, Color.white, g);
		
		for(int i = this.cases[0].length/2; i < this.cases[0].length; i++)
			for(int j = 0; j < this.cases.length; j++)
				this.cases[j][i] = new Case(i, j, Color.black, g);
		
		this.balls = new ArrayList<Ball>(
				List.of(
					new Ball(0, this.cases.length/2, Color.black, 0, g),
					new Ball(this.cases[0].length - 1, this.cases.length/2, Color.white, 0, g)
				)
		);
	}

	public void drawScene() {
		for(int i = 0; i < this.cases[0].length; i++)
			for(int j = 0; j < this.cases.length; j++)
				this.cases[j][i].drawMe();
		
		for(Ball ball : this.balls)
			ball.drawMe();
		
	}

	public void moveBalls() {
		for(Ball ball : this.balls)
			ball.move(cases);
	}
}

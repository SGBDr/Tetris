import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tetris extends BasicGame {
	
	private Scene scene = null;
	private long sec = System.currentTimeMillis();
	private long sec2 = System.currentTimeMillis();
	int l = 0;

	public Tetris(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gr, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		gr.getGraphics().setBackground(Color.white);
		Input input = gr.getInput();
		
		if(System.currentTimeMillis() - sec2 >= I.timePlayer) {
			if(input.isKeyDown(Input.KEY_RIGHT))scene.MoveAllPossibleRight();
			else if(input.isKeyDown(Input.KEY_LEFT))scene.MoveAllPossibleLeft();
			else if(input.isKeyDown(Input.KEY_ENTER))scene.rotateCase();
			sec2 = System.currentTimeMillis();
		}

		if(System.currentTimeMillis() - sec >= I.timeGame & l < 5) {
			scene.MoveAllPossibleDown();
			sec = System.currentTimeMillis();
			l++;
		}
		//scene.verifyWin();
		//scene.drawScene();
		scene.drawAllCase();
		scene.InjectObject();
	}

	@Override
	public void init(GameContainer gr) throws SlickException {
		// TODO Auto-generated method stub
		scene = new Scene(gr.getGraphics());
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

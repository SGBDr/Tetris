import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class tetrisMain {

	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File("./native/windows/").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Tetris("Tetris"));
		app.setTargetFrameRate(60);
		app.setShowFPS(false);
		app.setDisplayMode(I.width, I.height, false);
		app.start();
	}

}

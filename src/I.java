
public interface I {
	//you can modify c to add column and rows to the GAME.
	// but be sure of the way that width % c == 0 and height % c == 0
	static int c = 30;
	static int cc = c - 5;
	static int x_ecart = 50;
	static int y_ecart = 50;
	static int width = 600;
	static int height = 510;
	static int width_h = width + x_ecart*2;
	static int height_h = height + y_ecart*2;
	//modify timeGame to add Speed to your game, don't touch timePlayer fucking man (I'm smilling)
	static int timeGame = 100; 
	//je suis s�rieux ne touche pas a cette variable idiot
	static int timePlayer = 100;
}

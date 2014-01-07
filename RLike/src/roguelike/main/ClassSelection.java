package roguelike.main;

import roguelike.etc.Session;
import roguelike.ui.CharacterSelectPanel;
import roguelike.ui.Window;

public class ClassSelection {

	public static void main(String[] args) {
		Window w = new Window(0, 0, 800, 600);
		CharacterSelectPanel p = new CharacterSelectPanel();
		w.add(p);
		//w.pack();
		w.setVisible(true);
	}
}

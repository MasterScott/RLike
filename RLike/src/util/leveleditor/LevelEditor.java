package util.leveleditor;

public class LevelEditor {

	public static void main(String[] args) {
		EditorWindow w = new EditorWindow();
		EditorMenu m = new EditorMenu();
		w.add(m);
		w.pack();
		w.setVisible(true);
	}
}

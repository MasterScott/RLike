package roguelike.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import roguelike.actors.Creature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.actors.Stat;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.Cave;
import roguelike.world.Floor;

public class LoadCave {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Window w = new Window();

		Session.player = new Player('@', Color.WHITE, 0,0);
		Session.player.setImage(GraphicFile.CLASSM, 2, 5);
		
		w.notifyCharacterSelected();

	}

}

package br.com.ifsp.batalhanaval.manager;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameManager{
	
	enum STATES { MENU,
				  PLAYING, WIN, LOSE, DRAW, 
				  YOURTURN, ENEMYTURN}
	
	STATES state;
	
	public GameManager() {
		state = STATES.PLAYING;
	}
}

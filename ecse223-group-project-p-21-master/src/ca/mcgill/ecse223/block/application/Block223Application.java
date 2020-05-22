package ca.mcgill.ecse223.block.application;


import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.WelcomePage;

public class Block223Application {

	private static Block223 block223=null;
	public static Game CurrentGame=null;
	public static User CurrentUser=null;
	public static UserRole CurrentUserRole=null;
	public static PlayedGame CurrentPlayableGame=null;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().frame.setVisible(true);
            }
        });
	}
	
	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 =Block223Persistence.load();
		}
		return block223;
	}
	
	public static Block223 resetBlock223() {
		block223=Block223Persistence.load();
		return block223;
	}
	
	public static void setBlock223(Block223 b) {
		block223=b;
	}
	
	public static Game getCurrentGame() {
		return CurrentGame;
	}
	
	public static void setCurrentGame(Game game) {
		CurrentGame=game;
	}
	
	public static User getCurrentUser() {
		return CurrentUser;
	}
	public static void setCurrentUser(User user) {
		CurrentUser=user;
	}
	
	public static UserRole getCurrentUserRole() {
		return CurrentUserRole;
	}
	
	public static void setCurrentUserRole(UserRole role) {
		CurrentUserRole=role;
	}
	public static PlayedGame getCurrentPlayableGame() {
		return CurrentPlayableGame;
	}
	
	public static void setCurrentPlayableGame(PlayedGame game) {
		CurrentPlayableGame=game;
	}

}

package ca.mcgill.ecse223.block.controller;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;


public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {

		String error="";
		if (Block223Application.getCurrentUserRole() instanceof Admin == false) 
			error="Admin privileges are required to create a game. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		Game game;

		if (Block223Application.getBlock223().findGame(name) != null) {
			throw new InvalidInputException("The name of a game must be unique.");
		}

		try {
			game=new Game(name, 1, (Admin) admin, 1, 1, 1, 1, 1, block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		Block223Application.setCurrentGame(game);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		// I'm not 100% sure my code's right cuz some of the things I'm a little meh about but yeah
		// Should I put this after the error checks

		String error = "";

		if (Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to define game settings.");

		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to define game settings. ";

		if (Block223Application.getCurrentGame().getAdmin()!= Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can define its game settings. ";

		if (nrLevels < 1 || nrLevels > 99)
			error = error + "The number of levels must be between 1 and 99. ";

		if (minPaddleLength <= 0)
			throw new InvalidInputException("The minimum length of the paddle must be greater than zero.");

		if (maxPaddleLength <= 0 || maxPaddleLength > 390)
			throw new InvalidInputException("The maximum length of the paddle must be greater than zero and less than or equal to 390.");

		if (nrBlocksPerLevel <= 0)
			throw new InvalidInputException("The number of blocks per level must be greater than zero.");

		if (ballSpeedIncreaseFactor <= 0)
			throw new InvalidInputException("The speed increase factor of the ball must be greater than zero.");  

		if (minBallSpeedY <= 0 && minBallSpeedX <= 0)
			throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");  

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		if (checkNumberOfBAs(nrBlocksPerLevel)) 
			throw new InvalidInputException("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");

		Game game = Block223Application.getCurrentGame();
		game.setNrBlocksPerLevel(nrBlocksPerLevel);
		Ball gameBall = game.getBall();
		gameBall.setMinBallSpeedX(minBallSpeedX);
		gameBall.setMinBallSpeedY(minBallSpeedY);
		gameBall.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		Paddle gamePaddle = game.getPaddle();
		gamePaddle.setMaxPaddleLength(maxPaddleLength);
		gamePaddle.setMinPaddleLength(minPaddleLength);

		while (nrLevels > game.numberOfLevels()) {
			game.addLevel();
		}
		while (nrLevels < game.numberOfLevels()) {
			Level level = game.getLevel(game.numberOfLevels()-1);
			level.delete();
		}
	}

	public static boolean checkNumberOfBAs(int nrBlocksPerLevel) {
		for (Level level: Block223Application.getCurrentGame().getLevels()) {
			int currentNumberofBlocks=level.numberOfBlockAssignments();
			if (currentNumberofBlocks > nrBlocksPerLevel)
				return true;
		}
		return false;
	}

	public static void deleteGame(String name) throws InvalidInputException {

		Block223 block223 = Block223Application.getBlock223();
		Game game = block223.findGame(name);
		String error="";
		// We must check that the user is an admin AND the admin of the game!

		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { 	
			error= "Admin privileges are required to delete a game. ";
		}

		if (Block223Application.getCurrentGame()!=null) {
			if (Block223Application.getCurrentGame().getAdmin()!= Block223Application.getCurrentUserRole())
				error=error+"Only the admin who created the game can delete the game. ";
		}



		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		if (game != null) {
			if (game.isPublished()==true)
				throw new InvalidInputException("A published game cannot be deleted.");
			game.delete();
			Block223Persistence.save(block223);
		}

	}

	public static void selectGame(String name) throws InvalidInputException {

		String error = "";
		Game game = Block223Application.getBlock223().findGame(name);

		if (game == null) 
			throw new InvalidInputException("A game with name " +name+ " does not exist.");

		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to select a game. ";	
			throw new InvalidInputException(error);
		}

		if (game.isPublished())
			throw new InvalidInputException("A published game cannot be changed.");

		if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
			error += "Only the admin who created the game can select the game. ";
			throw new InvalidInputException(error);
		}

		Block223Application.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = "";

		if(Block223Application.getCurrentGame() == null) 
			throw new InvalidInputException("A game must be selected to define game settings.");

		if(!(Block223Application.getCurrentUserRole() instanceof Admin))
			error += "Admin privileges are required to define game settings. ";		

		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			error += "Only the admin who created the game can define its game settings. ";

		if (error.length() > 0) 
			throw new InvalidInputException(error.trim());

		if (name == null || name.equals("")) 
			throw new InvalidInputException("The name of a game must be specified.");

		if ((Block223Application.getBlock223().findGame(name))!=null&&(!(Block223Application.getCurrentGame().getName()).equals(name)))
			throw new InvalidInputException("The name of a game must be unique.");

		Game game = Block223Application.getCurrentGame();

		String currentName = Block223Application.getCurrentGame().getName();

		if(!(currentName.equals(name))) 
			game.setName(name);

		try {
			setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
		} 
		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}		

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {

		if(Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to add a block.");

		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin))
			error += "Admin privileges are required to add a block.";		



		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			error += "Only the admin who created the game can add a block.";

		if (error.length() > 0) 
			throw new InvalidInputException(error.trim());

		Game game = Block223Application.getCurrentGame();
		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			if (block.getBlue() == blue && block.getRed() == red && block.getGreen()== green)
				throw new InvalidInputException ("A block with the same color already exists for the game.");
		}
		try {
			game.addBlock(red, green, blue, points);
		}
		catch (RuntimeException e)	{
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void deleteBlock(int id) throws InvalidInputException {

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to delete a block.");

		String error="";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)	
			error="Admin privileges are required to delete a block. ";



		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can delete a block. ";
		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Block block = Block223Application.getCurrentGame().findBlock(id);

		if (block != null) {
			block.delete();
		}

	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {

		String error="";
		if (red<0||red>255)
			error+="Red must be between 0 and 255. ";

		if (green<0||green>255)
			error+="Green must be between 0 and 255. ";

		if (blue<0||blue>255)
			error+="Blue must be between 0 and 255. ";

		if (points<1||points>1000)
			error+="Points must be between 1 and 1000. ";


		if (Block223Application.getCurrentUserRole()instanceof Admin == false)	
			error="Admin privileges are required to update a block. ";

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to update a block.");

		if (Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can update a block. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		for (Block B : Block223Application.getCurrentGame().getBlocks())
			if (B.getRed() == red && B.getBlue()== blue && B.getGreen() == green)
				throw new InvalidInputException("A block with the same color already exists for the game.");


		Block block = Block223Application.getCurrentGame().findBlock(id);

		if (Block223Application.getCurrentGame().findBlock(id) == null)
			throw new InvalidInputException("The block does not exist.");

		block.setRed(red);
		block.setGreen(green);
		block.setBlue(blue);
		block.setPoints(points);


	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		String error="";


		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to position a block.";

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to position a block. ");

		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can position a block. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game=Block223Application.getCurrentGame();
		Level gameLevel;

		try {
			gameLevel=game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException ("Level "+(level)+" does not exist for the game.");
		}

		int maxNrBlocksPerLevel = Block223Application.getCurrentGame().getNrBlocksPerLevel();
		if(gameLevel.numberOfBlockAssignments() == maxNrBlocksPerLevel) {
			throw new InvalidInputException ("The number of blocks has reached the maximum number ("+ maxNrBlocksPerLevel + ") allowed for this game.");
		}

		for (BlockAssignment BA : gameLevel.getBlockAssignments()) {
			if(BA.getGridHorizontalPosition() == gridHorizontalPosition && BA.getGridVerticalPosition() == gridVerticalPosition) {
				throw new InvalidInputException("A block already exists at location "+gridHorizontalPosition+"/"+gridVerticalPosition+".");
			}
		}

		Block block = game.findBlock(id);

		if(game.findBlock(id) == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		try {
			BlockAssignment newBlock = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition,gameLevel, block, game);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

		String error="";

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to move a block. ");

		if (Block223Application.getCurrentUserRole()instanceof Admin == false)	//instance of an admin
			error="Admin privileges are required to move a block. ";

		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can move a block. ";

		if (newGridHorizontalPosition <= 0 || newGridHorizontalPosition > 15)
			throw new InvalidInputException("The horizontal position must be between 1 and 15.");

		if (newGridVerticalPosition <= 0 || newGridVerticalPosition > 15)
			throw new InvalidInputException("The vertical position must be between 1 and 15.");

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game=Block223Application.getCurrentGame();
		Level gameLevel;

		try {
			gameLevel=game.getLevel(level-1);
			BlockAssignment assignment = gameLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
			if(assignment == null)
				throw new InvalidInputException("A block does not exist at location "+oldGridHorizontalPosition+"/"+oldGridVerticalPosition+".");

			for (BlockAssignment BA : gameLevel.getBlockAssignments()) {
				if(BA.getGridHorizontalPosition() == newGridHorizontalPosition && BA.getGridVerticalPosition() == newGridVerticalPosition) {
					throw new InvalidInputException("A block already exists at location "+newGridHorizontalPosition+"/"+newGridVerticalPosition+".");
				}
			}

			if (assignment != null) {
				assignment.setGridHorizontalPosition(newGridHorizontalPosition);
				assignment.setGridVerticalPosition(newGridVerticalPosition);
			}

		}
		catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException ("Level "+level+" does not exist for the game.");
		}

	}
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		// Should I put this after the error checks

		String error = "";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to remove a block. ";

		if (Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to remove a block.");

		if (Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can remove a block. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game = Block223Application.getCurrentGame();
		Level gameLevel = game.getLevel(level-1);
		BlockAssignment assignment = gameLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if (assignment != null) {
			assignment.delete();
		}
	}

	public static void saveGame() throws InvalidInputException {

		String error = "";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to save a game. ";

		if (Block223Application.getCurrentGame() == null)
			error = error + "A game must be selected to save it. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		if (Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can save it. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Block223 b=Block223Application.getBlock223();

		Block223Persistence.save(b);

	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {

		Block223 block223=Block223Application.getBlock223();

		String error = "";

		if (playerPassword == null||playerPassword.equals(""))
			throw new InvalidInputException("The player password needs to be specified.");

		if (Block223Application.getCurrentUserRole()!=null)
			error = "Cannot register a new user while a user is logged in. ";

		if (playerPassword.equals(adminPassword))
			error += "The passwords have to be different. ";	

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Player player = null;
		Admin admin = null;

		try{ 
			player= new Player(playerPassword, block223);
			User user= new User(username, block223, player);

			if(!(adminPassword==null||adminPassword.equals(""))) {
				admin=new Admin(adminPassword, block223);
				user.addRole(admin);
			}

			Block223Persistence.save(block223);
		}
		catch(RuntimeException e) {	
			if (e.getMessage().equals("The username must be specified.")) {
				player.delete();
				throw new InvalidInputException(e.getMessage());}
			if (e.getMessage().equals("Cannot create due to duplicate username")) {
				player.delete();
				throw new InvalidInputException("The username has already been taken.");}

			else if(player!=null) {player.delete();}

			else if(admin!=null) {admin.delete();}
			throw new InvalidInputException(e.getMessage());


		}
	}

	public static void login(String username, String password) throws InvalidInputException {
		String error = "";

		if(Block223Application.getCurrentUserRole()!=null)
			error= "Cannot login a user while a user is already logged in. ";

		Block223 b=Block223Application.resetBlock223();
		User user=b.findWithUsername(username);

		if(user==null||password.equals(""))
			error=error+"The username and password do not match. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		for (int i=0; i<user.numberOfRoles();i++) {
			String rolePassword= user.getRole(i).getPassword();

			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(user.getRole(i));
			}
		}

		if(password.equals(user.getRole(0).getPassword())==false && password.equals(user.getRole(1).getPassword())==false)
			error= error+"The username and password do not match. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
	}

	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {

		if (!(Block223Application.getCurrentUserRole() instanceof Player))
			throw new InvalidInputException("Player privileges are required to play a game.");

		Block223 b = Block223Application.getBlock223();
		Player player =(Player) Block223Application.getCurrentUserRole();
		Game game = b.findGame(name);

		PlayedGame pgame=b.findPlayableGame(id);;

		if (pgame==null) {

			User user =b.findWithRole(player);

			if (user!=null&&game!=null) {
				String username=user.getUsername();
				pgame = new PlayedGame(username, game, b);
				pgame.setPlayer(player);
			}
		}

		if (game==null&&pgame==null) 
			throw new InvalidInputException("The game does not exist.");

		if (pgame!=null&&pgame.getPlayer()!=player)
			throw new InvalidInputException("Only the player that started a game can continue the game.");

		Block223Application.setCurrentPlayableGame(pgame);
	}

	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		UserRole urole =Block223Application.getCurrentUserRole();

		if (urole==null)
			throw new InvalidInputException("Player privileges are required to play a game.");

		if (pgame==null)
			throw new InvalidInputException("A game must be selected to play it.");

		if (urole instanceof Admin && pgame.getPlayer()!=null)
			throw new InvalidInputException("Player privileges are required to play a game.");

		if (urole instanceof Admin&& pgame.getGame().getAdmin()!=urole)
			throw new InvalidInputException("Only the admin of a game can test the game.");

		if (urole instanceof Player && pgame.getPlayer()==null)
			throw new InvalidInputException("Admin privileges are required to test a game.");

		String userinputs;

		pgame.play();
		ui.takeInputs();

		while (pgame.getPlayStatus() == PlayStatus.Moving) {
			userinputs = ui.takeInputs();
			updatePaddlePosition(userinputs);
			pgame.move();

			if(userinputs.contains(" ")) {
				//String inputBeforeSpace = userinputs.substring(0, (userinputs.indexOf(" ")));
				pgame.pause();
				break;
			}

			try {
				Thread.sleep((long) pgame.getWaitTime()/500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ui.refresh();

		}

		if (pgame.getPlayStatus() == PlayStatus.GameOver) {
			ui.endGame(pgame.getLives(), getHallOfFameWithMostRecentEntry(1).getEntry(0));
			Block223Application.setCurrentPlayableGame(null);
			pgame.delete();
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}

		else if (pgame.getPlayer() != null) {
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}

	}

	public static void updatePaddlePosition(String userinputs) {
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		double currentPaddleLength = pgame.getCurrentPaddleLength();
		double currentPaddleX = pgame.getCurrentPaddleX();
		for (int i = 0; i < userinputs.length(); i++) {
			if (userinputs.charAt(i) == 'l') {
				Left(pgame);
			}
			if (userinputs.charAt(i) == 'r') {
				Right(pgame);
			}
			if (userinputs.charAt(i) == ' ') {
				break;
			}
		}
	}

	private static void Left(PlayedGame pgame) {
		double left = PlayedGame.PADDLE_MOVE_LEFT;
		double currentPaddleX = pgame.getCurrentPaddleX();
		if (currentPaddleX > 0)
			pgame.setCurrentPaddleX(pgame.getCurrentPaddleX() + left);
	}

	private static void Right(PlayedGame pgame) {
		double right = PlayedGame.PADDLE_MOVE_RIGHT;
		double currentPaddleX = pgame.getCurrentPaddleX();
		double currentPaddleLength = pgame.getCurrentPaddleLength();
		if (Game.PLAY_AREA_SIDE - currentPaddleLength > currentPaddleX)
			pgame.setCurrentPaddleX(pgame.getCurrentPaddleX() + right);
	}

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {

		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to test a game.");

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to test it.");

		if (Block223Application.getCurrentUserRole()!=Block223Application.getCurrentGame().getAdmin())
			throw new InvalidInputException("Only the admin who created the game can test it.");

		Game game = Block223Application.getCurrentGame();
		UserRole admin= game.getAdmin();
		Block223 b =Block223Application.getBlock223();

		String username= b.findWithRole(admin).getUsername();
		PlayedGame pgame=new PlayedGame(username, game, b);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);

		startGame(ui);

	}

	public static void selectTestGame() throws InvalidInputException {

		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to test a game.");

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to test it.");

		if (Block223Application.getCurrentUserRole()!=Block223Application.getCurrentGame().getAdmin())
			throw new InvalidInputException("Only the admin who created the game can test it.");
		
		for (Level level : Block223Application.getCurrentGame().getLevels()) {

			if (level.getBlockAssignments().size()<1)
				throw new InvalidInputException("At least one block must be defined for a game to be tested.");
		}
		
		Game game = Block223Application.getCurrentGame();
		UserRole admin= game.getAdmin();
		Block223 b =Block223Application.getBlock223();

		String username= b.findWithRole(admin).getUsername();
		PlayedGame pgame=new PlayedGame(username, game, b);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);

		//startGame(ui);

	}
	public static void publishGame () throws InvalidInputException {

		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to publish a game.");

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to publish it.");

		if (Block223Application.getCurrentUserRole()!=Block223Application.getCurrentGame().getAdmin())
			throw new InvalidInputException("Only the admin who created the game can publish it.");

		for (Level level : Block223Application.getCurrentGame().getLevels()) {

			if (level.getBlockAssignments().size()<1)
				throw new InvalidInputException("At least one block must be defined for a game to be published.");
		}


		Game game =Block223Application.getCurrentGame();
		game.setPublished(true);
		saveGame();
		Block223Application.setCurrentGame(null);


	}

	//fix
	public static List <TOHallOfFameEntry> searchHallOfFame(String gamename, String name) throws InvalidInputException {

		List <TOHallOfFameEntry> result = new ArrayList<TOHallOfFameEntry>();
		TOHallOfFame hOF=null;
		try {
			hOF= getHallOfFameWithoutSelecting(gamename,0,100);
		}
		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}

		for (int i=0; i<hOF.numberOfEntries();i++) {
			TOHallOfFameEntry entry=hOF.getEntry(i);
			if (hOF.getEntry(i).getPlayername().equals(name)) {
				result.add(entry);
			}
		}
		return result;
	}



	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {

		Block223 block223 = Block223Application.getBlock223();
		// Is there a line for getCurrentUserRole() ?
		// I'm pretty sure there is because I need to throw an exception if the role is not AdminRole
		ArrayList<TOGame> result = new ArrayList<TOGame>();
		UserRole user = Block223Application.getCurrentUserRole();

		if (!(user instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		for (Game game: block223.getGames()) {
			Admin gameAdmin = game.getAdmin();
			if (gameAdmin.equals((Admin)user)&& game.isPublished()==false) { 
				TOGame togame= new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
				result.add(togame);
			}
		}
		return result;

	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {

		if (Block223Application.getCurrentGame() == null)
			throw new InvalidInputException ("A game must be selected to access its information.");

		String error = "";

		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException ("Admin privileges are required to access game information.");

		if(Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			error = "Only the admin who created the game can access its information.";

		if (error.length() > 0) 
			throw new InvalidInputException(error.trim());


		Game game = Block223Application.getCurrentGame();
		TOGame to = new TOGame(game.getName(), game.getLevels().size(),game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to access its information.");

		String error="";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to access game information. ";

		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can access its information. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game = Block223Application.getCurrentGame();
		ArrayList<TOBlock> result = new ArrayList<>();
		for (Block block : game.getBlocks()) {
			TOBlock tobck = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(tobck);
		}

		return result;

	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {

		String error="";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to access game information. ";

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to access its information.");

		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can access its information. ";

		if (Block223Application.getCurrentGame().findBlock(id) == null)
			error=error+"The block does not exist. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		//Game game = Block223Application.getCurrentGame();
		Block block = Block223Application.getCurrentGame().findBlock(id);
		TOBlock tobck1 = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());

		return tobck1;

	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {

		if (Block223Application.getCurrentGame()==null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if(level < 1) {
			throw new InvalidInputException("Level 0 does not exist for the game.");
		}
		if (level > Block223Application.getCurrentGame().numberOfLevels()) {
			throw new InvalidInputException("Level "+level+" does not exist for the game.");
		}

		String error="";
		if (Block223Application.getCurrentUserRole()instanceof Admin == false)
			error="Admin privileges are required to access game information. ";

		if(Block223Application.getCurrentGame().getAdmin()!=Block223Application.getCurrentUserRole())
			error=error+"Only the admin who created the game can access its information. ";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game = Block223Application.getCurrentGame();
		Level gameLevel = game.getLevel(level-1);

		ArrayList<TOGridCell> result = new ArrayList<>();

		for(BlockAssignment bA: gameLevel.getBlockAssignments()) {
			TOGridCell toba = new TOGridCell(bA.getGridHorizontalPosition(),bA.getGridVerticalPosition(), bA.getBlock().getId(), 
					bA.getBlock().getRed(), bA.getBlock().getGreen(), bA.getBlock().getBlue(), bA.getBlock().getPoints());
			result.add(toba);
		}
		return result;
	}

	public static TOUserMode getUserMode() {

		UserRole userRole=Block223Application.getCurrentUserRole();

		if (userRole instanceof Admin) {
			TOUserMode to=new TOUserMode(Mode.Design);
			return to;
		}
		if (userRole instanceof Player) {
			TOUserMode to=new TOUserMode(Mode.Play);
			return to;
		}	
		else {
			TOUserMode to=new TOUserMode(Mode.None);
			return to;
		}

	}


	// play mode

	public static List<TOPlayableGame> getPlayableGames1() throws InvalidInputException {


		if (!(Block223Application.getCurrentUserRole() instanceof Player))
			throw new InvalidInputException("Player privileges are required to play a game.");

		Player player = (Player)Block223Application.getCurrentUserRole();

		Block223 b = Block223Application.getBlock223();

		ArrayList<TOPlayableGame> result = new ArrayList<TOPlayableGame>();

		List<Game> games= b.getGames();
		for (Game game : games) {
			boolean published = game.isPublished();
			if (published) {
				TOPlayableGame to=new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}

		return result;
	}

	public static List<TOPlayableGame> getPlayableGames2() throws InvalidInputException {


		if (!(Block223Application.getCurrentUserRole() instanceof Player))
			throw new InvalidInputException("Player privileges are required to play a game.");

		Player player = (Player)Block223Application.getCurrentUserRole();

		//Block223 b = Block223Application.getBlock223();

		ArrayList<TOPlayableGame> result = new ArrayList<TOPlayableGame>();

		List<PlayedGame> pgames = player.getPlayedGames();
		for (PlayedGame pgame :pgames) {
			TOPlayableGame to=new TOPlayableGame(pgame.getGame().getName(), pgame.getId(), pgame.getCurrentLevel());
			result.add(to);
		}

		return result;

	}
	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException{

		List<TOPlayableGame> result=null;
		try {
			result=getPlayableGames1();
			result.addAll(getPlayableGames2());
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}

		return result;
	}
	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		UserRole urole =Block223Application.getCurrentUserRole();

		if (urole==null)
			throw new InvalidInputException("Player privileges are required to play a game.");

		if (pgame==null)
			throw new InvalidInputException("A game must be selected to play it.");

		if (urole instanceof Admin && pgame.getPlayer()!=null)
			throw new InvalidInputException("Player privileges are required to play a game.");

		if (urole instanceof Admin&& pgame.getGame().getAdmin()!=urole)
			throw new InvalidInputException("Only the admin of a game can test the game.");

		if (urole instanceof Player && pgame.getPlayer()==null)
			throw new InvalidInputException("Admin privileges are required to test a game.");

		boolean paused = (pgame.getPlayStatus()==PlayStatus.Ready||pgame.getPlayStatus()==PlayStatus.Paused);

		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(),
				pgame.getLives(),pgame.getCurrentLevel(), pgame.getPlayername(), pgame.getCurrentBallX(), 
				pgame.getCurrentBallY(), pgame.getCurrentPaddleLength(), pgame.getCurrentPaddleX());

		for (PlayedBlockAssignment pblock: pgame.getBlocks()) {
			TOCurrentBlock toblock = new TOCurrentBlock (pblock.getBlock().getRed(),pblock.getBlock().getGreen(),
					pblock.getBlock().getBlue(), pblock.getBlock().getPoints(), pblock.getX(), pblock.getY(), result);
		}

		return result;
	}

	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {

		UserRole userRole=Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");

		if (Block223Application.getCurrentPlayableGame() == null)
			throw new InvalidInputException("A game must be selected to view its hall of fame.");

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());


		if (start<1) start=1;

		if (end>game.numberOfHallOfFameEntries())
			end=game.numberOfHallOfFameEntries();

		start=game.numberOfHallOfFameEntries()-start;

		end=game.numberOfHallOfFameEntries()-end;


		for (int i = start ; i>= end; i--) {
			TOHallOfFameEntry entry = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
			result.addEntry(entry);
		}
		return result;

	}


	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		UserRole userRole=Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");

		if (Block223Application.getCurrentPlayableGame() == null)
			throw new InvalidInputException("A game must be selected to view its hall of fame.");

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();

		Game game = pgame.getGame();
		if (game==null) {
			String name=getCurrentPlayableGame().getGamename();
			TOHallOfFame result = new TOHallOfFame(name);
		}
		else{
			TOHallOfFame result = new TOHallOfFame(pgame.getGame().getName()); 
		}
		HallOfFameEntry mostRecent = game.getMostRecentEntry();

		int indexR = game.indexOfHallOfFameEntry(mostRecent);


		int start = indexR + (numberOfEntries / 2 );
		if(start>(game.numberOfHallOfFameEntries()-1))
			start=(game.numberOfHallOfFameEntries()-1);

		int end=start-(numberOfEntries-1);
		if (end < 0 ) 
			end = 0;

		TOHallOfFame result = new TOHallOfFame(game.getName());

		for (int i = start ; i>= end; i--) {
			TOHallOfFameEntry entry = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);		
			result.addEntry(entry);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFameWithoutSelecting(String name, int start, int end) throws InvalidInputException {

		UserRole userRole=Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");

		Block223 b=Block223Application.getBlock223();
		TOHallOfFame result = new TOHallOfFame(name);
		Game game=b.findGame(name);

		if (start<1) start=1;

		if (end>game.numberOfHallOfFameEntries())
			end=game.numberOfHallOfFameEntries();

		start=game.numberOfHallOfFameEntries()-start;

		end=game.numberOfHallOfFameEntries()-end;


		for (int i = start ; i>= end; i--) {
			TOHallOfFameEntry entry = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
			result.addEntry(entry);
		}
		return result;

	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntryWithoutSelecting(String name, int numberOfEntries) throws InvalidInputException {
		UserRole userRole=Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");

		Block223 b=Block223Application.getBlock223();
		TOHallOfFame result = new TOHallOfFame(name);
		Game game=b.findGame(name);

		HallOfFameEntry mostRecent = game.getMostRecentEntry();

		int indexR = game.indexOfHallOfFameEntry(mostRecent);


		int start = indexR + (numberOfEntries / 2 );
		if(start>(game.numberOfHallOfFameEntries()-1))
			start=(game.numberOfHallOfFameEntries()-1);

		int end=start-(numberOfEntries-1);
		if (end < 0 ) 
			end = 0;

		for (int i = start ; i>= end; i--) {
			TOHallOfFameEntry entry = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);		
			result.addEntry(entry);
		}
		return result;
	}

	private List<Point2D> getInterectionPoints(Line2D l, double xc, double yc, double r){

		List<Point2D> list = new ArrayList<Point2D>();
		Double m = slope(l);
		double xl = l.getX1();
		double yl= l.getY1();
		if(m == null) {
			List<Double> yvals = new ArrayList<Double>();
			double x = xl;
			double radicand = r*r - (x-xc)*(x-xc);
			if(radicand >= 0) {
				yvals.add(yc - Math.sqrt(radicand));
				yvals.add(yc+Math.sqrt(radicand));
			}
			for(Double yval : yvals) {
				if(yval <= Math.min(yl, l.getY2()) && yval >= Math.max(yl, l.getY2())) {
					list.add(new Point2D.Double(x, yval));
				}
			}
		}
		else {
			List<Double> xvals = new ArrayList<Double>();
			double a = (m*m +1);
			double b = 2*m*(-m*xl+yl-yc)-2*xc;
			double c = Math.pow(-m*xl+yl-yc, 2) - r*r + xc*xc;
			if(b*b - 4*a*c >= 0) {
				xvals.add((-b+ Math.sqrt(b*b - 4*a*c))/(2*a));
				xvals.add((-b - Math.sqrt(b*b - 4*a*c))/(2*a));
			}
			for (Double xval : xvals) {
				if(xval <= Math.max(xl,  l.getX2()) && xval >= Math.min(xl, l.getX2())){
					list.add(new Point2D.Double(xval, m*xval - m*xl + yl));
				}
			}
		}
		return list;
	}

	public double slope(Line2D l) {

		double result = 0;
		double x1 = l.getX1();
		double x2 = l.getX2();
		double y1 = l.getY1();
		double y2 = l.getY2();

		result = ((y2 - y1)/(x2 - x1));

		return result;
	}


}

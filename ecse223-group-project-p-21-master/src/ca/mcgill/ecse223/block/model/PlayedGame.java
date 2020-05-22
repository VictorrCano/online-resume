/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 121 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

	//------------------------
	// STATIC VARIABLES
	//------------------------


	/**
	 * at design time, the initial wait time may be adjusted as seen fit
	 */
	public static final int INITIAL_WAIT_TIME = 10000;
	private static int nextId = 1;
	public static final int NR_LIVES = 3;

	/**
	 * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
	 * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
	 */
	public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
	public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

	/**
	 * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
	 * pixels moved when right arrow key is pressed
	 */
	public static final int PADDLE_MOVE_RIGHT = 5;

	/**
	 * pixels moved when left arrow key is pressed
	 */
	public static final int PADDLE_MOVE_LEFT = -5;

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//PlayedGame Attributes
	private int score;
	private int lives;
	private int currentLevel;
	private double waitTime;
	private String playername;
	private double ballDirectionX;
	private double ballDirectionY;
	private double currentBallX;
	private double currentBallY;
	private double currentPaddleLength;
	private double currentPaddleX;
	private double currentPaddleY;

	//Autounique Attributes
	private int id;

	//PlayedGame State Machines
	public enum PlayStatus { Ready, Moving, Paused, GameOver }
	private PlayStatus playStatus;

	//PlayedGame Associations
	private Player player;
	private Game game;
	private List<PlayedBlockAssignment> blocks;
	private BouncePoint bounce;
	private Block223 block223;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
	{
		// line 47 "../../../../../Block223PlayMode.ump"
		boolean didAddGameResult = setGame(aGame);
		if (!didAddGameResult)
		{
			throw new RuntimeException("Unable to create playedGame due to game");
		}
		// END OF UMPLE BEFORE INJECTION
		score = 0;
		lives = NR_LIVES;
		currentLevel = 1;
		waitTime = INITIAL_WAIT_TIME;
		playername = aPlayername;
		resetBallDirectionX();
		resetBallDirectionY();
		resetCurrentBallX();
		resetCurrentBallY();
		currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
		resetCurrentPaddleX();
		currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
		id = nextId++;
		boolean didAddGame = setGame(aGame);
		if (!didAddGame)
		{
			throw new RuntimeException("Unable to create playedGame due to game");
		}
		blocks = new ArrayList<PlayedBlockAssignment>();
		boolean didAddBlock223 = setBlock223(aBlock223);
		if (!didAddBlock223)
		{
			throw new RuntimeException("Unable to create playedGame due to block223");
		}
		setPlayStatus(PlayStatus.Ready);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setScore(int aScore)
	{
		boolean wasSet = false;
		score = aScore;
		wasSet = true;
		return wasSet;
	}

	public boolean setLives(int aLives)
	{
		boolean wasSet = false;
		lives = aLives;
		wasSet = true;
		return wasSet;
	}

	public boolean setCurrentLevel(int aCurrentLevel)
	{
		boolean wasSet = false;
		currentLevel = aCurrentLevel;
		wasSet = true;
		return wasSet;
	}

	public boolean setWaitTime(double aWaitTime)
	{
		boolean wasSet = false;
		waitTime = aWaitTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setPlayername(String aPlayername)
	{
		boolean wasSet = false;
		playername = aPlayername;
		wasSet = true;
		return wasSet;
	}
	/* Code from template attribute_SetDefaulted */
	public boolean setBallDirectionX(double aBallDirectionX)
	{
		boolean wasSet = false;
		ballDirectionX = aBallDirectionX;
		wasSet = true;
		return wasSet;
	}

	public boolean resetBallDirectionX()
	{
		boolean wasReset = false;
		ballDirectionX = getDefaultBallDirectionX();
		wasReset = true;
		return wasReset;
	}
	/* Code from template attribute_SetDefaulted */
	public boolean setBallDirectionY(double aBallDirectionY)
	{
		boolean wasSet = false;
		ballDirectionY = aBallDirectionY;
		wasSet = true;
		return wasSet;
	}

	public boolean resetBallDirectionY()
	{
		boolean wasReset = false;
		ballDirectionY = getDefaultBallDirectionY();
		wasReset = true;
		return wasReset;
	}
	/* Code from template attribute_SetDefaulted */
	public boolean setCurrentBallX(double aCurrentBallX)
	{
		boolean wasSet = false;
		currentBallX = aCurrentBallX;
		wasSet = true;
		return wasSet;
	}

	public boolean resetCurrentBallX()
	{
		boolean wasReset = false;
		currentBallX = getDefaultCurrentBallX();
		wasReset = true;
		return wasReset;
	}
	/* Code from template attribute_SetDefaulted */
	public boolean setCurrentBallY(double aCurrentBallY)
	{
		boolean wasSet = false;
		currentBallY = aCurrentBallY;
		wasSet = true;
		return wasSet;
	}

	public boolean resetCurrentBallY()
	{
		boolean wasReset = false;
		currentBallY = getDefaultCurrentBallY();
		wasReset = true;
		return wasReset;
	}

	public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
	{
		boolean wasSet = false;
		currentPaddleLength = aCurrentPaddleLength;
		wasSet = true;
		return wasSet;
	}
	/* Code from template attribute_SetDefaulted */
	public boolean setCurrentPaddleX(double aCurrentPaddleX)
	{
		boolean wasSet = false;
		currentPaddleX = aCurrentPaddleX;
		wasSet = true;
		return wasSet;
	}

	public boolean resetCurrentPaddleX()
	{
		boolean wasReset = false;
		currentPaddleX = getDefaultCurrentPaddleX();
		wasReset = true;
		return wasReset;
	}

	public int getScore()
	{
		return score;
	}

	public int getLives()
	{
		return lives;
	}

	public int getCurrentLevel()
	{
		return currentLevel;
	}

	public double getWaitTime()
	{
		return waitTime;
	}

	/**
	 * added here so that it only needs to be determined once
	 */
	public String getPlayername()
	{
		return playername;
	}

	/**
	 * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
	 */
	public double getBallDirectionX()
	{
		return ballDirectionX;
	}
	/* Code from template attribute_GetDefaulted */
	public double getDefaultBallDirectionX()
	{
		return getGame().getBall().getMinBallSpeedX();
	}

	public double getBallDirectionY()
	{
		return ballDirectionY;
	}
	/* Code from template attribute_GetDefaulted */
	public double getDefaultBallDirectionY()
	{
		return getGame().getBall().getMinBallSpeedY();
	}

	/**
	 * the position of the ball is at the center of the ball
	 */
	public double getCurrentBallX()
	{
		return currentBallX;
	}
	/* Code from template attribute_GetDefaulted */
	public double getDefaultCurrentBallX()
	{
		return BALL_INITIAL_X;
	}

	public double getCurrentBallY()
	{
		return currentBallY;
	}
	/* Code from template attribute_GetDefaulted */
	public double getDefaultCurrentBallY()
	{
		return BALL_INITIAL_Y;
	}

	public double getCurrentPaddleLength()
	{
		return currentPaddleLength;
	}

	/**
	 * the position of the paddle is at its top left corner
	 */
	public double getCurrentPaddleX()
	{
		return currentPaddleX;
	}
	/* Code from template attribute_GetDefaulted */
	public double getDefaultCurrentPaddleX()
	{
		return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
	}

	public double getCurrentPaddleY()
	{
		return currentPaddleY;
	}

	public int getId()
	{
		return id;
	}

	public String getPlayStatusFullName()
	{
		String answer = playStatus.toString();
		return answer;
	}

	public PlayStatus getPlayStatus()
	{
		return playStatus;
	}

	public boolean play()
	{
		boolean wasEventProcessed = false;

		PlayStatus aPlayStatus = playStatus;
		switch (aPlayStatus)
		{
		case Ready:
			setPlayStatus(PlayStatus.Moving);
			wasEventProcessed = true;
			break;
		case Paused:
			setPlayStatus(PlayStatus.Moving);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean pause()
	{
		boolean wasEventProcessed = false;

		PlayStatus aPlayStatus = playStatus;
		switch (aPlayStatus)
		{
		case Moving:
			setPlayStatus(PlayStatus.Paused);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean move()
	{
		boolean wasEventProcessed = false;

		PlayStatus aPlayStatus = playStatus;
		switch (aPlayStatus)
		{
		case Moving:
			if (hitPaddle())
			{
				// line 12 "../../../../../Block223States.ump"
				doHitPaddleOrWall();
				setPlayStatus(PlayStatus.Moving);
				wasEventProcessed = true;
				break;
			}
			if (isOutOfBoundsAndLastLife())
			{
				// line 13 "../../../../../Block223States.ump"
				doOutOfBounds();
				setPlayStatus(PlayStatus.GameOver);
				wasEventProcessed = true;
				break;
			}
			if (isOutOfBounds())
			{
				// line 14 "../../../../../Block223States.ump"
				doOutOfBounds();
				setPlayStatus(PlayStatus.Paused);
				wasEventProcessed = true;
				break;
			}
			if (hitLastBlockAndLastLevel())
			{
				// line 15 "../../../../../Block223States.ump"
				doHitBlock();
				setPlayStatus(PlayStatus.GameOver);
				wasEventProcessed = true;
				break;
			}
			if (hitLastBlock())
			{
				// line 16 "../../../../../Block223States.ump"
				doHitBlockNextLevel();
				setPlayStatus(PlayStatus.Ready);
				wasEventProcessed = true;
				break;
			}
			if (hitBlock())
			{
				// line 17 "../../../../../Block223States.ump"
				doHitBlock();
				setPlayStatus(PlayStatus.Moving);
				wasEventProcessed = true;

				break;
			}
			if (hitWall())
			{
				// line 18 "../../../../../Block223States.ump"
				doHitPaddleOrWall();
				setPlayStatus(PlayStatus.Moving);
				wasEventProcessed = true;
				break;
			}
			// line 19 "../../../../../Block223States.ump"
			doHitNothingAndNotOutOfBounds();
			setPlayStatus(PlayStatus.Moving);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	private void setPlayStatus(PlayStatus aPlayStatus)
	{
		playStatus = aPlayStatus;

		// entry actions and do activities
		switch(playStatus)
		{
		case Ready:
			// line 7 "../../../../../Block223States.ump"
			doSetup();
			break;
		case GameOver:
			// line 25 "../../../../../Block223States.ump"
			doGameOver();
			break;
		}
	}
	/* Code from template association_GetOne */
	public Player getPlayer()
	{
		return player;
	}

	public boolean hasPlayer()
	{
		boolean has = player != null;
		return has;
	}
	/* Code from template association_GetOne */
	public Game getGame()
	{
		return game;
	}
	/* Code from template association_GetMany */
	public PlayedBlockAssignment getBlock(int index)
	{
		PlayedBlockAssignment aBlock = blocks.get(index);
		return aBlock;
	}

	public List<PlayedBlockAssignment> getBlocks()
	{
		List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
		return newBlocks;
	}

	public int numberOfBlocks()
	{
		int number = blocks.size();
		return number;
	}

	public boolean hasBlocks()
	{
		boolean has = blocks.size() > 0;
		return has;
	}

	public int indexOfBlock(PlayedBlockAssignment aBlock)
	{
		int index = blocks.indexOf(aBlock);
		return index;
	}
	/* Code from template association_GetOne */
	public BouncePoint getBounce()
	{
		return bounce;
	}

	public boolean hasBounce()
	{
		boolean has = bounce != null;
		return has;
	}
	/* Code from template association_GetOne */
	public Block223 getBlock223()
	{
		return block223;
	}
	/* Code from template association_SetOptionalOneToMany */
	public boolean setPlayer(Player aPlayer)
	{
		boolean wasSet = false;
		Player existingPlayer = player;
		player = aPlayer;
		if (existingPlayer != null && !existingPlayer.equals(aPlayer))
		{
			existingPlayer.removePlayedGame(this);
		}
		if (aPlayer != null)
		{
			aPlayer.addPlayedGame(this);
		}
		wasSet = true;
		return wasSet;
	}
	/* Code from template association_SetOneToMany */
	public boolean setGame(Game aGame)
	{
		boolean wasSet = false;
		if (aGame == null)
		{
			return wasSet;
		}

		Game existingGame = game;
		game = aGame;
		if (existingGame != null && !existingGame.equals(aGame))
		{
			existingGame.removePlayedGame(this);
		}
		game.addPlayedGame(this);
		wasSet = true;
		return wasSet;
	}
	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfBlocks()
	{
		return 0;
	}
	/* Code from template association_AddManyToOne */
	public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
	{
		return new PlayedBlockAssignment(aX, aY, aBlock, this);
	}

	public boolean addBlock(PlayedBlockAssignment aBlock)
	{
		boolean wasAdded = false;
		if (blocks.contains(aBlock)) { return false; }
		PlayedGame existingPlayedGame = aBlock.getPlayedGame();
		boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
		if (isNewPlayedGame)
		{
			aBlock.setPlayedGame(this);
		}
		else
		{
			blocks.add(aBlock);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeBlock(PlayedBlockAssignment aBlock)
	{
		boolean wasRemoved = false;
		//Unable to remove aBlock, as it must always have a playedGame
		if (!this.equals(aBlock.getPlayedGame()))
		{
			blocks.remove(aBlock);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
	{  
		boolean wasAdded = false;
		if(addBlock(aBlock))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
			blocks.remove(aBlock);
			blocks.add(index, aBlock);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
	{
		boolean wasAdded = false;
		if(blocks.contains(aBlock))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
			blocks.remove(aBlock);
			blocks.add(index, aBlock);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addBlockAt(aBlock, index);
		}
		return wasAdded;
	}
	/* Code from template association_SetUnidirectionalOptionalOne */
	public boolean setBounce(BouncePoint aNewBounce)
	{
		boolean wasSet = false;
		bounce = aNewBounce;
		wasSet = true;
		return wasSet;
	}
	/* Code from template association_SetOneToMany */
	public boolean setBlock223(Block223 aBlock223)
	{
		boolean wasSet = false;
		if (aBlock223 == null)
		{
			return wasSet;
		}

		Block223 existingBlock223 = block223;
		block223 = aBlock223;
		if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
		{
			existingBlock223.removePlayedGame(this);
		}
		block223.addPlayedGame(this);
		wasSet = true;
		return wasSet;
	}

	public void delete()
	{
		if (player != null)
		{
			Player placeholderPlayer = player;
			this.player = null;
			placeholderPlayer.removePlayedGame(this);
		}
		Game placeholderGame = game;
		this.game = null;
		if(placeholderGame != null)
		{
			placeholderGame.removePlayedGame(this);
		}
		while (blocks.size() > 0)
		{
			PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
			aBlock.delete();
			blocks.remove(aBlock);
		}

		bounce = null;
		Block223 placeholderBlock223 = block223;
		this.block223 = null;
		if(placeholderBlock223 != null)
		{
			placeholderBlock223.removePlayedGame(this);
		}
	}

	// line 55 "../../../../../Block223PlayMode.ump"
	private boolean isCloser(BouncePoint bp1, BouncePoint bp2){
		if (bp1==null)
			return false;
		if(bp2==null)
			return true;
		else{
			double diff1=Math.abs(bp1.getX()-getCurrentBallX())+Math.abs(bp1.getY()-getCurrentBallY());
			double diff2=Math.abs(bp2.getX()-getCurrentBallY())+Math.abs(bp2.getY()-getCurrentBallY());
			return diff2>diff1;
		}
	}

	// line 127 "../../../../../Block223Persistence.ump"
	public static  void reinitializeAutouniquePgameID(List<PlayedGame> pgames){
		nextId = 0; 
		for (PlayedGame pgame : pgames) {
			if (pgame.getId() > nextId) {
				nextId = pgame.getId();
			}
		}
		nextId++;
	}


	/**
	 * Guards
	 */
	// line 32 "../../../../../Block223States.ump"
	private boolean hitPaddle(){
		BouncePoint bp = calculateBouncePointPaddle();
		setBounce(bp);
		if (bp != null) {
			return true;
		}
		else 
			return false;
	}

	// line 42 "../../../../../Block223States.ump"
	private BouncePoint calculateBouncePointPaddle(){
		// Current position of the ball
		double x = getCurrentBallX();
		double y = getCurrentBallY();
		// Where the ball is going
		double dx = getBallDirectionX();
		double dy = getBallDirectionY();
		double slope = dy/dx;
		// New position of the ball
		double positionX = (x+dx);
		double positionY = (y+dy);


		BouncePoint bp = null;

		// Add 5 to all the variables to account for the radius of the ball
		Rectangle2D regionA = new Rectangle2D.Double(getCurrentPaddleX(), getCurrentPaddleY()-5.0, getCurrentPaddleLength(), 5.0);
		Rectangle2D regionB = new Rectangle2D.Double(getCurrentPaddleX()-5, getCurrentPaddleY(), 5.0, 5.0);
		Rectangle2D regionC = new Rectangle2D.Double(getCurrentPaddleX()+getCurrentPaddleLength(), getCurrentPaddleY(), 5.0, 5.0);
		Rectangle2D regionF = new Rectangle2D.Double(getCurrentPaddleX()+getCurrentPaddleLength(), getCurrentPaddleY()-5.0, 3.0, 3.0);
		Rectangle2D regionE = new Rectangle2D.Double(getCurrentPaddleX()-5, getCurrentPaddleY()-5, 3.0, 3.0);

		java.awt.geom.Ellipse2D ball= new java.awt.geom.Ellipse2D.Double(positionX-5, positionY-5, 10, 10);

		java.awt.geom.Line2D path=new java.awt.geom.Line2D.Double(positionX, positionY, x, y);

		// BOUNCE

		// Section E
		if (ball.intersects(regionE)) {
			bp = new BouncePoint(0,0,null);
			// If approaching from the right, flip Y
			if(dx <= 0.0) {
				//double newX = x+(regionE.getY()-y)/slope;
				List<Point2D> points = getInterectionPoints(path, regionE.getX()+5, regionE.getY()+5, 5);
				if (points != null && !points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				} }
			// If approaching from the left, flip X
			else if (dx > 0.0) {
				List<Point2D> points = getInterectionPoints(path, regionE.getX()+5, regionE.getY()+5, 5);
				if (points != null && !points.isEmpty()) {
					//double newY = y+slope*(bp.getX()-x);
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
				} }
		}

		//Section F
		else if (ball.intersects(regionF)) {
			bp = new BouncePoint(0,0,null);
			// If approaching from the left, flip Y
			if (dx > 0.0) {
				List<Point2D> points = getInterectionPoints(path, regionF.getX(), regionF.getY()+5, 5);
				if (points != null && !points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				}
			} else if (dx <= 0.0) {
				List<Point2D> points = getInterectionPoints(path, regionF.getX(), regionF.getY()+5, 5);
				if (points != null && !points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
				}
			}
		}
		if ((bp!=null&&bp.getDirection()==null)||bp==null) {
			// Section C
			if (ball.intersects(regionC)&&dx<0) {
				bp = new BouncePoint(0,0,null);
				bp.setX(regionC.getX()+5);
				double newY = y+slope*(bp.getX()-x);
				bp.setY(newY);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
			}

			// Section A
			else if (ball.intersects(regionA)&&dy>0) {
				bp = new BouncePoint(0,0,null);
				bp.setY(regionA.getY());
				double newX = x+(bp.getY()-y)/slope;
				bp.setX(newX);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
			}

			// Section B
			else if (ball.intersects(regionB)&&dx>0) {
				bp = new BouncePoint(0,0,null);
				bp.setX(regionB.getX());
				double newY = y+slope*(bp.getX()-x);
				bp.setY(newY);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
			}
		}

		return bp;
	}

	// line 113 "../../../../../Block223States.ump"
	private void bounceBall(){
		double incomingX;
		double remainingX;
		double incomingY;
		double remainingY;

		double currentx=getCurrentBallX();
		double currenty=getCurrentBallY();
		double bpx=bounce.getX();
		double bpy=bounce.getY();
		double dx=getBallDirectionX();
		double dy=getBallDirectionY();

		// Flip_X case
		if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_X) {
			// Incoming distance
			incomingX = (bpx - currentx);
			remainingX = (dx - incomingX);
			if (remainingX == 0) {
				setCurrentBallX(bounce.getX());
				setCurrentBallY(bounce.getY());

				if (bounce.getHitBlock()!=null) {
					double sign=Math.signum(dy);
					if (sign==0) {sign=1;}
					setBallDirectionX(dx*(-1));
					setBallDirectionY(dy + sign*0.1*Math.abs(dx));
				}
			}
			else{
				double sign=Math.signum(dy);
				if (sign==0) {sign=1;}
				setBallDirectionX(dx*(-1));
				setBallDirectionY(dy + sign*0.1*Math.abs(dx));

				setCurrentBallX(bounce.getX() + (remainingX/dx)*(getBallDirectionX()));
				setCurrentBallY(bounce.getY() + (remainingX/dx)*(getBallDirectionY()));

			}
		}

		// Flip_Y case
		if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_Y) {
			// Incoming distance
			incomingY = (bpy - currenty);
			remainingY = (dy - incomingY);


			if (remainingY == 0) {
				setCurrentBallX(bounce.getX());
				setCurrentBallY(bounce.getY());

				if(bounce.getHitBlock()!=null) {
					double sign=Math.signum(dx);
					if (sign==0) {sign=1;}
					setBallDirectionY(dy*(-1));
					setBallDirectionX(dx+sign*0.1*Math.abs(dy));
				}

			}
			else
			{
				double sign=Math.signum(dx);
				if (sign==0) {sign=1;}

				setBallDirectionY(dy*(-1));
				setBallDirectionX(dx+sign*0.1*Math.abs(getBallDirectionY()));
				setCurrentBallX(bounce.getX() + (remainingY/dy)*(getBallDirectionX()));
				setCurrentBallY(bounce.getY() + (remainingY/dy)*(getBallDirectionY()));
			} 
		}

		// Flip_Both Case
		if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_BOTH) {
			// Incoming distance for both X and Y
			incomingY = (bpy - currenty);
			incomingX = (bpx - currentx);
			remainingY = (dy - incomingY);
			remainingX = (dx - incomingX);
			if (remainingY == 0 && remainingX == 0) {
				setCurrentBallY(bounce.getY());
				setCurrentBallX(bounce.getX());

			} else {
				setBallDirectionY(dy*(-1));
				setBallDirectionX(dx*(-1));

				setCurrentBallY(bounce.getY() + (remainingY/dy)*(getBallDirectionY()));
				setCurrentBallX(bounce.getX() + (remainingX/dx)*(getBallDirectionX()));
			}
		}
		
		if (ballDirectionX >= 10 || ballDirectionY >= 10) {
			ballDirectionX = ballDirectionX/10;
			ballDirectionY = ballDirectionY/10;
		}

		bounce=null; 
	}

	// line 169 "../../../../../Block223States.ump"
	private boolean isOutOfBoundsAndLastLife(){
		boolean outOfBounds = false;
		if(lives == 1)
			outOfBounds = isBallOutOfBounds();
		return outOfBounds;
	}

	// line 176 "../../../../../Block223States.ump"
	private boolean isBallOutOfBounds(){
		if (getCurrentBallY() > 385)
			return true;
		else return false;
	}

	// line 182 "../../../../../Block223States.ump"
	private boolean isOutOfBounds(){
		boolean outOfBounds = isBallOutOfBounds();
		return outOfBounds;
	}

	// line 188 "../../../../../Block223States.ump"
	private boolean hitLastBlockAndLastLevel(){
		Game game = getGame();
		int nrLevels = game.numberOfLevels();
		setBounce(null);

		if(nrLevels == currentLevel) {
			int nrBlocks = numberOfBlocks();

			if(nrBlocks == 1) {

				PlayedBlockAssignment block = getBlock(0);
				BouncePoint bp = calculateBouncePointBlock(block);
				setBounce(bp);
				return (bp!=null);
			}
		}
		return false;
	}

	// line 208 "../../../../../Block223States.ump"
	private boolean hitLastBlock(){
		int nrBlocks = numberOfBlocks();
		setBounce(null);
		if(nrBlocks == 1) {
			PlayedBlockAssignment block = getBlock(0);
			BouncePoint bp = calculateBouncePointBlock(block);
			this.setBounce(bp);
			return (bp != null);
		}
		return false;
	}

	// line 220 "../../../../../Block223States.ump"
	private boolean hitBlock(){
		int nrBlocks = this.numberOfBlocks();
		setBounce(null);
		for(int i=0; i<nrBlocks-1; i++) {	
			PlayedBlockAssignment block = getBlock(i);
			BouncePoint bp = calculateBouncePointBlock(block);
			BouncePoint bounce = this.getBounce();
			boolean closer = isCloser(bp, bounce);
			if(closer) {
				this.setBounce(bp);
			}
		}

		return getBounce()!=null;
	}

	// line 241 "../../../../../Block223States.ump"
	private boolean hitWall(){
		BouncePoint bp = calculateBouncePointWall();
		setBounce(bp);
		if (bp != null) {
			return true;
		}
		else
			return false;
	}

	// line 251 "../../../../../Block223States.ump"
	private BouncePoint calculateBouncePointWall(){
		// Current position of the ball
		double x = getCurrentBallX();
		double y = getCurrentBallY();
		// Where the ball is going
		double dx = getBallDirectionX();
		double dy = getBallDirectionY();
		double slope=dy/dx;
		// New position of the ball
		double positionX = (x+dx);
		double positionY = (y+dy);

		BouncePoint bp = null;
		java.awt.geom.Rectangle2D regionA=new java.awt.geom.Rectangle2D.Double(0, 0, 5, 390-5);

		java.awt.geom.Rectangle2D regionC=new java.awt.geom.Rectangle2D.Double(390-5, 0, 5, 390-5);

		java.awt.geom.Rectangle2D regionD=new java.awt.geom.Rectangle2D.Double(0, 0, 390-10, 5);

		java.awt.geom.Ellipse2D ball= new java.awt.geom.Ellipse2D.Double(positionX-5, positionY-5, 10, 10);


		// A-B Corner
		if (positionX == 5 && positionY == 5) {
			bp = new BouncePoint(0,0,null);
			bp.setX(5);
			bp.setY(5);
			bp.setDirection(BouncePoint.BounceDirection.FLIP_BOTH);
		}

		// B-C Corner
		else if (positionX == 385 && positionY == 5 ) {
			bp = new BouncePoint(0,0,null);
			bp.setX(385);
			bp.setY(5);
			bp.setDirection(BouncePoint.BounceDirection.FLIP_BOTH);
		}


		// Section A
		else if (ball.intersects(regionC)&&dx>0) {
			bp = new BouncePoint(0,0,null);
			bp.setX(390-5);
			double newy=y+slope*(bp.getX()-x);
			bp.setY(newy);
			bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
		}
		
		else if (ball.intersects(regionA)&&dx<0) {
			bp = new BouncePoint(0,0,null);
			bp.setX(5);
			double newy=y+slope*(bp.getX()-x);
			bp.setY(newy);
			bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
		}

		// Section B
		else if (ball.intersects(regionD)&&dy<0) {
			bp = new BouncePoint(0,0,null);
			bp.setY(5);
			double newx=x+(bp.getY()-y)/slope;
			bp.setX(newx);
			bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
		}


		return bp;
	}


	// line 306 "../../../../../Block223States.ump"
	private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment pblock){
		// Current position of the ball
		double x = getCurrentBallX();
		double y = getCurrentBallY();
		// Where the ball is going
		double dx = getBallDirectionX();
		double dy = getBallDirectionY();
		double slope=dy/dx;
		// New position of the ball
		double positionX = (x+dx);
		double positionY = (y+dy);

		BouncePoint bp = null; 
		//java.awt.geom.Rectangle2D physicalblock=new java.awt.geom.Rectangle2D.Double(pblock.getX()-10, pblock.getY()-10, 30.0, 30.0);

		java.awt.geom.Rectangle2D regionA=new java.awt.geom.Rectangle2D.Double(pblock.getX(), pblock.getY()-5, 20, 5);

		java.awt.geom.Rectangle2D regionD=new java.awt.geom.Rectangle2D.Double(pblock.getX(), pblock.getY()+20, 20, 5);

		java.awt.geom.Rectangle2D regionB=new java.awt.geom.Rectangle2D.Double(pblock.getX()-5, pblock.getY(), 5, 20);

		java.awt.geom.Rectangle2D regionC=new java.awt.geom.Rectangle2D.Double(pblock.getX()+20, pblock.getY(), 5, 20);

		java.awt.geom.Rectangle2D regionE=new java.awt.geom.Rectangle2D.Double(pblock.getX()-5, pblock.getY()-5, 5, 5);

		java.awt.geom.Rectangle2D regionF=new java.awt.geom.Rectangle2D.Double(pblock.getX()+20, pblock.getY()-5, 5, 5);

		java.awt.geom.Rectangle2D regionG=new java.awt.geom.Rectangle2D.Double(pblock.getX()-5, pblock.getY()+20, 5, 5);

		java.awt.geom.Rectangle2D regionH=new java.awt.geom.Rectangle2D.Double(pblock.getX()+20, pblock.getY()+20, 5, 5);

		java.awt.geom.Ellipse2D ball=new java.awt.geom.Ellipse2D.Double(positionX-5, positionY-5, 10, 10);

		java.awt.geom.Line2D path=new java.awt.geom.Line2D.Double(positionX, positionY, x, y);


		if (ball.intersects(regionE)) {
			bp = new BouncePoint(0,0,null);
			bp.setHitBlock(pblock);
			if  (dx<=0.0) {
				double newx=x+(regionE.getY()-y)/slope;
				List<Point2D> points = getInterectionPoints(path, pblock.getX(), pblock.getY(), 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				}}
			else if(dx>0.0) {
				bp.setX(regionE.getX());
				List<Point2D> points = getInterectionPoints(path, pblock.getX(), pblock.getY(), 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
					//flip Y if ball approaches from right and Flip X if ball approaches from left
				}}
		}
		//Section F
		else if (ball.intersects(regionF)) {
			bp = new BouncePoint(0,0,null);
			bp.setHitBlock(pblock);
			if  (dx>0.0) {
				List<Point2D> points = getInterectionPoints(path, pblock.getX()+20, pblock.getY(), 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				}}
			else if(dx<=0.0) {
				bp.setX(regionF.getX()+5);
				List<Point2D> points = getInterectionPoints(path, pblock.getX()+20, pblock.getY(), 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
					//flip Y if ball approaches from right and Flip X if ball approaches from left
				}}
		}
		//sectionG
		else if (ball.intersects(regionG)) {
			bp = new BouncePoint(0,0,null);
			bp.setHitBlock(pblock);
			if  (dx<=0.0) {
				List<Point2D> points = getInterectionPoints(path, pblock.getX(), pblock.getY()+20, 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				}}
			else if(dx>0.0) {
				List<Point2D> points = getInterectionPoints(path, pblock.getX(), pblock.getY()+20, 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
					//flip Y if ball approaches from right and Flip X if ball approaches from left
				}}
		}
		//Section H
		else if (ball.intersects(regionH)) {
			bp = new BouncePoint(0,0,null);
			bp.setHitBlock(pblock);
			if  (dx>0.0) {
				List<Point2D> points = getInterectionPoints(path, pblock.getX()+20, pblock.getY()+20, 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
				}}
			else if(dx<=0.0) {
				List<Point2D> points = getInterectionPoints(path, pblock.getX()+20, pblock.getY()+20, 5);
				if (points!=null&&!points.isEmpty()) {
					bp.setX(points.get(0).getX());
					bp.setY(points.get(0).getY());
					bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
					//flip Y if ball approaches from right and Flip X if ball approaches from left
				}}
		}
		// Section A
		if ((bp!=null&&bp.getDirection()==null)||bp==null) {
			if (ball.intersects(regionA)) {
				bp = new BouncePoint(0,0,null);
				bp.setHitBlock(pblock);
				bp.setY(regionA.getY());
				double newx=x+(bp.getY()-y)/slope;
				bp.setX(newx);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
			}
			//sectionD
			else if (ball.intersects(regionD)) {
				bp = new BouncePoint(0,0,null);
				bp.setHitBlock(pblock);
				bp.setY(regionD.getY()+5);
				double newx=x+(bp.getY()-y)/slope;
				bp.setX(newx);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_Y);
			}

			// Section B
			else if (ball.intersects(regionB)) {
				bp = new BouncePoint(0,0,null);
				bp.setHitBlock(pblock);
				bp.setX(regionB.getX());
				double newy=y+slope*(bp.getX()-x);
				bp.setY(newy);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
			}

			else if (ball.intersects(regionC)) {
				bp = new BouncePoint(0,0,null);
				bp.setHitBlock(pblock);
				bp.setX(regionC.getX()+5);
				double newy=y+slope*(bp.getX()-x);
				bp.setY(newy);
				bp.setDirection(BouncePoint.BounceDirection.FLIP_X);
			}
		}

		return bp;
	}


	/**
	 * Actions
	 */
	// line 382 "../../../../../Block223States.ump"
	private void doSetup(){
		Random rgen=new Random();
		doReset();
		Game game=getGame();
		Level level = game.getLevel(currentLevel-1);
		List<BlockAssignment> assignments= level.getBlockAssignments();

		for (BlockAssignment a: assignments){
			PlayedBlockAssignment pblock=new PlayedBlockAssignment(Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(a.getGridHorizontalPosition()-1),
					Game.WALL_PADDING+(Block.SIZE+Game.ROW_PADDING)*(a.getGridVerticalPosition()-1), a.getBlock(), this);
		}

		while (numberOfBlocks()<game.getNrBlocksPerLevel()) {
			int x=rgen.nextInt(15)+1;
			int y=rgen.nextInt(10)+1;
			while (level.findBlockAssignment(x,y)!=null) {
				x++;
				if (x==15) {
					y++;
					x=1;
				}
			}
			PlayedBlockAssignment pblock=new PlayedBlockAssignment(Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(x-1),
					Game.WALL_PADDING+(Block.SIZE+Game.ROW_PADDING)*(y-1), game.getRandomBlock(), this);

		}
	}

	// line 410 "../../../../../Block223States.ump"
	private void doReset(){
		resetCurrentBallX();
		resetCurrentBallY();
		resetBallDirectionX();
		resetBallDirectionY();
		resetCurrentPaddleX();
	}

	// line 418 "../../../../../Block223States.ump"
	private void doHitPaddleOrWall(){
		bounceBall();
	}

	// line 423 "../../../../../Block223States.ump"
	private void doOutOfBounds(){
		setLives(lives-1);
		doReset();
	}

	// line 428 "../../../../../Block223States.ump"
	private void doHitBlock(){
		score = getScore();   
		bounce = getBounce();
		PlayedBlockAssignment pblock = bounce.getHitBlock();
		Block block = pblock.getBlock();
		int points = block.getPoints();
		setScore(score+points);
		pblock.delete();
		bounceBall();
	}

	// line 443 "../../../../../Block223States.ump"
	private void doHitBlockNextLevel(){
		doHitBlock();
		int level = this.getCurrentLevel();

		setCurrentLevel(level+1);
		setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength()-(getGame().getPaddle().getMaxPaddleLength()-getGame().getPaddle().getMinPaddleLength())/(getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
		setWaitTime(INITIAL_WAIT_TIME*getGame().getBall().getBallSpeedIncreaseFactor());
	}

	// line 453 "../../../../../Block223States.ump"
	private void doHitNothingAndNotOutOfBounds(){
		double x = getCurrentBallX();
		double y = getCurrentBallY();

		double dx = getBallDirectionX();
		double dy = getBallDirectionY();

		setCurrentBallX(x+dx);
		setCurrentBallY(y+dy);
	}

	// line 465 "../../../../../Block223States.ump"
	private void doGameOver(){
		Block223 block223=this.getBlock223();
		Player p=this.getPlayer();

		if (p!=null){
			Game game=this.getGame();
			HallOfFameEntry hof=new HallOfFameEntry(score, playername, p, game, block223);
			game.setMostRecentEntry(hof);
			//this.delete();
		}
	}


	public String toString()
	{
		return super.toString() + "["+
				"id" + ":" + getId()+ "," +
				"score" + ":" + getScore()+ "," +
				"lives" + ":" + getLives()+ "," +
				"currentLevel" + ":" + getCurrentLevel()+ "," +
				"waitTime" + ":" + getWaitTime()+ "," +
				"playername" + ":" + getPlayername()+ "," +
				"ballDirectionX" + ":" + getBallDirectionX()+ "," +
				"ballDirectionY" + ":" + getBallDirectionY()+ "," +
				"currentBallX" + ":" + getCurrentBallX()+ "," +
				"currentBallY" + ":" + getCurrentBallY()+ "," +
				"currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
				"currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
				"currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
				"  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
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
	//------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	//------------------------

	// line 124 "../../../../../Block223Persistence.ump"
	private static final long serialVersionUID = 11789647876L ;


}
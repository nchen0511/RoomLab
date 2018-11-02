package Game;

import People.Person;
import Rooms.Room;
import Rooms.TPRoom;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {
	

	private static boolean gameOn = true;

	//sets up board and player1 outside of scope so other methods can access them
	public static Room[][] board;
	public static Person player1;
	public static int fragCounter;
	public static int hp;
	public static String[][] fullMap;
	public static boolean[][] shownLoc;
	public static String fowMap = "";

	public static void main(String[] args)
	{
		System.out.println("You are an adventurer on a quest to cleanse a nearby forest of a goblin infestation. Depending on the difficulty, you must slain a certain amount of goblins in order to win.");
		System.out.println("You may type leave to end the game whenever.");
		System.out.println("What is your name?");
		Scanner in = new Scanner(System.in);
		player1 = new Person(0,0);

		System.out.println("Welcome, what difficulty would you like to play in? (easy/medium/hard)");
		String input;
		int difficulty;
		while(true){
			input = in.nextLine().toLowerCase().trim(); 
			if(input.equals("easy")){
				difficulty = 1;
				break;
			} else if(input.equals("medium")) {
				difficulty = 2;
				break;
			} else if(input.equals("hard")){
				difficulty = 3;
				break;
			} else {
				System.out.println("Please select a difficulty. (easy/medium/hard) ");
			}
		}

		//sets up size of the board depending on difficulty;
		board = new Room[difficulty*4][difficulty*4];
		//Fill the board with normal rooms
		for (int x = 0; x<board.length; x++)
		{
			for (int y = 0; y < board[x].length; y++)
			{
				board[x][y] = new Room(x,y);
			}
		}
		
		//Create 1~3 TP room depending on difficulty.
		for (int i = 0; i < difficulty;i++) {
			int x = (int) (Math.random() * board.length);
			int y = (int) (Math.random() * board.length);
			board[x][y] = new TPRoom(x, y);
		}

		 //Puts player into default room
		System.out.println("You enter the forest...");
		board[1][1].enterRoom(player1);
		updateMap();

		while(gameOn)
		{
			System.out.println("What would you like to do? (W, A, S, D, status, map, scout)");
			String move = in.nextLine();
			if(validMove(move, player1, board))
			{
				System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
				updateMap();
			}
			else {
				System.out.println("You can't do that! Please choose a valid option.");
			}
			
			
		}
		in.close();
	}

	/**
	 * Checks that the movement chosen is within the valid game map.
	 * @param move the move chosen
	 * @param p person moving
	 * @param map the 2D array of rooms
	 * @return
	 */
	public static boolean validMove(String move, Person p, Room[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "w":
				if (p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "d":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "a":
				if (p.getyLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "leave":
				{
					gameOff();
					return true;
				}
			case "status":
				{
					System.out.println("HP: " + hp);
					System.out.println("Frag Count: " + fragCounter);
					return true;
				}
			case "map":
				{
					getMap();
					return true;
				}
			case "scout":
				{
					scout();
				}
			default:
				break;
		}
		return true;
	}
	public static void gameOff()
	{
		gameOn = false;
	}

	public static void teleport(int x, int y){
		board[(int)(Math.random() * board.length)][(int)(Math.random() * board.length)].enterRoom(player1);
		updateMap();
	}

	public static void scout(){
	    int x = player1.getxLoc();
	    int y = player1.getyLoc();

	    //shows the 8 areas around the player on the map
		try {
			board[x - 1][y - 1].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x - 1][y].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x - 1][y + 1].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x][y - 1].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x][y + 1].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x + 1][y - 1].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x + 1][y].show();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			board[x + 1][y + 1].show();
		} catch (IndexOutOfBoundsException e) {
		}

        //updates map then displays map;
        updateMap();
 	    getMap();
		System.out.println("You scout the area...");
    }

    public static void updateMap(){
	    fowMap = "";

        for(int i=0;i<board.length;i++){
            for(int n=0;n<board[i].length;n++){
            	fowMap += "[" + board[i][n] + "]";
            }
            fowMap+="\n";
        }

        fowMap += "Key: O = You, N = Nothing, T = Teleport";
    }
	public static void getMap(){
		System.out.println(fowMap);
	}
}

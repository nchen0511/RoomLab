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
		String name = in.nextLine();
		player1 = new Person(name,0,0);

		System.out.println("Welcome, " + name + ". What difficulty would you like to play in? (easy/medium/hard)");
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

		fullMap = new String[board.length][board[0].length];
		shownLoc = new boolean[board.length][board[0].length];
		//Sets up fullMap, shownLoc, and fowMap
		for(int i=0;i<board.length;i++){
			for(int n=0;n<board[i].length;n++){
				fullMap[i][n] = board[i][n].toString();
				shownLoc[i][n] = false;
			}
		}

		for(int i=0;i<board.length;i++){
			for(int n=0;n<board[i].length;n++){
				fowMap+= "[ ]";
			}
			fowMap+="\n";
		}
		updateMap();


		 //Puts player into default room
		board[0][0].enterRoom(player1);

		while(gameOn)
		{
			System.out.println("What would you like to do? (N, S, E, W, status, map, scout)");
			String move = in.nextLine();
			if(validMove(move, player1, board))
			{
			//	System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
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
			case "n":
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
			case "e":
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

			case "w":
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
		board[x][y].leaveRoom(player1);
		board[(int)(Math.random() * board.length)][(int)(Math.random() * board.length)].enterRoom(player1);
	}

	public static void scout(){
	    int x = player1.getxLoc();
	    int y = player1.getyLoc();

	    //shows the 8 area around the player on the map, and the location the player is in
	    if(x-1>-1&&y+1>-1) {
            shownLoc[x-1][y+1] = true;
        }

 	    if(y+1>-1) {
            shownLoc[x][y+1] = true;
        }

 	    if(x+1<board[0].length-1&&y+1>-1) {
            shownLoc[x+1][y+1] = true;
        }

 	    if(x-1>-1) {
            shownLoc[x-1][y] = true;
        }

        shownLoc[x][y] = true;

 	    if(x+1<board[0].length) {
            shownLoc[x+1][y] = true;
        }

        if(x-1>-1&&y-1>-1) {
            shownLoc[x-1][y-1] = true;
        }

        if(y-1>-1) {
            shownLoc[x][y-1] = true;
        }

        if(x+1<board[0].length-1&&y-1>-1) {
            shownLoc[x+1][y-1] = true;
        }

        //updates map then displays map;
        updateMap();
 	    getMap();

    }

    public static void updateMap(){
	    fowMap = "";

        for(int i=0;i<board.length;i++){
            for(int n=0;n<board[i].length;n++){
                if(i==player1.getxLoc()&&n==player1.getyLoc()) {
                    fowMap += "[O]";
                } else {
                    if (shownLoc[i][n]) {
                        fowMap += "[" + fullMap[i][n] + "]";
                    } else {
                        fowMap += "[ ]";
                    }
                }
            }
            fowMap+="\n";
        }

        fowMap += "Key: O = You, N = Nothing, T = Teleport";
    }
	public static void getMap(){
		System.out.println(fowMap);
	}
}

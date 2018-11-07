//Nick Chen
package Game;

import People.Person;
import Rooms.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
	

	private static boolean gameOn = true;

	//sets up board and player1 outside of main so other methods can access them
	public static Board board;
	public static Person player1;
	public static int goal;

	public static String fowMap = "";

	public static void main(String[] args)
	{
		System.out.println("You are an adventurer on a quest to cleanse a nearby forest of a goblin infestation. Depending on the difficulty, you must slain a certain amount of goblins in order to win.");
		System.out.println("You may type leave to end the game whenever.");
		Scanner in = new Scanner(System.in);

		System.out.println("Welcome, what difficulty would you like to play in? (easy/medium/hard/custom)");
		String input;
		int difficulty;
		int customX = 0;
		int customY = 0;
		int area;
		while(true){
			input = in.nextLine().toLowerCase().trim(); 
			if(input.equals("easy")){
				difficulty = 1;
				goal = 8;
				break;
			} else if(input.equals("medium")) {
				difficulty = 2;
				goal = 32;
				break;
			} else if(input.equals("hard")) {
				difficulty = 3;
				goal = 128;
				break;
			} else if(input.equals("custom")){
				while(true) {
					System.out.println("Okay, please enter the width of the board.");
					try {
						int y = in.nextInt();
						customY = y;
						break;
					} catch (InputMismatchException e) {
						System.out.println("Please enter a valid number!");
						String clear = in.next();
					}
				}

				while(true){
					System.out.println("Okay, now enter the length of the board.");
					try {
						int x = in.nextInt();
						customX = x;
						break;
					} catch (InputMismatchException e){
						System.out.println("Please enter a valid number!");
						String clear = in.next();
					}
				}

				difficulty = 3;
				System.out.println("Alright, the room was set up according to your likings. Other factors will be calculated depending on board area and as hard difficulty.");
				break;
			} else {
				System.out.println("Please select a difficulty. (easy/medium/hard/custom) ");
			}
		}


		//sets up size of the board.room depending on difficulty;
		if(input.equals("custom")){
			board = new Board(new Room[customX][customY]);
		} else {
			board = new Board(difficulty * 4, difficulty * 4);
		}


		//sets up area and goal variable for later calculations
		area = board.room.length*board.room[0].length;
		goal = (int)(area*.5);


		//Fill the board.room with normal rooms
		for (int x = 0; x<board.room.length; x++)
		{
			for (int y = 0; y < board.room[x].length; y++)
			{
				board.room[x][y] = new Room(x,y);
			}
		}

		//Fill the board.room with goblin rooms
		for (int x = 0; x<board.room.length; x++)
		{
			for (int y = 0; y < board.room[x].length; y++)
			{
				board.room[x][y] = new GoblinRoom(x,y);
			}
		}


		//Creates ZRoom
		for (int i = 0; i < (int)(area*.1);i++) {
			int x = (int) (Math.random() * board.room.length);
			int y = (int) (Math.random() * board.room[0].length);
			board.room[x][y] = new AmbushRoom(x, y);
		}


		//Create TPRoom
		for (int i = 0; i < (int)(area*.1);i++) {
			int x = (int) (Math.random() * board.room.length);
			int y = (int) (Math.random() * board.room[0].length);
			board.room[x][y] = new TPRoom(x, y);
		}

		//Ensures the starting room is normal
		board.room[0][0] = new Room(0,0);

        player1 = new Person(0,0, (int)(area*.05)+1);
		 //Puts player into default room
		System.out.println("You enter the forest...");
		board.room[0][0].enterRoom(player1);
		updateMap();


		while(gameOn)
		{
			System.out.println("What would you like to do? (W, A, S, D, status, map, scout, leave)");
			String move = in.nextLine();
			if(validMove(move, player1, board.room))
			{
				//System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
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
					System.out.println("HP: " + player1.getHP());
					System.out.println("Frag Count: " + player1.getFrag() + " / " + goal);
					System.out.println("Scouts: " + player1.getScout());
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
		System.out.println("Results: ");
		if(player1.getFrag()<goal) {
			if (player1.getHP() < 1) {
				System.out.println("You did not manage to finish your mission, and the forest remains infested for generations. Hundreds of other adventurers follows your fate...");
			} else {
				System.out.println("You did not manage to finish your mission, but at least you made it out alive. Perhaps someone else will finish what you started...");
			}
		} else {
			if (player1.getHP() < 1) {
				System.out.println("You fall in battle, but you managed to clear the forest of the pests. The forest remains peaceful for decades to come...");
			} else {
				System.out.println("You clear out the forest with no problem and head home for your long awaited reward, knowing that the forest remains peaceful for decades to come...");
			}
		}
	}

	public static void teleport(int x, int y){
		board.room[x][y].leaveRoom(player1);
		board.room[(int)(Math.random() * board.room.length)][(int)(Math.random() * board.room[0].length)].enterRoom(player1);
		updateMap();
	}

	public static void scout(){
	    if(player1.getScout()>0) {

            int x = player1.getxLoc();
            int y = player1.getyLoc();

            //shows the 8 areas around the player on the map
            try {
                board.room[x - 1][y - 1].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x - 1][y].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x - 1][y + 1].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x][y - 1].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x][y + 1].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x + 1][y - 1].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x + 1][y].show();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                board.room[x + 1][y + 1].show();
            } catch (IndexOutOfBoundsException e) {
            }

            //updates map then displays map;
            updateMap();
            getMap();
            player1.setScout(player1.getScout()-1);
            System.out.println("You scout the area...");
        } else {
            System.out.println("You are out of scouts!");
        }
    }

    public static void updateMap(){
	    fowMap = "";

        for(int i=0;i<board.room.length;i++){
            for(int n=0;n<board.room[i].length;n++){
            	fowMap += "[" + board.room[i][n] + "]";
            }
            fowMap+="\n";
        }

        fowMap += "Key: O = You, N = Nothing, T = Teleport";
    }
	public static void getMap(){
		System.out.println(fowMap);
	}

	public static void normalize(int x, int y, Person p){
	    board.room[x][y] = new Room(x,y,p);
    }
}

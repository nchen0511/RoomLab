//Nick Chen
package Rooms;

public class Board {
    public Room[][] room;
    public Board (Room[][] room){
        this.room = room;
    }
    public Board (int x, int y){
        this.room = new Room[x][y];
    }
}

import java.util.ArrayList;

/*
* The player is the hero of the story and controllable by the user. 
*/
public class Player{
    // Player State Variables
    private String name;
    private int hitPoints; // Current HP
    private int startingHitPoints; // Max HP, cannot heal beyond this
    private int sightRange = 4;

    // Inventory
    private ArrayList<Item> inventory;
    private Tile currentTile;

    // Art
    private String playerImage = "Player.png";

    // Player with a random name. 
    public Player(){
        name = GameData.getRandomName();
        hitPoints = 10;
        startingHitPoints = 10;
        inventory = new ArrayList<Item>();
    }

    // Player with specified parameters
    public Player(String newName, int newHitPoints){
        name = newName;
        hitPoints = newHitPoints;
        inventory = new ArrayList<Item>();
    }

    // Return the file name of the Image that represents the player. 
    public String getImageName(){
        return playerImage;
    }

    // Assign the tile the player is on
    public void setTile(Tile currentTile){
        this.currentTile = currentTile;
        currentTile.setPlayer(this);
    }

    // Needed to resolve items
    public Tile getCurrentTile(){
        return currentTile;
    }

    public void playerDied(){
        currentTile.setPlayer(null);
    }

    // Is the next tile a wall?
    // True if it is not, false if it is 
    public boolean isMoveValid(int direction){
        Tile[] neighbors = currentTile.getNeighbors();
        if(neighbors[direction].isWall()){ // Can the player move to that tile?
            return false;
        }else{
            return true;
        }
    }

    // Move the player to the next tile in the given direction
    // 0 - North
    // 1 - East
    // 2 - South
    // 3 - West
    public void moveTo(int direction){
        System.out.println("Moving " + direction);
        // get the next tile
        Tile[] neighbors = currentTile.getNeighbors();

        // update the tiles
        Tile newTile = neighbors[direction];
        moveToTile(newTile);
    } 

    // Move the player to a given tile
    private void moveToTile(Tile newTile){
        // Move the player from the current tile to the next tile
        newTile.setPlayer(this);// assign the player to it
        currentTile.setPlayer(null); // update the currentTile state
        // assign the player reference to the current tile
        currentTile = newTile;
    }

    // A4-Q4 - remove fog from the nearby tiles. 
    public void setVision(){
        // for this tile
        currentTile.setVisible(true);
        currentTile.setNeighborsVisibleRecusive(sightRange);
    }

    // --- Simple Accessors ---
    // Basic toString. Just the name
    public String toString(){
        return name;
    }

    // ----- Health -----
    public int getHitPoints(){
        return hitPoints;
    }

    // take damage
    public void takeDamage(int damage){
        hitPoints -= damage;
    }

    // Small accessor method that lets the player heal
    public void healDamage(int healing){
        hitPoints += healing;
        if(hitPoints > startingHitPoints){
            hitPoints = startingHitPoints;
        }
    }

    // ----- Inventory -----
    // Collect an item
    public void addItem(Item newItem){
        inventory.add(newItem);
    }

    // Collect an item
    public void addItems(ArrayList<Item> newItemList){
        for(int i=0; i<newItemList.size(); i++) {
            inventory.add(newItemList.get(i));
        }
    }

    // current player Item count
    public int getItemCount(){
        return inventory.size();
    }

    // Useful for debugging
    public String getInventory(){
        return inventory.toString();
    }
    // Printing out hte inventory in a format that is useful for saving to files. 
    public String formattedInventoryToString(){
        return inventory.toString();
    }

    // Print out a status method
    // Includes name, hitpoints and progress (locations reached and entered)
    public String playerStatus(){
        String st = name;
        st += " has " + hitPoints + " Hit Points.";
        st += "\nThey also collected the following loot: " + getInventory();
        return st;
    }

}
import java.awt.image.BufferedImage; // BufferedImage

// the Tile class represents a single Tile on the GameBoard. 
// This is where the other objects in the game are stored. 
// c Dylan Fries 2019
public class Tile{

    // - Tile description - 
    private String description = "Nothing interesting here";

    // A4
    // Is this tile currently visible
    protected boolean isVisible = true; 
    
    // is it passable or not
    protected boolean isWall = false;
    // Objects on the tile
    protected Creature creature = null;
    protected Item item;
    protected Player player;

    // the neighbors of this tile
    private Tile[] neighbors;

    // - Tile Art - 
    private String baseTileArt = "Floor.png";
    private String wallTileArt = "Wall.png";
    private String fogTileArt = "Fog.png";

    // Basic Tile
    public Tile(){
        // basic tile constructor
        isWall = false;
    }
    // Accepts a description also. 
    public Tile(String description){
        this.description = description;
    }
    
    // ***  A4 Section -  Used for visibility ***
    public boolean isVisible(){
        return isVisible;
    }
    // Assign a visibility value to this tile
    public void setVisible(boolean canSee){
        isVisible = canSee;
    }

    // base case version of the method simply sets the neighboring tiles to be visable. 
    public void setNeighborsVisible(boolean canSee){
        // Make the neighbors visible
        for(int i = 0; i < neighbors.length; i++){
            neighbors[i].setVisible(true);
        }
    }

    // recursively sets the neighbors to be visible for a given range
    // You should check each tiles neighbors recursively, then calling
    // this method on them. If the tile is a wall, or already visable
    // stop, otherwise increment the rangeLeft method and continue. 
    public void setNeighborsVisibleRecusive(int rangeLeft){
        if(rangeLeft != 0 && !isWall) {
            rangeLeft--;
            setNeighborsVisible(true);
            for(int i=0; i<neighbors.length; i++) {
                neighbors[i].setNeighborsVisibleRecusive(rangeLeft);
            }
        }
    }
    
    // ***** Superclass methods that get overwritten *****
    // Override this with child classes. 
    public void activateTile(){ 
        // Does nothing. Used for subclasses. 
    }

    // ***** Neighbors ***** 
    public void setNeighbors(Tile[] newNeighbors){
        neighbors = newNeighbors;
    }

    public Tile[] getNeighbors(){
        return neighbors;
    }

    // ***** Mutator methods *****
    public void setItem(Item newItem){
        item = newItem;
    }
    public void setPlayer(Player newPlayer){
        player = newPlayer;
    }
    public void setCreature(Creature newCreature){
        creature = newCreature;
    }
    // Set the state of the tile to be Wall
    public void setWall(boolean isWall){
        this.isWall = isWall;
    }

    // Description of the Tile
    public void setDescription(String description){
        this.description = description;
    }

    // -----  Accessor methods -----
    // Override this method in child classes
    public String toString(){
        return description;
    }

    // Returns the Item
    public Item getItem(){
        return item;
    }
    // Returns the player
    public Player getPlayer(){
        return player;
    }
    // Returns the Creature
    public Creature getCreature(){
        return creature;
    }
    // ----- Image access for art -----
    // Return the correct image from the tile based on the tile state
    public String getImageName(){
        if(isVisible){
            if(isWall){
                return wallTileArt;
            }else{
                return baseTileArt;
            }
        }else{
            return fogTileArt;
        }
    }

    // ----- Does the Tile contain things --
    // Does the Tile contain an item? 
    public boolean hasItem(){
        return (item != null);
    }
    // Does the Tile contain a Player? 
    public boolean hasPlayer(){
        return (player != null);
    }
    // Does the Tile contain a Creature? 
    public boolean hasCreature(){
        return (creature != null);
    }
    
    // returns true if this Tile is a wall or false if it is not
    // walls are not passible by Creatures or Players 
    public boolean isWall(){
        return isWall;
    }
}
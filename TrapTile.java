/*
*   Subclass of Tile that damages the player if they step on it. 
*/
public class TrapTile extends Tile{
    // Override this method in child classes
    private int damage = 2;
    private String trapArtName = "Trap.png";

    public String toString(){
        if(player != null){
            return "Oh No! Its a Trap!" + player.toString() + " Takes " + damage + " Damage!";
        }else{
            return "Oh No! Its a Trap!";
        }
        
    }

    // Override this with child classes. 
    public void activateTile(){
        player.takeDamage(damage);
    }
    
    // ToString method to return the image name
    public String getImageName() {
        return trapArtName;
    }
}
// HealingTile is an overloaded Tile class that heals the player
// When they step on it. 
public class HealingTile extends Tile{
    private int healing = 4;
    private boolean isAvailable = true;

    // A4
    // Names of the files for healing
    private String healingImageName = "Healing.png";
    private String healingImageDisabled = "DisabledHealing.png";
    
    // Override this method in child classes
    public String toString(){

        if( player == null)
            return "A Healing Tile";
        
        if( isAvailable ){
            return player.toString() + " has found a Healing Tile! They heal up to " + healing + " hit points!";
        }else{
            return player.toString() + " has found a Healing Tile but it has already been used up";
        }
    }

    // Override this with child classes. 
    public void activateTile(){
        if( player == null)
            return;

        if( isAvailable ){
            player.healDamage(healing);
            isAvailable = false;
        }else{
            // Do nothing
        }
    }
    
    // toString method that returns the corresponding image name 
    public String getImageName(){
        if(isAvailable){
                return healingImageName;
        } else{
            return healingImageDisabled;
          }
    }
    
}
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final String name;
    private int position;
    private int money = 1500;
    private int numUtilities = 0;
    private int numRailroads = 0;
    private ArrayList<Property> properties = new ArrayList<Property>();

    public Player(String name){
        this.name = name;
        position = 0;
    }

    public int getPosition() { return position; }

    public String getName() { return name; }

    public int getMoney() { return money; }

    public void addMoney(int addMoney){
        this.money += addMoney;
    }

    public void pay(Player receiving, int amount){
        receiving.addMoney(amount);
        addMoney(-amount);
    }

    //add property to Player's properties
    public void buy(Property property){
        addMoney(-property.getPrice());
        properties.add(property);
        sortPropertiesByGroup(properties);
    }

    private void sortPropertiesByGroup(ArrayList<Property> properties){
        ArrayList<Utility> utilities = new ArrayList<>();
        ArrayList<Railroad> railroads = new ArrayList<>();

        for(Property property : properties){
            if(property instanceof Utility){
                utilities.add((Utility) property);
                properties.remove(property);
            } else if(property instanceof Railroad){
                railroads.add((Railroad) property);
                properties.remove(property);
            }
        }
        Collections.sort(utilities);
        Collections.sort(railroads);
        Collections.sort(properties);

        properties.addAll(railroads);
        properties.addAll(utilities);
    }

    //move Player
    public void move(int numSquares){
        position += numSquares;

        //if pass GO
        if(position >= 40){
            System.out.println(name + " passed GO and collected $200");
            money += 200;
            position %= 40;
        }
    }

    //list all properties that Player owns
    public void listProperties(){
        for(Property property : properties){
            System.out.println(property.getName());
        }
    }

    //check if property is in Player's properties
    private boolean owns(Property property){
        return properties.contains(property);
    }

    public boolean ownsGroup(ColorProperty.Group group, Board board){
        Square currentSquare;

        //check each square on the board that is in group, and checks if player owns
        for(int i = 0; i < 41; i++){
            currentSquare = board.getSquareAt(i);
            if(currentSquare instanceof ColorProperty && ((ColorProperty) currentSquare).getGroup() == group && !owns((Property) currentSquare)){
                return false;
            }
        }

        return true;
    }
}

/**
 * Write a description of class Pizza here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;

public class Pizza {
    private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
    private int sold;
    
    public Pizza(){
        this.sold = 0;
    }
}

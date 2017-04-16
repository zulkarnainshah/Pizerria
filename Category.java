/**
 * Write a description of class Category here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Category {
    private String name;
    private int min;
    private int max;

    public Category(String name,int min,int max){
        this.name = name;
        this.min = min;
        this.max = max;
    }
    
    public String toString(){
        return this.name;
    }
}
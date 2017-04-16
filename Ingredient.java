/**
 * Write a description of class Ingredient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ingredient {
    private String name;
    private double price;
    private Category category;
    private int sold;
    
    public Ingredient(String name,double price,Category category){
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public Category getCategory(){
        return this.category;
    }
    public int getSold(){
        return this.sold;
    }
    public String toString(){
        //“Tomato sauce” where “Tomato” is the ingredient name and “sauce” is the category
        return this.name + " " + this.category.toString();
    }
    
}

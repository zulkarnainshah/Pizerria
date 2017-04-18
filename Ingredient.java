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
    public void setSold(int sold){
        this.sold = sold;
    }
    
    /**Gets plural names to be used in main menu report option according to quantity**/
    public String getStringForReport(){
       if(this.sold == 0 || this.sold > 1){
           return ""+this.sold+" "+this.toString()+"s sold worth $"+String.format("%.2f",(this.sold*this.price));
        }
        else{
            return ""+this.sold+" "+this.toString()+" sold worth $"+String.format("%.2f",(this.sold*this.price));
        }
    }
    
    /**Returns (Price*Sold)**/
    public double getTotalPrice(){
        return this.price*this.sold;
    }
    
}

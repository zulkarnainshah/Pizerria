/**
 * Write a description of class Pizza here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;

public class Pizza {
    private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
    private int sold;

    public Pizza(){
        this.sold = 0;
    }

    public LinkedList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public String toString(){
        //CRUST pizza with TOPPINGS and SAUCE: $PRICE
        Ingredient crustIngredient = this.getCrustIngredient();
        Ingredient sauceIngredient = this.getSauceIngredient();
        ArrayList<Ingredient> toppingIngredientList = this.getToppingIngredientList();

        String crustMessage = crustIngredient != null ? crustIngredient.getName() + " crust pizza" : "no crust";
        String sauceMessage = sauceIngredient != null ? "and " + sauceIngredient.getName() + " sauce" : "and no sauce";
        String toppingMessage = toppingIngredientList.size() > 0 ? "with "+ this.getToppingIngredientListAsString() : "with no toppings";

        return crustMessage +" "+ toppingMessage+ " "+ sauceMessage+": $"+String.format("%.2f",this.calculatePizzaTotal());
    }

    public Ingredient getCrustIngredient(){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("crust")){
                return ingredient;
            }

        }
        return null;

    }

    public Ingredient getSauceIngredient(){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("sauce")){
                return ingredient;
            }

        }
        return null;

    }

    public ArrayList<Ingredient> getToppingIngredientList(){ // becasue  a pizza can have more than 1 toppings (> = 2 < = 3)
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        ArrayList<Ingredient> toppingIngredients = new ArrayList<>();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("topping")){
                toppingIngredients.add(ingredient);
            }

        }
        return toppingIngredients;

    }

    public String getToppingIngredientListAsString(){
        String stringFormattedList = "";
        Iterator<Ingredient> iterator = this.getToppingIngredientList().iterator();
        while(iterator.hasNext()){
            stringFormattedList += iterator.next().getName() +", ";
        }
        if(stringFormattedList.endsWith(", ")){
            stringFormattedList = stringFormattedList.substring(0, stringFormattedList.length() - 2);
        }
        return stringFormattedList;
    }

    public int getCrustIngredientCount(){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        int count = 0;

        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("crust")){
                count++;
            }

        }
        return count;
    }

    public int getSauceIngredientCount(){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        int count = 0;
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("sauce")){
                count++;
            }

        }
        return count;
    }

    public int getToppingIngredientCount(){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        int count = 0;
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            if(ingredient.getCategory().toString().equalsIgnoreCase("topping")){
                count++;
            }

        }
        return count;
    }

    public double calculatePizzaTotal(){
        double total = 0.0;
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            total += iterator.next().getPrice(); 
        }
        return total;
    }

    /**Returns a non-zero length String if Pizza is NOT valid else zero length String**/
    public String validatePizza(){
        // every pizza must have exactly 1 choice of crust and 1 choice of sauce, 
        //and must also have at least 2 -- but no more than 3 -- toppings
        String validationMessage = "";
        int tokenCount = 0;
        if(this.getCrustIngredientCount() < 1){
            validationMessage += "Must have at least 1 crust";
            tokenCount++;
        }
        if(this.getSauceIngredientCount() < 1){
            if(tokenCount == 0){
                validationMessage += "Must have at least 1 sauce";
            }
            else{
                validationMessage += "\nMust have at least 1 sauce";
            }

            tokenCount++;
        }
        if(this.getToppingIngredientCount() < 2){
            if(tokenCount == 0){
                validationMessage += "Must have at least 2 toppings";
            }
            else{
                validationMessage += "\nMust have at least 2 toppings";
            }

        }
        return validationMessage;
    }

    /**Returns true if the Ingredient passed is added to ingredients list , else return false **/
    public boolean containsIngredient(Ingredient ingredient){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient m_ingredient = iterator.next();
            if(m_ingredient.getName().toString().equalsIgnoreCase(ingredient.getName())){
                return true;
            }

        }
        return false;
    }

    /**Returns the count of maximum addable of the ingredient passed as argument **/
    public int getAddedCountForIngredientCategory(Category category){
        switch(category.toString()){
            case "crust":
            return this.getCrustIngredientCount();

            case "sauce":
            return this.getSauceIngredientCount();

            case "topping":
            return this.getToppingIngredientCount();

        }
        return 0;
    }

}

/**
 * Write a description of class Kitchen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;
import java.util.Iterator;

public class Kitchen {
    public static final Category CRUST = new Category("crust", 1, 1);
    public static final Category SAUCE = new Category("sauce", 1, 1);
    public static final Category TOPPING = new Category("topping", 2, 3);
    private Category[] categories = {CRUST, SAUCE, TOPPING};
    private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();

    /** Initializes all ingredients and adds to linked list**/        
    public Kitchen(){
        ingredients.add(new Ingredient("Thin",3.00,CRUST));
        ingredients.add(new Ingredient("Thick",3.50,CRUST));
        ingredients.add(new Ingredient("Tomato",1.00,SAUCE));
        ingredients.add(new Ingredient("Barbeque",1.00,SAUCE));
        ingredients.add(new Ingredient("Capsicum",0.50,TOPPING));
        ingredients.add(new Ingredient("Olives",1.50,TOPPING));
        ingredients.add(new Ingredient("Jalapenos",1.00,TOPPING));
        ingredients.add(new Ingredient("Beef",2.75,TOPPING));
        ingredients.add(new Ingredient("Pepperoni",2.50,TOPPING));
    }

    public Ingredient getIngredientWithName(String name){
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            String ingredientName = ingredient.getName();
            if(name.length() != 0){
                if(ingredientName.equalsIgnoreCase(name) || ingredientName.toLowerCase().startsWith(name.toLowerCase())){
                    return ingredient;
                }
            }
        }
        return null;
    }
    public LinkedList<Ingredient> getIngredientsWithName(String name){
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            String ing_name = ingredient.getName();
            if(name.length() != 0){
                if(ing_name.equalsIgnoreCase(name) || ing_name.toLowerCase().startsWith(name.toLowerCase())){
                    ingredients.add(ingredient);
                }
            }
        }
        return ingredients;
    }
    public LinkedList<Ingredient> getIngredientsHavingCategoryName(String c_Name){
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        Iterator<Ingredient> iterator = this.ingredients.iterator();
        while(iterator.hasNext()){
            Ingredient ingredient = iterator.next();
            String categoryName = ingredient.getCategory().toString();
            if(c_Name.length() != 0){
                if(categoryName.equalsIgnoreCase(c_Name) || categoryName.toLowerCase().startsWith(c_Name.toLowerCase())){
                    ingredients.add(ingredient);
                }
            }
        }
        return ingredients;
    }

    public LinkedList<Ingredient>getIngredients(){
        return this.ingredients;
    }
}
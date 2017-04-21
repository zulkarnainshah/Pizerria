/**
 * Write a description of class Pizzeria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Pizzeria {
    private LinkedList<Customer> customers = new LinkedList<Customer>(); // new customer is added to this list
    private Kitchen kitchen;

    public Pizzeria(){
        this.kitchen = new Kitchen();
    }

    public LinkedList<Customer> getCustomers(){
        return this.customers;
    }

    public Kitchen getKitchen(){
        return this.kitchen;
    }

    public boolean customerWithPhoneExists(String phone){
        Iterator<Customer> iterator = this.customers.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerWithPhoneNumber(String phone){
        Iterator<Customer> iterator = this.customers.iterator();
        while(iterator.hasNext()){
            Customer customer = iterator.next();
            if(customer.getPhone().equals(phone)){
                return customer;
            }
        }
        return null;
    }

    public static void main(String g[]){
        Pizzeria pizzeria = new Pizzeria(); // Kitchen initialized, All Ingredients intialized

        char[] allowedChoices = {'a','v','s','r','x'};
        Scanner scanner = In.getScanner();
        scanner.useDelimiter("[|\n]");

        char pizzeriaChoice = 0;
        System.out.println("Welcome to Luigi's Pizzeria!");

        while(pizzeriaChoice != 'x'){
            System.out.print("Pizzeria choice (a/v/s/r/x):");
            try{
                pizzeriaChoice = scanner.next().charAt(0); 
            }
            catch(Exception ex){
            }

            switch(pizzeriaChoice){
                //ADD CUSTOMER
                case 'a':
                System.out.print("Phone:");
                String phone = scanner.next();
                if(pizzeria.customerWithPhoneExists(phone) == false){
                    System.out.print("Name:");
                    String name = scanner.next();
                    Customer newCustomer = new Customer(phone,name);
                    pizzeria.customers.add(newCustomer);
                }
                else{
                    System.out.println("An existing customer has that phone number");
                }
                break;

                //VIEW CUSTOMERS
                case 'v':
                Iterator<Customer> iterator = pizzeria.customers.iterator();
                while(iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                break;

                //SERVE CUSTOMER
                case 's':
                System.out.print("Phone:");
                String phoneNumber = scanner.next();
                Customer customer = pizzeria.getCustomerWithPhoneNumber(phoneNumber);
                if(customer != null){
                    System.out.println("Serving "+customer.getName());
                    char customerChoice = 0;
                    Pizza pizza = null;
                    while(customerChoice != 'c' || customerChoice != 'p' || customerChoice != 'o'){

                        System.out.print("Customer choice (c/p/o):");
                        try{
                            customerChoice = scanner.next().charAt(0);
                        }
                        catch(StringIndexOutOfBoundsException e){}
                        switch(customerChoice){
                            //CREATE NEW PIZZA
                            case 'c':
                            //TODO: Create pizza object
                            pizza = new Pizza();
                            System.out.println("Creating new pizza");
                            String name = "";
                            boolean isPizzaValid = false;
                            while(isPizzaValid == false){
                                System.out.print("Ingredient(s):");
                                name = scanner.next();
                                if(name.equals(".")){
                                    //Pizza validation and set isPizzaValid accordingly
                                    String pizzaValidationMessage = pizza.validatePizza(); 
                                    if(pizzaValidationMessage.length() > 0){
                                        System.out.println(pizzaValidationMessage);
                                    }
                                    else{
                                        System.out.println("ORDER SUMMARY");
                                        System.out.println(pizza);
                                        System.out.println("Total: $"+String.format("%.2f",pizza.calculatePizzaTotal()));
                                        customer.getOrder().add(pizza);
                                        customer.getOrdered().add(pizza);
                                        isPizzaValid = true;

                                    }
                                }
                                //PROCESS MORE THAN ONE INGREDIENTS SUPPLIED AS SINGLE COMMA SEPARATED STRING
                                else if(name.contains(",")){
                                    String[] tokens = name.split(",");
                                    if(tokens.length>0){
                                        for(String ingrName:tokens){
                                            Ingredient ingredient = pizzeria.kitchen.getIngredientWithName(ingrName);
                                            if(ingredient != null){
                                                //TODO: update ingredient sold value
                                                //ingredient.setSold(ingredient.getSold()+1);
                                                pizza.getIngredients().add(ingredient);
                                            }
                                        }
                                        System.out.println(pizza); 
                                    }else{
                                        //Handle only comma supplied by user 
                                    }
                                }
                                //PROCESS SINGLE INGREDIENT SUPPLIED AS SINGLE STRING
                                else{
                                    Ingredient ingredient = pizzeria.kitchen.getIngredientWithName(name);
                                    if(ingredient != null){
                                        //TODO: Add ingredient to pizza after validation
                                        // The same ingredient cannot be added twice
                                        //And you cannot add more than the maximum ingredients for a category
                                        if(pizza.containsIngredient(ingredient) == true){
                                            System.out.println("Already added "+ingredient);
                                        }
                                        //TODO: Check max of category of ingredient
                                        else if(pizza.getAddedCountForIngredientCategory(ingredient.getCategory()) >= ingredient.getCategory().getMax()){
                                            System.out.println("Can only add "+ingredient.getCategory().getMax()+" "+ingredient.getCategory().getPluralName());
                                        }
                                        else{
                                            //TODO: update ingredient sold value
                                            //ingredient.setSold(ingredient.getSold()+1);
                                            pizza.getIngredients().add(ingredient);
                                            System.out.println(pizza);
                                        }

                                    }
                                    else{
                                        if(!name.equals(".") && !name.startsWith("-")){
                                            System.out.println("No ingredient matching "+name);
                                        }
                                        if(name.startsWith("-")){
                                            //TODO: get the minus out from the name and search again for that ingredient
                                            String ingredientName = name.substring(1,name.length());
                                            Ingredient m_ingredient = pizzeria.kitchen.getIngredientWithName(ingredientName);
                                            if(m_ingredient != null){
                                                //Remove from pizza
                                                pizza.getIngredients().remove(m_ingredient);
                                                System.out.println(pizza);
                                            }
                                            else{
                                                System.out.println("No ingredient matching "+ingredientName);
                                            }

                                        }
                                    }
                                }

                            }

                            break;

                            //SELECT FROM POPULAR PAST PIZZAS
                            case 'p':
                            break;

                            //SUBMIT ORDER
                            case 'o':
                            //Process order here and move to main menu
                            if(customer.getOrderCount() == 0 || pizza == null){
                                System.out.println("Empty order discarded");
                                pizzeriaChoice = '?';
                            }
                            else{
                                System.out.println("Order submitted");
                                //add pizza to 'Ordered' list of Customer
                                //customer.getOrdered().add(pizza);
                                pizzeriaChoice = '?';
                            }
                            break;

                            default:
                            System.out.println("c = create new pizza\np = select from popular past pizzas\no = submit order");
                            break;
                        }

                        if(pizzeriaChoice == '?'){
                            break;
                        }
                    }

                }
                else{
                    System.out.println("No such customer");
                }
                break;

                case 'r':
                pizzeria.printReport();
                break;

                //EXIT
                case 'x':
                System.exit(0);
                break;

                default:
                System.out.println("a = add customer\nv = view customers\ns = serve customer\nr = show report\nx = exit");
                break;
            }
        }

    }

    /**Gets the 'sold' quantity value for each ingredient from all customers**/
    public void printReport(){
        int thinCrust = 0;
        int thickCrust = 0;
        int tomatoSauce = 0;
        int barbequeSauce = 0;
        int capsicumTopping = 0;
        int olivesTopping = 0;
        int jalapenosTopping = 0;
        int beefTopping = 0;
        int pepperoniTopping = 0;
        double totalIncome = 0.0;
        String message = "";

        Iterator<Customer> customerIterator = this.customers.iterator();
        while(customerIterator.hasNext()){
            Customer customer = customerIterator.next();
            Iterator<Pizza> orderIterator = customer.getOrdered().iterator();
            while(orderIterator.hasNext()){
                Pizza pizza = orderIterator.next();
                Iterator<Ingredient> ingredientIterator = pizza.getIngredients().iterator();
                while(ingredientIterator.hasNext()){
                    Ingredient ingredient = ingredientIterator.next();
                    if(ingredient.toString().contains("Thin")){
                        thinCrust++;
                    }
                    else if(ingredient.toString().contains("Thick")){
                        thickCrust++;
                    }
                    else if(ingredient.toString().contains("Tomato")){
                        tomatoSauce++;
                    }
                    else if(ingredient.toString().contains("Barbeque")){
                        barbequeSauce++;
                    }
                    else if(ingredient.toString().contains("Capsicum")){
                        capsicumTopping++;
                    }
                    else if(ingredient.toString().contains("Olives")){
                        olivesTopping++;
                    }
                    else if(ingredient.toString().contains("Jalapenos")){
                        jalapenosTopping++;
                    }
                    else if(ingredient.toString().contains("Beef")){
                        beefTopping++;
                    }
                    else if(ingredient.toString().contains("Pepperoni")){
                        pepperoniTopping++;
                    }
                }
            }
        }

        //Use Kitchen ingredients list as buffer to get string messages for report
        Iterator<Ingredient> ingredientIterator = this.kitchen.getIngredients().iterator();
        while(ingredientIterator.hasNext()){
            Ingredient ingredient = ingredientIterator.next();
            if(ingredient.toString().contains("Thin")){
                ingredient.setSold(thinCrust);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Thick")){
                ingredient.setSold(thickCrust);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Tomato")){
                ingredient.setSold(tomatoSauce);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Barbeque")){
                ingredient.setSold(barbequeSauce);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Capsicum")){
                ingredient.setSold(capsicumTopping);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Olives")){
                ingredient.setSold(olivesTopping);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Jalapenos")){
                ingredient.setSold(jalapenosTopping);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Beef")){
                ingredient.setSold(beefTopping);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
            else if(ingredient.toString().contains("Pepperoni")){
                ingredient.setSold(pepperoniTopping);
                message += ingredient.getStringForReport()+"\n";
                totalIncome += ingredient.getTotalPrice();
            }
        }

        message += "Income: $"+String.format("%.2f",totalIncome);
        System.out.println(message);

    }

}

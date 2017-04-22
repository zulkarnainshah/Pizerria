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
                if(phone.length()>0){
                    if(pizzeria.customerWithPhoneExists(phone) == false){
                        System.out.print("Name:");
                        String name = scanner.next();
                        if(name.length()>0){
                            Customer newCustomer = new Customer(phone,name);
                            pizzeria.customers.add(newCustomer);
                        }
                    }
                    else{
                        System.out.println("An existing customer has that phone number");
                    }
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
                                        pizza.setSold(pizza.getSold()+1);
                                        customer.getOrder().add(pizza);
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
                                                if(pizza.containsIngredient(ingredient) == true){
                                                }
                                                //Check max of category of ingredient
                                                else if(pizza.getAddedCountForIngredientCategory(ingredient.getCategory()) >= ingredient.getCategory().getMax()){
                                                }
                                                else{
                                                    ingredient.setSold(ingredient.getSold()+1);
                                                    //ingredient.setSold(1);
                                                    pizza.getIngredients().add(ingredient);
                                                }
                                            }
                                        }
                                        System.out.println(pizza); 
                                    }else{
                                        //Handle only comma supplied by user 
                                        System.out.println("No ingredient matching "+name);
                                    }
                                }
                                //PROCESS SINGLE INGREDIENT SUPPLIED AS SINGLE STRING
                                else{
                                    Ingredient ingredient = pizzeria.kitchen.getIngredientWithName(name);
                                    if(ingredient != null){
                                        //Add ingredient to pizza after these validations
                                        //1. The same ingredient cannot be added twice
                                        //2. And you cannot add more than the maximum ingredients for a category
                                        if(pizza.containsIngredient(ingredient) == true){
                                            System.out.println("Already added "+ingredient);
                                        }
                                        //Check max of category of ingredient
                                        else if(pizza.getAddedCountForIngredientCategory(ingredient.getCategory()) >= ingredient.getCategory().getMax()){
                                            System.out.println("Can only add "+ingredient.getCategory().getMax()+" "+ingredient.getCategory().getPluralName());
                                        }
                                        else{
                                            ingredient.setSold(ingredient.getSold()+1);
                                            //ingredient.setSold(1);
                                            pizza.getIngredients().add(ingredient);
                                            System.out.println(pizza);
                                        }

                                    }
                                    else{
                                        if(!name.equals(".") && !name.startsWith("-")){
                                            System.out.println("No ingredient matching "+name);
                                        }
                                        if(name.startsWith("-")){
                                            //Get the minus out from the name and search again for that ingredient
                                            String ingredientName = name.substring(1,name.length());
                                            Ingredient m_ingredient = pizzeria.kitchen.getIngredientWithName(ingredientName);
                                            if(m_ingredient != null){
                                                //Remove from pizza
                                                m_ingredient.setSold(m_ingredient.getSold()-1);
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
                            LinkedList<Pizza> pastOrders = customer.getOrdered();
                            pastOrders.sort(new java.util.Comparator<Pizza>(){
                                    public int compare(Pizza pizza1,Pizza pizza2){
                                        if(pizza2.getSold() > pizza1.getSold())
                                            return 1;
                                        else if(pizza2.getSold() < pizza1.getSold())
                                            return -1;
                                        else return 0;
                                    }
                                });
                            if(pastOrders.size()>0){
                                System.out.println("Select from popular past pizzas:");
                                int index = 1;
                                for(int i = 0;i<customer.getOrdered().size();i++){
                                    Pizza m_pizza = pastOrders.get(i);
                                    System.out.println(index+". ("+m_pizza.getSold()+"x) "+m_pizza);
                                    index++;
                                    //Show only top three
                                    if(i==2){
                                        break;
                                    }
                                }
                                System.out.print("Selection:");
                                char selection = 0;
                                try{
                                    selection = scanner.next().charAt(0);
                                    switch(selection){
                                        case '1':
                                        //Get pizza at index 0 of pastOrders list
                                        try{
                                            Pizza pizzaToAdd = pastOrders.get(0);
                                            //increase SOLD of every ingredient of the pizza by 1
                                            for(Ingredient ingredient:pizzaToAdd.getIngredients()){
                                                ingredient.setSold(ingredient.getSold()+1);
                                            }
                                            System.out.println("ORDER SUMMARY");
                                            System.out.println(pizzaToAdd);
                                            System.out.println("Total: $"+String.format("%.2f",pizzaToAdd.calculatePizzaTotal()));
                                            pizzaToAdd.setSold(pizzaToAdd.getSold()+1);
                                            //Replace the pizza from Ordered with this one . 
                                            customer.getOrdered().set(0,pizzaToAdd);
                                        }
                                        catch(IndexOutOfBoundsException be){

                                        }

                                        break;
                                        case '2':
                                        //Get pizza at index 1 of pastOrders list
                                        try{
                                            Pizza pizzaToAdd = pastOrders.get(1);
                                            //increase SOLD of every ingredient of the pizza by 1
                                            for(Ingredient ingredient:pizzaToAdd.getIngredients()){
                                                ingredient.setSold(ingredient.getSold()+1);
                                            }
                                            System.out.println("ORDER SUMMARY");
                                            System.out.println(pizzaToAdd);
                                            System.out.println("Total: $"+String.format("%.2f",pizzaToAdd.calculatePizzaTotal()));
                                            pizzaToAdd.setSold(pizzaToAdd.getSold()+1);
                                            customer.getOrdered().set(1,pizzaToAdd);
                                        }
                                        catch(IndexOutOfBoundsException be){

                                        }
                                        break;
                                        case '3':
                                        //Get pizza at index 2 of pastOrders list
                                        try{
                                            Pizza pizzaToAdd = pastOrders.get(2);
                                            //increase SOLD of every ingredient of the pizza by 1
                                            for(Ingredient ingredient:pizzaToAdd.getIngredients()){
                                                ingredient.setSold(ingredient.getSold()+1);
                                            }
                                            System.out.println("ORDER SUMMARY");
                                            System.out.println(pizzaToAdd);
                                            System.out.println("Total: $"+String.format("%.2f",pizzaToAdd.calculatePizzaTotal()));
                                            pizzaToAdd.setSold(pizzaToAdd.getSold()+1);
                                            customer.getOrdered().set(2,pizzaToAdd);
                                        }
                                        catch(IndexOutOfBoundsException be){

                                        }
                                        break;
                                        default:
                                        break;
                                    }
                                }
                                catch(Exception e){}

                            }
                            else{
                                System.out.println("No past orders for this customer");
                            }

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
                                //add pizza to 'Ordered' list of Customer (in decreasing order of SOLD property of Pizza) and remove from 'Order' list
                                customer.getOrdered().addAll(customer.getOrder());
                                customer.getOrder().clear();
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
        
        Iterator<Ingredient>ingredientIterator = this.kitchen.getIngredients().iterator();
        while(ingredientIterator.hasNext()){
           Ingredient ingredient = ingredientIterator.next();
           message += ingredient.getStringForReport()+"\n";
           totalIncome += ingredient.getTotalPrice(); 
        }

        message += "Income: $"+String.format("%.2f",totalIncome);
        System.out.println(message);

    }

}

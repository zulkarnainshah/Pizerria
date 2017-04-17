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
            pizzeriaChoice = scanner.next().charAt(0); 

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
                                        isPizzaValid = true;
                                        
                                    }
                                }
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
                                //also set the sold property of each ingredient in the pizza
                                
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
}

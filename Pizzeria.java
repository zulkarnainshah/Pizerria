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

    public static void main(String g[]){
        Pizzeria pizzeria = new Pizzeria(); // Kitchen initialized, All Ingredients intialized

        char[] allowedChoices = {'a','v','s','r','x'};
        Scanner scanner = In.getScanner();
        scanner.useDelimiter("[|\n]");
        
        char choice = 0;
        System.out.println("Welcome to Luigi's Pizzeria!");

        while(choice != 'x' || choice != 'X'){
            System.out.print("Pizzeria choice (a/v/s/r/x):");
            choice = scanner.next(".").charAt(0); 
            switch(choice){
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
                
                case 'v':
                Iterator<Customer> iterator = pizzeria.customers.iterator();
                while(iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                break;
                
                case 's':
                
                break;
                
                case 'r':
                break;
                
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

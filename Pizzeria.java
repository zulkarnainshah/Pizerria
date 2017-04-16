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
                    while(customerChoice != 'c' || customerChoice != 'p' || customerChoice != 'o'){
                        System.out.print("Customer choice (c/p/o):");
                        customerChoice = scanner.next().charAt(0);
                        switch(customerChoice){
                            //CREATE NEW PIZZA
                            case 'c':
                            System.out.println("Creating new pizza");
                            break;

                            //SELECT FROM POPULAR PAST PIZZAS
                            case 'p':
                            break;

                            //SUBMIT ORDER
                            case 'o':
                            if(customer.getOrderCount() == 0){
                                System.out.println("Empty order discarded");
                                pizzeriaChoice = '?';
                            }
                            else{
                                //Process order here
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

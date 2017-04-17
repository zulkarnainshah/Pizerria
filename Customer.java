/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.LinkedList;

public class Customer {
    private String phone;
    private String name;
    private LinkedList<Pizza> ordered = new LinkedList<Pizza>(); // Successfully placed orders are added here to maintaing history of customer
    private LinkedList<Pizza> order = new LinkedList<Pizza>(); // remove all elements from this when order successfully placed
    
    public Customer(String phone,String name){
        this.phone = phone;
        this.name = name;
    }
    
    public String toString(){
    //“Kelly Lee:95432123” where “Kelly Lee” is the customer’s name and “95432123” is the customer’s phone number.
        return this.name+": "+this.phone;
    }
    
    public String getPhone(){
        return this.phone;
    }
    public String getName(){
        return this.name;
    }
    public int getOrderCount(){
        return this.order.size();
    }
    public LinkedList<Pizza> getOrder(){
        return this.order;
    }
    
    public LinkedList<Pizza> getOrdered(){
        return this.ordered;
    }
}

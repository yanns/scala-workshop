package section9;

import section4.Section4.Customer;

public class Section9 {

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(24, true);
        System.out.println(customer.toString());

        System.out.println("customer age: " + customer.age());
        System.out.println("customer have id: " + customer.haveId());

        // TODO: use java getters
        //System.out.println("customer age: " + customer.getAge());
        //System.out.println("customer have id: " + customer.isHaveId());
    }
}

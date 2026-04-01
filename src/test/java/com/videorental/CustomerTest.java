package com.videorental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    void sample() {
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR),
                2));
        customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR),
                3));
        customer.addRental(new Rental(new Movie("ShortNew", Movie.NEW_RELEASE),
                1));
        customer.addRental(new Rental(new Movie("LongNew", Movie.NEW_RELEASE),
                2));
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS),
                3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS),
                4));
        String receipt = customer.statement();
        System.out.println(receipt);

        Assertions.assertEquals("Rental Record for Bob\n" +
                "\t2.0(Jaws)\n" +
                "\t3.5(GoldenEye)\n" +
                "\t3.0(ShortNew)\n" +
                "\t6.0(LongNew)\n" +
                "\t1.5(Bambi)\n" +
                "\t3.0(Toy Story)\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter pointers", customer.statement());
    }
}

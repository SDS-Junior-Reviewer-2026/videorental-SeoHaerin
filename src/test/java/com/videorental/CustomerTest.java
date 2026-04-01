package com.videorental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    void statementForNoRental() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers", statement);
    }
    @Test
    void statementForRegularMovieRentalForLessThan3Days() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("TITAN", Movie.REGULAR),
                2));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITAN)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers", statement);
    }
    @Test
    void statementForRegularMovieRentalForMoreThan2Days() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("TITAN", Movie.REGULAR),
                3));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITAN)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers", statement);
    }
    @Test
    void statementForNewReleaseMovie() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("MERRY", Movie.NEW_RELEASE),
                1));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(MERRY)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers", statement);
    }
    @Test
    void statementForChildrensMovieRentalMoreThan3Days() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("UP", Movie.CHILDRENS),
                4));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(UP)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers", statement);
    }
    @Test
    void statementForChildrensMovieRentalLessThan4Days() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("DROWNING", Movie.CHILDRENS),
                3));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(DROWNING)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers", statement);
    }
    @Test
    void statementForNewReleaseMovieRentalMoreThan1Day() {
        Customer customer= new Customer("NAME_NOT_IMPORTANT");
        customer.addRental(new Rental(new Movie("KING LIVE MAN", Movie.NEW_RELEASE),
                2));
        String statement= customer.statement();
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(KING LIVE MAN)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers", statement);
    }


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

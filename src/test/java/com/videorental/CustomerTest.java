package com.videorental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    public static final String NAME = "NAME_NOT_IMPORTANT";
    Customer customer= new Customer(NAME);
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    @Test
    void statementForNoRental() {
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers", customer.statement());
    }
    @Test
    void statementForRegularMovieRentalForLessThan3Days() {
        customer.addRental(createRentalFor(Movie.REGULAR, 2));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers", customer.statement());
    }

    private static Rental createRentalFor(int priceCode, int daysRented) {
        Movie movie= new Movie(TITLE, priceCode);
        Rental rental= new Rental(movie, daysRented);
        return rental;
    }

    @Test
    void statementForRegularMovieRentalForMoreThan2Days() {
        customer.addRental(createRentalFor(Movie.REGULAR, 3));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers", customer.statement());
    }
    @Test
    void statementForNewReleaseMovie() {
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers", customer.statement());
    }
    @Test
    void statementForChildrensMovieRentalMoreThan3Days() {
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers", customer.statement());
    }
    @Test
    void statementForChildrensMovieRentalLessThan4Days() {
        customer.addRental(createRentalFor(Movie.CHILDRENS, 3));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers", customer.statement());
    }
    @Test
    void statementForNewReleaseMovieRentalMoreThan1Day() {
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));
        Assertions.assertEquals("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers", customer.statement());
    }
    @Test
    void movieShouldChangePriceCode() {
        Movie movie = new Movie("Jaws", Movie.NEW_RELEASE);
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));
        movie.setPriceCode(Movie.CHILDRENS);
        Assertions.assertEquals(Movie.CHILDRENS, movie.getPriceCode());
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

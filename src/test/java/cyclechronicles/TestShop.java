package cyclechronicles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShop {

    private Shop shop;

    private Order order;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    public void testGravelBike() {
        order = new Order(Type.GRAVEL, "customer1");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testEBike() {
        order = new Order(Type.EBIKE, "customer1");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersLessThanFiveTotal() {
        order = new Order(Type.RACE, "customer1");
        assertTrue(shop.accept(order));
    }

    @Test
    public void testOtherBikePendingOrderForSameCustomer() {
        order = new Order(Type.RACE, "customer1");

        shop.accept(new Order(Type.RACE, "customer1"));

        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersExactlyFiveTotal() {
        order = new Order(Type.RACE, "customer1");

        shop.accept(new Order(Type.RACE, "customer2"));
        shop.accept(new Order(Type.RACE, "customer3"));
        shop.accept(new Order(Type.RACE, "customer4"));
        shop.accept(new Order(Type.RACE, "customer5"));
        shop.accept(new Order(Type.RACE, "customer6"));

        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersExactlyFourTotal() {
        order = new Order(Type.RACE, "customer1");

        shop.accept(new Order(Type.RACE, "customer2"));
        shop.accept(new Order(Type.RACE, "customer3"));
        shop.accept(new Order(Type.RACE, "customer4"));
        shop.accept(new Order(Type.RACE, "customer5"));

        assertTrue(shop.accept(order));
    }
}

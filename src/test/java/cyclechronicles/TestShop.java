package cyclechronicles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cyclechronicles.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TestShop {

    private Shop shop;

    @Mock
    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shop = new Shop();
    }

    @Test
    public void testGravelBike() {
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        assertFalse(shop.accept(order));
    }

    @Test
    public void testEBike() {
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersLessThanFiveTotal() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer1");
        assertTrue(shop.accept(order));
    }

    @Test
    public void testOtherBikePendingOrderForSameCustomer() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer1");

        shop.accept(mockOrder(Type.RACE, "customer1"));

        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersExactlyFiveTotal() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer1");

        shop.accept(mockOrder(Type.RACE, "customer2"));
        shop.accept(mockOrder(Type.RACE, "customer3"));
        shop.accept(mockOrder(Type.RACE, "customer4"));
        shop.accept(mockOrder(Type.RACE, "customer5"));
        shop.accept(mockOrder(Type.RACE, "customer6"));

        assertFalse(shop.accept(order));
    }

    @Test
    public void testOtherBikeNoPendingOrdersExactlyFourTotal() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer1");

        shop.accept(mockOrder(Type.RACE, "customer2"));
        shop.accept(mockOrder(Type.RACE, "customer3"));
        shop.accept(mockOrder(Type.RACE, "customer4"));
        shop.accept(mockOrder(Type.RACE, "customer5"));

        assertTrue(shop.accept(order));
    }

    // Utility method to create mocked orders
    private Order mockOrder(Type type, String customer) {
        Order mockOrder = mock(Order.class);
        when(mockOrder.getBicycleType()).thenReturn(type);
        when(mockOrder.getCustomer()).thenReturn(customer);
        return mockOrder;
    }
}

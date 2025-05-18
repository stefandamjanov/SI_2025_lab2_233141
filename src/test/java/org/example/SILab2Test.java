package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2Test {
    @Test
    public void testEveryStatement() {
        // Тест 1: allItems == null
        RuntimeException e1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", e1.getMessage());

        // Тест 2: item.getName() == null
        Item i2 = new Item(null, 1, 100, 0.0);
        RuntimeException e2 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(i2), "1234567890123456"));
        assertEquals("Invalid item!", e2.getMessage());

        // Тест 3: item.getName() == ""
        Item i3 = new Item("", 1, 100, 0.0);
        RuntimeException e3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(i3), "1234567890123456"));
        assertEquals("Invalid item!", e3.getMessage());

        // Тест 4: price > 300, discount > 0
        Item i4 = new Item("item1", 1, 400, 0.1);
        double result4 = SILab2.checkCart(List.of(i4), "1234567890123456");
        assertEquals(330.0, result4);

        // Тест 5: quantity > 10, no discount
        Item i5 = new Item("item2", 11, 10, 0.0);
        double result5 = SILab2.checkCart(List.of(i5), "1234567890123456");
        assertEquals(80.0, result5);

        // Тест 6: невалиден карактер во cardNumber
        Item i6 = new Item("item3", 1, 100, 0.0);
        RuntimeException e6 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(i6), "1234abcd90123456"));
        assertEquals("Invalid character in card number!", e6.getMessage());
    }
    @Test
    public void testMultipleCondition() {
        // A = F, B = F, C = F
        Item t1 = new Item("item1", 1, 100, 0.0);
        assertEquals(100.0, SILab2.checkCart(List.of(t1), "1234567890123456"));

        // A = F, B = F, C = T
        Item t2 = new Item("item2", 11, 100, 0.0);
        assertEquals(1070.0, SILab2.checkCart(List.of(t2), "1234567890123456"));

        // A = F, B = T, C = F
        Item t3 = new Item("item3", 1, 100, 0.1);
        assertEquals(60.0, SILab2.checkCart(List.of(t3), "1234567890123456"));

        // A = F, B = T, C = T
        Item t4 = new Item("item4", 11, 100, 0.2);
        assertEquals(850.0, SILab2.checkCart(List.of(t4), "1234567890123456"));

        // A = T, B = F, C = F
        Item t5 = new Item("item5", 1, 400, 0.0);
        assertEquals(370.0, SILab2.checkCart(List.of(t5), "1234567890123456"));

        // A = T, B = F, C = T
        Item t6 = new Item("item6", 11, 400, 0.0);
        assertEquals(4370.0, SILab2.checkCart(List.of(t6), "1234567890123456"));

        // A = T, B = T, C = F
        Item t7 = new Item("item7", 1, 400, 0.2);
        assertEquals(290.0, SILab2.checkCart(List.of(t7), "1234567890123456"));

        // A = T, B = T, C = T
        Item t8 = new Item("item8", 11, 400, 0.2);
        assertEquals(3490.0, SILab2.checkCart(List.of(t8), "1234567890123456"));

    }
}

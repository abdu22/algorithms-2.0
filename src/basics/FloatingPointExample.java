package basics;

import java.math.BigDecimal;

public class FloatingPointExample {
    public static void main(String[] args) {

        double num1 = 0.1;
        double num2 = 0.2;
        double sum = num1 + num2;
        double expected = 0.3;
        double delta = 0.00000000001;
        if (Math.abs(sum - expected) < delta) {
            System.out.println("Sum is equal to expected value : " + expected);
        }


        /* // Adding BigDecimal numbers
        BigDecimal num1 = new BigDecimal("0.1");
        BigDecimal num2 = new BigDecimal("0.2");
        BigDecimal sum = num1.add(num2);
        System.out.println("Sum: " + sum);

        // Multiplying BigDecimal numbers
        BigDecimal product = num1.multiply(num2);
        System.out.println("Product: " + product);

        // Rounding BigDecimal numbers
        BigDecimal num3 = new BigDecimal("1.23456789");
        BigDecimal rounded = num3.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Rounded: " + rounded); */
    }
}

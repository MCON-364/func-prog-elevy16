package edu.touro.las.mcon364.func_prog.exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface Practice
 *
 * In this assignment you will:
 *  - Create and return different functional interfaces
 *  - Apply them
 *  - Practice chaining where appropriate
 *
 * IMPORTANT:
 *  - Use lambdas
 *  - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================

    /**
     * 1) Create a Supplier that returns the current year.
     *
     * Hint:
     * You can get the current date using:
     *     LocalDate.now()
     *
     * Then extract the year using:
     *     getYear()
     *
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {

        Supplier<Integer> currentDate = () ->  LocalDate.now().getYear();
        return currentDate;
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {

        Supplier<Integer> randomScore = () ->  ThreadLocalRandom.current().nextInt(1, 100);
        return randomScore;
     }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {

        Predicate<String> isAllUpperCase = s -> s.equals(s.toUpperCase());
        return isAllUpperCase;
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     *
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {

        Predicate<Integer> isPositive = x -> x > 0;
        Predicate<Integer> isDivisibleByFive = x -> x % 5 == 0;
        Predicate<Integer> isPositiveAndDivisibleByFive = isPositive.and(isDivisibleByFive);
        return isPositiveAndDivisibleByFive;
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     *
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {

        Function<Double, Double> celsiusToFahrenheit = c -> c * 9/5 + 32;
        return celsiusToFahrenheit;
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     *
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels() {
        Function<String, Integer> countVowels = (str) -> {
            int count = 0;
            str = str.toLowerCase();
            for (char c : str.toCharArray()) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    count++;
                }
            }
            return count;
        };
        return countVowels;
    }

    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     *
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter() {
        Consumer<String> starPrinter = (str) -> System.out.println("*** " + str + " ***");
        return starPrinter;
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare() {
        Consumer<Integer> printSquare = (num) -> System.out.println(num * num);
        return printSquare;
    }

    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     *  - A Predicate
     *  - A Function
     *  - A Consumer
     *
     * Process the list as follows:
     *  - Keep only strings longer than 3 characters
     *  - Convert them to lowercase
     *  - Print them
     */
    public static void processStrings(List<String> values) {
        Predicate<String> longerThanThreeChars = (str) -> str.length() > 3;
        Function<String, String> toLowerCase =  str -> str.toLowerCase();
        Consumer<String> printString = (str) -> System.out.println(str);

        for (String value : values) {
            if (longerThanThreeChars.test(value)) {             // Predicate: keep if true
                String lowerCase = toLowerCase.apply(value);    // Function: convert to lower case
                printString.accept(lowerCase);                  // Consumer: print
            }
        }


    }

    /**
     * 10) Apply:
     *  - A Supplier
     *  - A Predicate
     *  - A Consumer
     *
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
        Supplier<Integer> randomScore = () -> (int)(Math.random() * 101);
        Predicate<Integer> above70 = (score) -> score > 70;
        Consumer<Integer> printer = (score)  -> System.out.println(score);

        for (int i = 0; i < 5; i++) {
            int score = randomScore.get();  // generate
            if (above70.test(score)) {      // filter
                printer.accept(score);      // print
            }
        }
    }
}

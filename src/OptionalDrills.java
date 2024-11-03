
import utilities.Car;
import utilities.Dish;
import utilities.Insurance;

import java.util.List;
import java.util.Optional;

public class OptionalDrills {

    /**
     * Print out the name of a dish that is vegetarian. If there is no vegetarian dish, do nothing.
     * @param menu - the list of dishes to look through
     */
    public static void printOutExampleVegetarianDish(List<Dish> menu) {
        menu.stream()
                .filter(Dish::isVegetarian) // Filter for vegetarian dishes
                .findFirst() // Get the first vegetarian dish, if any
                .ifPresent(dish -> System.out.println(dish.getName())); // Print the name if present
    }

    /**
     * Return the name of the dish. There is a good chance the utilities.Dish coming in is null.
     * @param dish A dish (maybe null...)
     * @return the name of the dish if it exists
     */
    public static Optional<String> getDishName(Dish dish) {
        return Optional.ofNullable(dish) // Handle potential null Dish
                .map(Dish::getName); // Map to the name if Dish is present
    }

    /**
     * Return the name of the insurance for this car.
     * @param car A car
     * @return The name of the insurance if it exists
     */
    public static Optional<String> getExistingInsuranceName(Car car) {
        return Optional.ofNullable(car)
                .map(Car::getInsurance) // Get the Insurance object, which might be null
                .map(insurance -> insurance != null ? insurance.get().getName() : null); // Map to the name if Insurance is not null
    }


    /**
     * Use the private 'otherService' method below to find the name of the
     * insurance that will be cheapest for me.  Be careful!
     * @param car A car
     * @return the name of the cheapest insurance if it exists
     */
    public static Optional<String> findCheapestInsuranceName(Car car) {
        return Optional.ofNullable(car) // Handle potential null Car
                .flatMap(c -> Optional.ofNullable(otherService(c))) // Get Insurance using otherService, handle null
                .map(Insurance::getName); // Map to the name if Insurance is present
    }

    /**
     * Cool!  I wrote a new version of 'otherService' called
     * 'safeOtherService' that will never return null!  But now
     * the car being passed in is an Optional... what do I do???
     * @param car maybe a car?
     * @return the name of the car's cheapest insurance if it and the car exist
     */
    public static Optional<String> findCheapestInsuranceName(Optional<Car> car) {
        return car.map(OptionalDrills::safeOtherService) // Call safeOtherService
                .map(Insurance::getName); // Map to the name if Insurance is present
    }


    /**
     * Tries to find the cheapest insurance, may be null.
     */
    private static Insurance otherService(Car car) {
        return null;
    }

    /**
     * Tries to find the cheapest insurance, may be null.
     */
    private static Insurance safeOtherService(Car car) {
        return new Insurance("Amazon utilities.Insurance");
    }
}

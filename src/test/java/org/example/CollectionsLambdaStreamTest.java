package org.example;

import org.example.puzzle.utils.ArraySolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionsLambdaStreamTest {

    private List<Dog> dogs;

    @BeforeEach
    void setUp() {
        dogs = Dog.getDogs();
    }

    //computeIfAbsent
    @Test
    void test_computeIfAbsent_should_return_the_existing_mapped_value_if_key_is_associated_with_a_value() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 5);
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 5);
        // It’s important to know that the mapping function is only called if the mapping isn’t present.
        //computeIfAbsent() : First, it checks if the key is present in the map. If the key is present, and a non-null value is related to the key, then it returns that value.
        //As we can see, the key “John” has a non-null mapping present, and it returns the value 5. If our mapping function was used, we’d expect the function to return the length of 4.
    }

    //computeIfAbsent
    @Test
    void test_value_is_computed_when_key_is_not_present_in_the_map(){
        Map<String, Integer> map = new HashMap<>();
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 4);
        assertEquals(map.get("John"), 4);
    }

    //computeIfAbsent
    @Test
    void test_value_is_computed_when_key_is_associated_with_null_in_the_map(){
        Map<String, Integer> map = new HashMap<>();
        map.put("John", null);
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 4);
        assertEquals(map.get("John"), 4);
    }

    @Test
    void test_computeIfAbsent_will_return_current_value_if_key_has_non_null_value_associated_with_it(){
        Map<String, Integer> stringLength = new HashMap<>();
        stringLength.put("John", 5);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 5);
    }

    @Test
    void test_computeIfAbsent_will_return_computed_value_if_key_has_null_value_associated_with_it(){
        Map<String, Integer> stringLength = new HashMap<>();
        stringLength.put("John", null);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 4);
        System.out.println("stringLength = " + stringLength);
        assertEquals(stringLength.get("John"), 4);
    }

    @Test
    void test_computeIfAbsent_will_return_computed_value_if_key_is_not_present_in_the_map(){
        Map<String, Integer> stringLength = new HashMap<>();
        //stringLength.put("John", null);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 4);
        System.out.println("stringLength = " + stringLength);
        assertEquals(stringLength.get("John"), 4);
    }
    @Test
    void test_should_sum_age_of_all_dogs(){
        Integer sumOfAges = dogs.stream()
                .map(Dog::getAge)
                .reduce((total, age) -> total + age).orElse(0);
        System.out.println("sumOfAges = " + sumOfAges);
        assertEquals(sumOfAges, 51);
    }

    @Test
    void test_sum_IntStream() {
        //IntStream.of(1,2,3,4,5).sum();
        assertEquals(IntStream.of(1,2,3,4,5).sum(), 15);
    }

    @Test
    void test_reduce_using_identity_accumulator_as_lambda() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertEquals(result, 21);
    }

    @Test
    void test_reduce_using_identity_accumulator_as_method_reference() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, Integer::sum);
        assertEquals(result, 21);
    }
    @Test
    void test_reduce_to_calculate_sum_of_ages_of_person_objects(){
        List<Person> people = List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35)
        );
        Integer sumOfAges = people.stream()
                .reduce(0, (partialResult, user) -> partialResult + user.getAge(), Integer::sum);
        assertEquals(sumOfAges, 90);
    }

    @Test
    void test_reduce_to_calculate_sum_of_ages_of_person_objects_using_sum(){
        List<Person> people = List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35)
        );
        Integer sumOfAges = people.stream()
                .mapToInt(Person::getAge)
                .sum();
        assertEquals(sumOfAges, 90);
    }

    // Given a list of dog objects (name, breed, age) group them by breed and get the max age for each breed.
    @Test
    void test_get_max_age_per_group_of_breed_of_dogs() {
        dogs.forEach(System.out::println);
        Map<String, Integer> maxAgeByBreed = dogs.stream()
                .collect(groupingBy(Dog::getBreed, collectingAndThen(maxBy(comparing(Dog::getAge)), dog -> dog.map(Dog::getAge).orElse(0))));
        System.out.println("maxAgeByBreed = " + maxAgeByBreed);
    }

    //https://www.baeldung.com/java-stream-reduce
    @Test
    void using_reduce_without_identity_returns_optional() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> optional = numbers.stream()
                .reduce((partialResult, number) -> partialResult + number);
        int sum = numbers.stream()
                .reduce((partialResult, number) -> partialResult + number).orElse(0);
        assertEquals(optional.get(), 21);
        assertEquals(sum, 21);
        int sumOfNumbers = numbers.stream()
                .reduce(Integer::sum).orElse(0);
        assertEquals(sumOfNumbers, 21);
    }

    @Test
    void using_reduce_with_identity_returns_actual_value() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream()
                .reduce(0, (partialResult, number) -> partialResult + number);
        assertEquals(sum, 21);

        int sumOfNumbers = numbers.stream()
                .reduce(0, Integer::sum);
        assertEquals(sumOfNumbers, 21);
    }

    //https://www.baeldung.com/java-stream-reduce
    @Test
    void using_reduce_with_identity_returns_actual_value_for_custom_objects() {
        List<Person> people = List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35)
        );
        Integer sumOfAges = people.stream()
                .reduce(0, (partialResult, person) -> partialResult + person.getAge(), Integer::sum);
        assertEquals(sumOfAges, 90);
    }
    
    // Given a list of Food (name, vegetarian, calories, type), get a map displaying max calories for each type of food
    @Test
    void test_get_max_Calories_per_group_by_food_type() {
        Map<Food.Type, Integer> maxByFoodType = Food.menu.stream()
                .collect(groupingBy(Food::getType, collectingAndThen(maxBy(comparing(Food::getCalories)), food -> food.map(Food::getCalories).orElse(0))));
        System.out.println("maxByFoodType = " + maxByFoodType);
        assertTrue(maxByFoodType.containsKey(Food.Type.MEAT) && maxByFoodType.get(Food.Type.MEAT) == 7100);
        assertTrue(maxByFoodType.containsKey(Food.Type.FISH) && maxByFoodType.get(Food.Type.FISH) == 4150);
        assertTrue(maxByFoodType.containsKey(Food.Type.OTHER) && maxByFoodType.get(Food.Type.OTHER) == 5150);
    }
    //maxByFoodType = {FISH=4150, OTHER=5150, MEAT=7100}

    // Given a list of Food (name, vegetarian, calories, type), get a map displaying food name with max calories for each type of food
    @Test
    void test_get_food_name_with_max_Calories_per_group_of_food_type() {
        Map<Food.Type, String> foodByMaxCaloriesFoodType = Food.menu.stream()
                .collect(groupingBy(Food::getType, collectingAndThen(maxBy(comparing(Food::getCalories)), food -> food.map(Food::getName).orElse(""))));
        System.out.println("foodByMaxCaloriesFoodType = " + foodByMaxCaloriesFoodType);
        //foodByMaxCaloriesFoodType = {FISH=salmon, OTHER=pizza, MEAT=beef}
        assertTrue(foodByMaxCaloriesFoodType.containsKey(Food.Type.MEAT) && foodByMaxCaloriesFoodType.get(Food.Type.MEAT).equals("beef"));
        assertTrue(foodByMaxCaloriesFoodType.containsKey(Food.Type.FISH) && foodByMaxCaloriesFoodType.get(Food.Type.FISH).equals("salmon"));
        assertTrue(foodByMaxCaloriesFoodType.containsKey(Food.Type.OTHER) && foodByMaxCaloriesFoodType.get(Food.Type.OTHER).equals("pizza"));
    }

    // Given a list of Food (name, vegetarian, calories, type), get a map displaying total calories per food type
    @Test
    void test_get_total_calories_per_food_type(){
        Map<Food.Type, Integer> totalCaloriesByFoodType = Food.menu.stream()
                .collect(groupingBy(Food::getType, summingInt(Food::getCalories)));
        System.out.println("totalCaloriesByFoodType = " + totalCaloriesByFoodType);
        assertTrue(totalCaloriesByFoodType.containsKey(Food.Type.MEAT) && totalCaloriesByFoodType.get(Food.Type.MEAT) == 10300);
        assertTrue(totalCaloriesByFoodType.containsKey(Food.Type.FISH) && totalCaloriesByFoodType.get(Food.Type.FISH) == 5550);
        assertTrue(totalCaloriesByFoodType.containsKey(Food.Type.OTHER) && totalCaloriesByFoodType.get(Food.Type.OTHER) == 11310);
    }

    // We use a Comparator returned by the nullsFirst method and pass a natural ordering comparator as the underlying comparator it must use.
    @Test
    void test_nullsFirst_for_list_of_strings() {
        List<String> fruits = Arrays.asList("pear", "apple", "grapes", null, "orange");
        //fruits.sort(Comparator.naturalOrder());
        fruits.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(fruits); //  [null, apple, grapes, orange, pear]
    }

    @Test
    void test_nullsFirst_for_list_of_strings_reverseOrder() {
        List<String> fruits = Arrays.asList("pear", "apple", "grapes", null, "orange");
        //fruits.sort(Comparator.naturalOrder());
        fruits.sort(Comparator.nullsFirst(Comparator.reverseOrder()));
        System.out.println(fruits); //  [null, pear, orange, grapes, apple]
    }

    @Test
    void test_nullsFirst_should_place_the_objects_first_in_sorted_result(){
        List<Person> list = new ArrayList<>(List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person(null, 35),
                new Person("", 40),
                new Person("Bill", 25),
                new Person("Jill", 50)
        ));
        Comparator<Person> comparing = comparing(Person::getName);

        Comparator<Person> personComparator = comparing(Person::getName, Comparator.nullsFirst(String::compareTo));
        list.sort(personComparator);
        list.forEach(System.out::println);
    }

    //Sorting a list using old way of writing anonymous Comparator
    @Test
    void test_sort_list_of_inventories_using_old_way_of_Comparator() {
        List<Inventory> inventories = Inventory.getInventories();
        inventories.sort(new Comparator<Inventory>(){
            @Override
            public int compare(Inventory o1, Inventory o2) {
                return Long.compare(o1.getInventoryId(), o2.getInventoryId());
            }
        });
        inventories.forEach(System.out::println);
    }

    //Sorting a list using Comparator written as lambda expression
    @Test
    void test_sort_list_of_inventories_using_Comparator_as_lambda() {
        List<Inventory> inventories = Inventory.getInventories();
        inventories.sort((inv1, inv2) -> Long.compare(inv1.getInventoryId(), inv2.getInventoryId()));
        inventories.forEach(System.out::println);
    }
    @Test
    void test_sort_list_of_inventories_using_Comparator_dot_comparing() {
        List<Inventory> inventories = Inventory.getInventories();
        inventories.sort(Comparator.comparing(Inventory::getInventoryId));
        inventories.forEach(System.out::println);
    }

    //This is performant as it does not require boxing to Long type in this case.
    @Test
    void test_sort_list_of_inventories_using_Comparator_dot_comparing_using_specialized_long_comparison() {
        List<Inventory> inventories = Inventory.getInventories();
        inventories.sort(Comparator.comparingLong(Inventory::getInventoryId));  // Since getInventory for an Inventory returns a long value.
        inventories.forEach(System.out::println);
    }

    // sorting a list of inventories based on location (location field has type Location)
    @Test
    void test_sort_list_of_inventories_based_on_typed_field() {
        List<Inventory> inventories = Inventory.getInventories();
        //The function we have passed, maps an Inventory to a Location object. But a Location object is not Comparable.
        // So, we need to pass Comparator to compare two Location instances as a second argument to comparing.
        //inventories.sort(Comparator.comparing(Inventory::getLocation));
        inventories.sort(Comparator.comparing(Inventory::getLocation, (loc1, loc2) -> loc1.getState().compareTo(loc2.getState())));
        inventories.forEach(System.out::println);
    }

    // sorting a list of inventories based on location reversed (location field has type Location)
    @Test
    void test_sort_list_of_inventories_based_on_typed_field_version2() {
        List<Inventory> inventories = Inventory.getInventories();
        //The function we have passed, maps an Inventory to a Location object. But a Location object is not Comparable.
        // So, we need to pass Comparator to compare two Location instances as a second argument to comparing.
        //inventories.sort(Comparator.comparing(Inventory::getLocation));
        inventories.sort(Comparator.comparing(Inventory::getLocation, Comparator.comparing(Inventory.Location::getState)).reversed());
        inventories.forEach(System.out::println);
    }

    /*
    We can chain Comparators easily. The Comparators next in the chain will be used to break ties if the first comparator determines the two objects are equal.
    Let us now sort the list by the number of items in an inventory. If two inventories have the same number of items, we sort by the inventory id (the tie breaker).
     */
    @Test
    void test_sort_list_of_inventories_based_on_items_count_but_if_count_matches_then_compare_using_inventoryId() {
        List<Inventory> inventories = Inventory.getInventories();
        inventories.sort(Comparator.comparing((Inventory i) -> i.getItems().size()).thenComparing(Inventory::getInventoryId));
        inventories.sort(Comparator.comparing((Inventory i) -> i.getItems().size()).thenComparingLong(Inventory::getInventoryId));
        // If we want to sort the inventory list by count of the items and if count matches then by inventory id reversed then use the following.
        //inventories.sort(Comparator.comparing((Inventory i) -> i.getItems().size()).thenComparing(Comparator.comparingLong(Inventory::getInventoryId).reversed()));
        inventories.forEach(System.out::println);
    }

    //To do
    @Test
    void test_sort_students_using_lastName_and_if_lastName_matches_then_sort_using_firstName_but_if_there_are_nulls_in_firstName_they_should_come_before_nonNull_firstName(){
        List<Student> students = new ArrayList<>(List.of(
                new Student("John", "Blue", 'A', 90),
                new Student("Johan", "Sorensson", 'A', 80),
                new Student("Elsa", "Martisson", 'B', 70),
                new Student("Abbey", "Martisson", 'C', 60),
                new Student(null, "Martisson", 'A', 95),
                new Student("Michael", "Jordan", 'A', 98)
        ));
        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Comparator.nullsFirst(Comparator.comparing(Student::getFirstName))));
        students.forEach(System.out::println);
    }

    // sort using date of birth of Employees
    @Test
    void test_should_sort_list_of_employees_using_dateOfBirth() {
        List<Employee> employees = Employee.getEmployees();
        employees.sort(Comparator.comparing(Employee::getDateOfBirth));
        employees.forEach(System.out::println);
    }

    /*
    There are two ways we can reverse sort.
    Method 1: By using the call to reversed() it returns a new comparator which swaps the order in which it compares.
     */
    @Test
    void test_should_sort_list_of_employees_using_dateOfBirth_reversed() {
        List<Employee> employees = Employee.getEmployees();
        employees.sort(Comparator.comparing(Employee::getDateOfBirth).reversed());
        employees.forEach(System.out::println);
    }

    /*
    There are two ways we can reverse sort.
    Method 2: Pass an explicit comparator for comparing the keys extracted by the key extractor.
    The second method is a bit interesting.
    We use the two argument method of the comparing method, which accepts a key extracting function and an explicit comparator to compare the keys.
     */
    @Test
    void test_should_sort_list_of_employees_using_dateOfBirth_reversed_method2() {
        List<Employee> employees = Employee.getEmployees();
        employees.sort(Comparator.comparing(Employee::getDateOfBirth, Comparator.reverseOrder()));
        employees.forEach(System.out::println);
    }



    // Sort a list of Person objects by first name
    // Name field in a few of Person objects may be null
    // Or/And a few of the Person objects itself may be null
}

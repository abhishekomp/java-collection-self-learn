package org.example.jdk21;

import java.util.Comparator;
import java.util.List;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * JDK 16+ (finalized) — RECORDS
 * ─────────────────────────────────────────────────────────────────────────────
 * A record is a special class that is:
 *   • immutable by default (all fields are final)
 *   • automatically provided with: constructor, getters (component accessors),
 *     equals(), hashCode(), and toString()
 *
 * Records are ideal for simple data-carrier objects (DTOs, value objects).
 *
 * Compare with the traditional Employee / Dog classes in the test package —
 * they require ~50 lines of boilerplate that a record replaces with one line.
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class RecordDemo {

    // ── Simple record ─────────────────────────────────────────────────────────
    // The compiler generates: constructor, name(), age(), equals(), hashCode(), toString()
    record Person(String name, int age) {}

    // ── Record with custom compact constructor ────────────────────────────────
    // A compact constructor (no parameter list) lets you add validation/normalisation
    // before the auto-generated assignment of fields.
    record Product(String name, double price) {
        Product {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative: " + price);
            }
            // Compact constructors implicitly assign all components after this block.
        }
    }

    // ── Record implementing an interface ─────────────────────────────────────
    interface Describable {
        String describe();
    }

    record Point(double x, double y) implements Describable {
        // Records can have additional (instance) methods
        public double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }

        @Override
        public String describe() {
            return "Point at (%.2f, %.2f), distance from origin: %.2f"
                    .formatted(x, y, distanceFromOrigin());
        }
    }

    // ── Generic record ────────────────────────────────────────────────────────
    record Pair<A, B>(A first, B second) {}

    public static void main(String[] args) {

        // 1. Basic record usage
        var alice = new Person("Alice", 30);
        System.out.println(alice);                // Person[name=Alice, age=30]
        System.out.println(alice.name());         // Alice   (accessor, NOT getName())
        System.out.println(alice.age());          // 30

        // 2. equals / hashCode are generated automatically
        var alice2 = new Person("Alice", 30);
        System.out.println(alice.equals(alice2)); // true

        // 3. Sorting a list of records — same as with regular classes
        List<Person> people = List.of(
                new Person("Bob",   25),
                new Person("Alice", 30),
                new Person("Carol", 22)
        );
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::name))
                .toList(); // Stream.toList() — Java 16+
        sorted.forEach(System.out::println);

        // 4. Record with validation
        var pen = new Product("Pen", 1.99);
        System.out.println(pen);
        try {
            new Product("Invalid", -5.0); // throws IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // 5. Record with custom methods and interface
        var p = new Point(3.0, 4.0);
        System.out.println(p.describe());         // Point at (3.00, 4.00), distance: 5.00

        // 6. Generic record
        var pair = new Pair<>("hello", 42);
        System.out.println(pair.first() + " -> " + pair.second());
    }
}


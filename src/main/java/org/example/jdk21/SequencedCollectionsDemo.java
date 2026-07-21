package org.example.jdk21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SequencedCollection;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * JDK 21 — SEQUENCED COLLECTIONS  (JEP 431)
 * ─────────────────────────────────────────────────────────────────────────────
 * Java 21 added three new interfaces to the collections hierarchy:
 *
 *   SequencedCollection<E>
 *     │  addFirst(e) / addLast(e)
 *     │  getFirst()  / getLast()
 *     │  removeFirst() / removeLast()
 *     │  reversed()  → a reverse-order view of this collection
 *     ├── List (already sequenced)
 *     ├── Deque
 *     └── SortedSet
 *
 *   SequencedSet<E>      extends SequencedCollection + Set
 *   SequencedMap<K,V>    (getFirst/LastEntry, putFirst/Last, reversed, …)
 *
 * Before JDK 21:
 *   • list.get(0)               → first element
 *   • list.get(list.size() - 1) → last element (error-prone)
 *   • no standard way to get the first/last of a Set or Map
 *
 * After JDK 21:
 *   • list.getFirst()   → first element (throws NoSuchElementException if empty)
 *   • list.getLast()    → last element
 *   • reversed()        → a live reversed view
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class SequencedCollectionsDemo {

    public static void main(String[] args) {

        // ── ArrayList implements SequencedCollection ──────────────────────────
        List<String> fruits = new ArrayList<>(List.of("Mango", "Apple", "Banana", "Cherry"));

        System.out.println("── ArrayList as SequencedCollection ──────────────");
        System.out.println("All     : " + fruits);
        System.out.println("First   : " + fruits.getFirst());   // JDK 21 — no more get(0)
        System.out.println("Last    : " + fruits.getLast());     // JDK 21 — no more get(size-1)

        // addFirst / addLast — prepend or append without index arithmetic
        fruits.addFirst("Pineapple");
        fruits.addLast("Grape");
        System.out.println("After addFirst/Last: " + fruits);

        // removeFirst / removeLast
        String removed = fruits.removeFirst();
        System.out.println("Removed first: " + removed + " → " + fruits);

        // reversed() — returns a live reverse-order view of the collection
        // Changes to the original list are reflected in the reversed view
        SequencedCollection<String> reversedFruits = fruits.reversed();
        System.out.println("Reversed view: " + reversedFruits);

        // ── LinkedList also implements SequencedCollection ────────────────────
        System.out.println("\n── LinkedList as SequencedCollection ─────────────");
        LinkedList<Integer> numbers = new LinkedList<>(List.of(10, 20, 30, 40, 50));
        System.out.println("First  : " + numbers.getFirst());
        System.out.println("Last   : " + numbers.getLast());
        numbers.addFirst(5);
        numbers.addLast(55);
        System.out.println("After addFirst(5) addLast(55): " + numbers);

        // ── Iterating the reversed view ───────────────────────────────────────
        System.out.println("\n── Iterating via reversed() ──────────────────────");
        List<String> words = List.of("Alpha", "Beta", "Gamma", "Delta");
        System.out.print("Normal  : ");
        words.forEach(w -> System.out.print(w + " "));
        System.out.println();

        System.out.print("Reversed: ");
        // reversed() is a view — no new list is created, no copying
        words.reversed().forEach(w -> System.out.print(w + " "));
        System.out.println();

        // ── Practical use-case: last N elements ───────────────────────────────
        System.out.println("\n── Last 3 elements (using getLast / reversed) ────");
        List<Integer> logs = new ArrayList<>(List.of(100, 200, 300, 400, 500, 600));
        List<Integer> lastThree = logs.reversed().stream().limit(3).toList();
        System.out.println("Last 3 log entries: " + lastThree);
    }
}


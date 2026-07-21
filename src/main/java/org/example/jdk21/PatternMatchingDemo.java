package org.example.jdk21;

import java.util.List;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * JDK 16 / 21 — PATTERN MATCHING
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Three forms of pattern matching covered here:
 *
 *   1. Pattern matching for instanceof  (JDK 16 — finalized)
 *      Combines the type test and the cast into a single expression.
 *
 *   2. Pattern matching in switch       (JDK 21 — finalized)
 *      Switch can now match on *types* (not just constants), replacing tall
 *      if-else-instanceof chains with a concise, exhaustive switch expression.
 *
 *   3. Guarded patterns (when clauses)  (JDK 21 — finalized)
 *      A case may include an additional boolean guard: case Type t when t.x() > 0.
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class PatternMatchingDemo {

    // ── 1. Pattern matching for instanceof ───────────────────────────────────

    /**
     * Old style (Java 15 and earlier): explicit cast after instanceof check.
     */
    static String describeOldStyle(Object obj) {
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;           // redundant cast
            return "Integer: " + i;
        } else if (obj instanceof String) {
            String s = (String) obj;             // redundant cast
            return "String of length " + s.length();
        }
        return "Unknown";
    }

    /**
     * New style (Java 16+): the pattern variable is bound on the same line.
     * The cast is gone; the compiler guarantees the type is correct.
     */
    static String describeNewStyle(Object obj) {
        if (obj instanceof Integer i) {           // 'i' is an Integer inside this block
            return "Integer: " + i;
        } else if (obj instanceof String s) {     // 's' is a String inside this block
            return "String of length " + s.length();
        }
        return "Unknown";
    }

    // ── 2. Pattern matching in switch (JDK 21) ───────────────────────────────

    /**
     * A switch expression that dispatches on the runtime type of {@code obj}.
     * Before JDK 21 this had to be a chain of if-else-instanceof blocks.
     *
     * The switch is exhaustive when the last arm is {@code default} or when the
     * sealed type hierarchy covers all cases.
     */
    static String formatValue(Object obj) {
        return switch (obj) {
            case Integer i   -> "int    = " + i;
            case Double  d   -> "double = %.4f".formatted(d);
            case String  s   -> "string = \"%s\" (length %d)".formatted(s, s.length());
            case int[]   arr -> "int[]  of length " + arr.length;
            case null        -> "(null)";
            default          -> "other  : " + obj.getClass().getSimpleName();
        };
    }

    // ── 3. Guarded patterns (when clauses, JDK 21) ───────────────────────────

    // All three permitted types are siblings inside PatternMatchingDemo — use simple names.
    sealed interface Notification permits Email, SMS, PushAlert {}

    record Email(String to, String subject, String body) implements Notification {}
    record SMS(String to, String message)                implements Notification {}
    record PushAlert(String title, int priority)         implements Notification {}

    /**
     * Routes a notification and uses a {@code when} guard to distinguish
     * urgent push alerts from normal ones.
     */
    static String route(Notification n) {
        return switch (n) {
            case Email e     -> "Sending email to %s, subject: [%s]".formatted(e.to(), e.subject());
            case SMS  s      -> "Sending SMS to %s: %s".formatted(s.to(), s.message());
            // Guarded pattern: matches PushAlert only when priority > 5
            case PushAlert p when p.priority() > 5 ->
                    "🔴 URGENT push alert: " + p.title();
            case PushAlert p ->
                    "🔵 Normal push alert: " + p.title();
        };
    }

    // ── 4. Deconstruction patterns with records (JDK 21 preview → 21 final) ──
    // Note: record patterns in switch are finalized in JDK 21.
    record Coordinate(int x, int y) {}

    static String describeCoordinate(Object obj) {
        return switch (obj) {
            // Record pattern: deconstructs the record and binds components directly
            case Coordinate(int x, int y) when x == 0 && y == 0 -> "Origin";
            case Coordinate(int x, int y) when x == 0            -> "On Y-axis at y=" + y;
            case Coordinate(int x, int y) when y == 0            -> "On X-axis at x=" + x;
            case Coordinate(int x, int y)                         -> "Point (%d, %d)".formatted(x, y);
            default -> "Not a coordinate";
        };
    }

    public static void main(String[] args) {

        // 1. instanceof pattern matching
        List<Object> objects = List.of(42, "Hello World", 3.14, new int[]{1, 2, 3});
        System.out.println("── instanceof pattern matching ──────────────────");
        objects.forEach(o -> System.out.println(describeNewStyle(o)));

        // 2. Switch expression with type patterns
        System.out.println("\n── switch type patterns ──────────────────────────");
        List<Object> values = List.of(100, 2.71828, "Java 21", new int[]{5, 10}, true);
        values.forEach(v -> System.out.println(formatValue(v)));
        System.out.println(formatValue(null));

        // 3. Guarded patterns
        System.out.println("\n── guarded patterns ─────────────────────────────");
        List<Notification> notifications = List.of(
                new Email("alice@example.com", "Meeting", "See you at 10am."),
                new SMS("+1-555-0100", "Your OTP is 4321"),
                new PushAlert("Server is down!", 9),
                new PushAlert("New message arrived", 3)
        );
        notifications.forEach(n -> System.out.println(route(n)));

        // 4. Record patterns
        System.out.println("\n── record patterns ──────────────────────────────");
        List<Object> coords = List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 5),
                new Coordinate(3, 0),
                new Coordinate(3, 4),
                "not a coordinate"
        );
        coords.forEach(c -> System.out.println(describeCoordinate(c)));
    }
}


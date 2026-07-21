package org.example.jdk21;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * JDK 15+ (finalized) — TEXT BLOCKS
 * ─────────────────────────────────────────────────────────────────────────────
 * A text block is a multi-line string literal that:
 *   • starts with """ (triple-quote) followed by a newline
 *   • ends with closing """ (which may be on its own line to control indentation)
 *   • automatically strips leading incidental whitespace
 *   • supports the same escape sequences as regular strings, plus:
 *       \s  — explicit trailing space (prevents trimming)
 *       \   — line continuation (suppresses embedded newline)
 *
 * Useful for embedding JSON, HTML, SQL, XML directly in source code.
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class TextBlockDemo {

    public static void main(String[] args) {

        // ── 1. Basic text block ───────────────────────────────────────────────
        // Incidental whitespace (the leading spaces that align with the code)
        // is stripped automatically up to the column of the closing """.
        String html = """
                <html>
                    <body>
                        <p>Hello, Java 21!</p>
                    </body>
                </html>
                """;
        System.out.println("── HTML ───────────────────────────────────────────");
        System.out.println(html);

        // ── 2. JSON payload ───────────────────────────────────────────────────
        String json = """
                {
                    "name":    "Alice",
                    "age":     30,
                    "hobbies": ["reading", "coding"]
                }
                """;
        System.out.println("── JSON ───────────────────────────────────────────");
        System.out.println(json);

        // ── 3. SQL query ──────────────────────────────────────────────────────
        String sql = """
                SELECT e.name,
                       e.salary,
                       d.name AS department
                FROM   employees e
                JOIN   departments d ON e.dept_id = d.id
                WHERE  e.salary > 50000
                ORDER  BY e.salary DESC
                """;
        System.out.println("── SQL ────────────────────────────────────────────");
        System.out.println(sql);

        // ── 4. String interpolation via formatted() ───────────────────────────
        // Text blocks work seamlessly with String.formatted() (an instance method,
        // equivalent to String.format(block, args)).
        String name    = "Bob";
        int    age     = 25;
        double salary  = 72_500.00;

        String profile = """
                Employee Profile
                ─────────────────
                Name   : %s
                Age    : %d
                Salary : $%,.2f
                """.formatted(name, age, salary);
        System.out.println("── Formatted text block ───────────────────────────");
        System.out.println(profile);

        // ── 5. Line continuation (\) ──────────────────────────────────────────
        // Use \ at the end of a line to join it with the next line
        // (useful when you want logical wrapping without a real newline in the value).
        String oneLiner = """
                This is a very long sentence that \
                continues on the next source line \
                but is a single logical line.
                """;
        System.out.println("── Line continuation ──────────────────────────────");
        System.out.println(oneLiner);

        // ── 6. Trailing space preservation (\s) ──────────────────────────────
        // Trailing whitespace is normally stripped; \s keeps it.
        String padded = """
                Left  \s
                Right \s
                """;
        // Each line has exactly one trailing space because of \s.
        System.out.println("── Trailing spaces preserved with \\s ───────────────");
        padded.lines().forEach(line -> System.out.println("|" + line + "|"));

        // ── 7. Comparison: old-style vs text block ────────────────────────────
        // Old style (hard to read, error-prone with escaping)
        @SuppressWarnings("TextBlockMigration")
        String oldStyle = "{\n" +
                          "    \"key\": \"value\"\n" +
                          "}";

        // New style — same content, far more readable
        String newStyle = """
                {
                    "key": "value"
                }""";   // closing """ on same line as last content → no trailing newline

        System.out.println("── Old vs new style equal? " + oldStyle.equals(newStyle));
    }
}


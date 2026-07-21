package org.example.jdk21;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * JDK 17+ (finalized) — SEALED CLASSES
 * ─────────────────────────────────────────────────────────────────────────────
 * A sealed class/interface restricts which other classes may extend or implement
 * it. The permitted subclasses must be declared in the same compilation unit
 * (typically same package, same file or same module).
 *
 * Key benefits:
 *   1. Exhaustive modelling — you know every possible subtype at compile time.
 *   2. Pairs perfectly with switch expressions and pattern matching (JDK 21).
 *   3. Makes the class hierarchy an explicit, closed API contract.
 *
 * Permitted subclasses must be one of:
 *   • final     — cannot be subclassed further
 *   • sealed    — can be subclassed, but with its own permitted list
 *   • non-sealed — opened back up (any class may extend it)
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class SealedClassDemo {

    // ── Sealed interface representing all possible shapes ─────────────────────
    sealed interface Shape permits Circle, Rectangle, Triangle {}

    record Circle(double radius) implements Shape {
        public double area() { return Math.PI * radius * radius; }
    }

    record Rectangle(double width, double height) implements Shape {
        public double area() { return width * height; }
    }

    record Triangle(double base, double height) implements Shape {
        public double area() { return 0.5 * base * height; }
    }

    // ── Sealed class for a bank account hierarchy ─────────────────────────────
    sealed abstract static class BankAccount
            permits BankAccount.SavingsAccount,
                    BankAccount.CurrentAccount,
                    BankAccount.InvestmentAccount {

        private final String id;
        private double balance;

        BankAccount(String id, double balance) {
            this.id      = id;
            this.balance = balance;
        }

        public String getId()      { return id; }
        public double getBalance() { return balance; }

        // ── Permitted subclasses ──────────────────────────────────────────────

        /** Earns interest; withdrawals capped at balance. */
        static final class SavingsAccount extends BankAccount {
            private final double interestRate;
            SavingsAccount(String id, double balance, double interestRate) {
                super(id, balance);
                this.interestRate = interestRate;
            }
            double interestRate() { return interestRate; }
        }

        /** Allows overdraft up to a limit. */
        static final class CurrentAccount extends BankAccount {
            private final double overdraftLimit;
            CurrentAccount(String id, double balance, double overdraftLimit) {
                super(id, balance);
                this.overdraftLimit = overdraftLimit;
            }
            double overdraftLimit() { return overdraftLimit; }
        }

        /** Holds investment portfolios; has a risk rating. */
        static final class InvestmentAccount extends BankAccount {
            private final String riskRating; // LOW / MEDIUM / HIGH
            InvestmentAccount(String id, double balance, String riskRating) {
                super(id, balance);
                this.riskRating = riskRating;
            }
            String riskRating() { return riskRating; }
        }
    }

    // ── Pattern-matching switch over sealed types (JDK 21) ───────────────────
    /**
     * Because Shape is sealed and all permitted subtypes are listed, the compiler
     * can verify that the switch is *exhaustive* — no default branch needed.
     */
    static double computeArea(Shape shape) {
        return switch (shape) {                        // exhaustive — no 'default' needed
            case Circle    c -> c.area();
            case Rectangle r -> r.area();
            case Triangle  t -> t.area();
        };
    }

    static String describeAccount(BankAccount account) {
        return switch (account) {
            case BankAccount.SavingsAccount    s ->
                    "Savings [%s] balance=%.2f rate=%.2f%%"
                            .formatted(s.getId(), s.getBalance(), s.interestRate() * 100);
            case BankAccount.CurrentAccount    c ->
                    "Current [%s] balance=%.2f overdraft_limit=%.2f"
                            .formatted(c.getId(), c.getBalance(), c.overdraftLimit());
            case BankAccount.InvestmentAccount i ->
                    "Investment [%s] balance=%.2f risk=%s"
                            .formatted(i.getId(), i.getBalance(), i.riskRating());
        };
    }

    public static void main(String[] args) {

        // ── Shapes ────────────────────────────────────────────────────────────
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6),
                new Triangle(3, 8)
        };

        for (Shape s : shapes) {
            System.out.printf("%-25s area = %.2f%n", s, computeArea(s));
        }

        // ── Bank accounts ─────────────────────────────────────────────────────
        BankAccount[] accounts = {
                new BankAccount.SavingsAccount   ("S001", 10_000, 0.035),
                new BankAccount.CurrentAccount   ("C001", 2_500, 500),
                new BankAccount.InvestmentAccount("I001", 50_000, "HIGH")
        };

        System.out.println();
        for (BankAccount a : accounts) {
            System.out.println(describeAccount(a));
        }
    }
}


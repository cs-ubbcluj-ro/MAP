package lecture.examples.lecture2;

class MathUtils {
    public static double add(double n1, double n2) {
        return n1 + n2;
    }

    public static int gcd(int n1, int n2) {
        if (n2 == 0)
            return n1;
        return gcd(n2, n1 % n2);
    }
}

public class Rational {
    static double PRIMEGAME_FIRST = 17.0 / 91; // first number in John H. Conway's prime producing machine
    private int numerator;
    private int denominator;
    // 14 numbers which can produce an infinity of primes

    public Rational() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(int num) {
        this(num, 1);
        // this(1, 0); // ERROR
    }

    public Rational(int num, int den) {
        this.numerator = num;
        this.denominator = den;
    }

    @Override
    public String toString() {
        return "Rational{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator &&
                denominator == rational.denominator;
    }

    public Rational add(Rational r) {
        Rational result = new Rational(this.numerator * r.denominator + this.denominator * r.numerator, this.denominator * r.denominator);
        int gcd = MathUtils.gcd(result.numerator, result.denominator);
        result.numerator /= gcd;
        result.denominator /= gcd;
        return result;
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(3);
        Rational r3 = null; // r3 has no object associated

        r1 = r2; // both r1 and r2 refer the same object
        System.out.println(r1);
        System.out.println(r2);

        Rational r4 = new Rational(3);
        System.out.println(r1.equals(r4));
        System.out.println(r1 == r4);

        System.out.println(Rational.PRIMEGAME_FIRST);
        System.out.println(PRIMEGAME_FIRST);
        PRIMEGAME_FIRST = 78.0 / 85; // this is actually the second in the list of rational numbers
        System.out.println(PRIMEGAME_FIRST);

        Rational r5 = new Rational(2, 4);
        Rational r6 = new Rational(1, 2);
        Rational sum = r5.add(r6);
        System.out.println("Sum: " + sum);
    }
}
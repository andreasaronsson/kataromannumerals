package nu.aron.kataromannumerals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.vavr.CheckedFunction1;
import io.vavr.Tuple;
import io.vavr.control.Option;
import io.vavr.test.Arbitrary;
import io.vavr.test.CheckResult;
import io.vavr.test.Property;

class MainTest {

    Main testee;

    @BeforeEach
    void setUp() {
        testee = new Main();
    }

    @Test
    void multipleChars() {
        assertEquals("MM", testee.charMultiple.apply(2, "M"));
        assertEquals("", testee.charMultiple.apply(0, "C"));
    }

    @Test
    void thousands() {
        assertEquals("MM", testee.mille.apply(2000));
    }

    @Test
    void testThrees() {
        Arbitrary<Integer> numbers = Arbitrary.integer()
                .filter(i -> i < 3000)
                .filter(i -> i > 0)
                .filter(i -> i % 10 == 3);
        CheckedFunction1<Integer, Boolean> threeIsIII = i -> testee.numeral(i).endsWith("III");
        CheckResult result = Property.def("threes")
                .forAll(numbers)
                .suchThat(threeIsIII)
                .check();
        Option<Tuple> sample = result.sample();
        sample.stdout();
        result.assertIsSatisfied();
    }

    @Test
    void testFives() {
        Arbitrary<Integer> numbers = Arbitrary.integer()
                .filter(i -> i < 3000)
                .filter(i -> i > 0)
                .filter(i -> i % 10 == 5);
        CheckedFunction1<Integer, Boolean> fiveIsV = i -> testee.numeral(i).endsWith("V");
        CheckResult result = Property.def("fives")
                .forAll(numbers)
                .suchThat(fiveIsV)
                .check();
        Option<Tuple> sample = result.sample();
        sample.stdout();
        result.assertIsSatisfied();
    }

    @Test
    void testOnes() {
        Arbitrary<Integer> numbers = Arbitrary.integer()
                .filter(i -> i < 3000)
                .filter(i -> i > 0)
                .filter(i -> i % 10 == 1);
        CheckedFunction1<Integer, Boolean> oneIsI = i -> testee.numeral(i).endsWith("I");
        CheckResult result = Property.def("ones")
                .forAll(numbers)
                .suchThat(oneIsI)
                .check();
        Option<Tuple> sample = result.sample();
        sample.stdout();
        result.assertIsSatisfied();
    }

    @Test
    void examples() {
        assertEquals("I", testee.numeral(1));
        assertEquals("II", testee.numeral(2));
        assertEquals("III", testee.numeral(3));
        assertEquals("IV", testee.numeral(4));
        assertEquals("V", testee.numeral(5));
        assertEquals("VI", testee.numeral(6));
        assertEquals("VII", testee.numeral(7));
        assertEquals("VIII", testee.numeral(8));
        assertEquals("IX", testee.numeral(9));
        assertEquals("X", testee.numeral(10));
        assertEquals("XIX", testee.numeral(19));
        assertEquals("XLIX", testee.numeral(49));
        assertEquals("XCIX", testee.numeral(99));
        assertEquals("CCCIX", testee.numeral(309));
        assertEquals("DXXVII", testee.numeral(527));
        assertEquals("DCCLXXXIX", testee.numeral(789));
        assertEquals("MDCCCLXXXVIII", testee.numeral(1888));
        assertEquals("MCMXCIX", testee.numeral(1999));
        assertEquals("MMI", testee.numeral(2001));
    }
}

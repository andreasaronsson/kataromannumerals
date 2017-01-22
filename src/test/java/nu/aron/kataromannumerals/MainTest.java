package nu.aron.kataromannumerals;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javaslang.CheckedFunction1;
import javaslang.Tuple;
import javaslang.control.Option;
import javaslang.test.Arbitrary;
import javaslang.test.CheckResult;
import javaslang.test.Property;

public class MainTest {

    Main testee;

    @Before
    public void setUp() {
        testee = new Main();
    }

    @Test
    public void multipleChars() {
        assertThat(testee.charMultiple.apply(2, "M"), is("MM"));
        assertThat(testee.charMultiple.apply(0, "C"), is(""));
    }

    @Test
    public void thousands() {
        assertThat(testee.mille.apply(2000), is("MM"));
    }

    @Test
    public final void testThrees() {
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
    public final void testFives() {
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
    public final void testOnes() {
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
    public void examples() {
        assertThat(testee.numeral(1), is("I"));
        assertThat(testee.numeral(2), is("II"));
        assertThat(testee.numeral(3), is("III"));
        assertThat(testee.numeral(4), is("IV"));
        assertThat(testee.numeral(5), is("V"));
        assertThat(testee.numeral(6), is("VI"));
        assertThat(testee.numeral(7), is("VII"));
        assertThat(testee.numeral(8), is("VIII"));
        assertThat(testee.numeral(9), is("IX"));
        assertThat(testee.numeral(10), is("X"));
        assertThat(testee.numeral(19), is("XIX"));
        assertThat(testee.numeral(49), is("XLIX"));
        assertThat(testee.numeral(99), is("XCIX"));
        assertThat(testee.numeral(309), is("CCCIX"));
        assertThat(testee.numeral(527), is("DXXVII"));
        assertThat(testee.numeral(789), is("DCCLXXXIX"));
        assertThat(testee.numeral(1888), is("MDCCCLXXXVIII"));
        assertThat(testee.numeral(1999), is("MCMXCIX"));
        assertThat(testee.numeral(2001), is("MMI"));
    }
}

package nu.aron.kataromannumerals;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import nu.aron.kataromannumerals.Main;

public class MainTest {

    @Test
    public final void testOne() {

        assertThat(Main.numeral(1), is("I"));
    }

    @Test
    public final void testTen() {
        assertThat(Main.numeral(10), is("X"));
    }

    @Test
    public final void testSeven() {
        assertThat(Main.numeral(7), is("VII"));
    }
}

package aron.nu.kataromannumerals;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MainTest {

    @Test
    public final void testMain() {
        assertThat(Main.numeral(1), is("I"));
    }
}

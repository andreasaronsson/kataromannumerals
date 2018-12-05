package nu.aron.kataromannumerals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistryTest {

    Registry registry;

    @BeforeEach
    void setUp() {
        // registry = new Registry(empty());
    }

    @Test
    void test() {
        System.err.println("One");
        Registry.get().stdout();
        Registry.add("first");
        System.err.println("Two");
        Registry.get().stdout();
        Registry.add("second");
        Registry.add("third");
        System.err.println("Three");
        Registry.get().stdout();
        Registry.remove("second");
        System.err.println("Four");
        Registry.get().stdout();
    }
}

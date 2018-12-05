package nu.aron.kataromannumerals;

import static io.vavr.collection.HashSet.empty;

import io.vavr.collection.Set;

public class Registry {
    private static Set<String> mappings = empty();

    Registry() {
        // static
    }

    public static void remove(String key) {
        mappings = mappings.remove(key);
    }

    public static void add(String key) {
        mappings = mappings.add(key);
    }

    public static Set<String> get() {
        return mappings;
    }

    @Override
    public String toString() {
        return mappings.toString();
    }
}

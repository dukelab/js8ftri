package dukelab.js8ftri.ch3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

public class Exercise07 {

    public enum Type {
        NORMAL,
        REVERSED,
        SPACE_SENSITIVE,
        SPACE_INSENSITIVE,
        CASE_SENSITIVE,
        CASE_INSENSITIVE
    }

    public static void main(String[] args) {
        test("hello", "hell o", Type.NORMAL);
        test("hello", "hello", Type.NORMAL);
        test("hello", "hell o", Type.REVERSED);
        test("hello", "hello", Type.REVERSED);
        test("hello", "HEllo", Type.CASE_SENSITIVE);
        test("hello", "HEllo", Type.CASE_INSENSITIVE);
        test("hell o", "h ello", Type.SPACE_SENSITIVE);
        test("hell o", "h ello", Type.SPACE_INSENSITIVE);
        test("H e l l o", "h Ellox   ", Type.CASE_INSENSITIVE, Type.SPACE_INSENSITIVE);
        test("H e l l o", "h Ello   ", Type.REVERSED, Type.CASE_INSENSITIVE);
        test("H e l l o", "h Ello   ", Type.REVERSED, Type.CASE_INSENSITIVE, Type.SPACE_INSENSITIVE);
    }

    private static void test(String s1, String s2, Type... types) {
        Type first = types[0];
        Type[] remaining = types.length > 1 ? Arrays.copyOfRange(types, 1, types.length) : new Type[0];
        Comparator<String> c = generate(first, remaining);
        System.out.printf("%s(%s, %s) : %d \n", Arrays.toString(types), s1, s2, c.compare(s1, s2));
    }

    private static Comparator<String> generate(Type first, Type... types) {
        BiFunction<String, String, String> fun = convert(first);
        for (final Type type : types) {
            final BiFunction<String, String, String> before = fun;
            final BiFunction<String, String, String> after = convert(type);
            fun = (s1, s2) -> after.apply(before.apply(s1, s2), before.apply(s2, s1));
        }
        final BiFunction<String, String, String> local = fun;
        return (s1, s2) -> local.apply(s1, s2).compareTo(local.apply(s2, s1));
    }

    private static BiFunction<String, String, String> convert(Type type) {
        // m = main string, s = swap
        switch (type) {
        case REVERSED:
            return (m, s) -> s;
        case CASE_INSENSITIVE:
            return (m, s) -> m.toLowerCase();
        case SPACE_INSENSITIVE:
            return (m, s) -> m.replaceAll("\\s+", "");
        default:
        }
        return (m, s) -> m;
    }

}

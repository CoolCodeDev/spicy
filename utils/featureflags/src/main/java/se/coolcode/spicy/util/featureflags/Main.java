package se.coolcode.spicy.util.featureflags;

public class Main {

    public static void main(String[] args) {

        LetterPrinter printer = FeatureFlags.objectToggle(
                () -> false,
                LetterPrinter.class,
                () -> System.out.println("AAA"),
                () -> System.out.println("BBB"));
        printer.print();
    }

    public static interface LetterPrinter {
        void print();
    }

}

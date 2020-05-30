import javax.annotation.Nonnull;

@SuppressWarnings({"ConstantConditions", "UnusedReturnValue"})
public class Example {

    public static void main(String... args) {

//        businessFunctionWithNonNullParam(null); // Fehler richtig erkannt

//        businessFunctionWithNullCheck(null); // Richtigerweise kein Fehler erkannt

//        businessFunctionWithNullCheckAndAnnotation(null); // Fälschlicherweise kein Fehler erkannt
    }

    public static int businessFunctionWithNullCheck(Object object) {
        if(object == null) return -1;
        return object.hashCode();
    }

    public static int businessFunctionWithNonNullParam(Object object) {
        return object.hashCode();
    }

    public static int businessFunctionWithNullCheckAndAnnotation(@Nonnull Object object) {
        if(object == null) return -1;
        return object.hashCode();
    }

}

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Example {

    public static int businessFunctionWithNonNullParam(Object object) {
        //null würde NPE erzeugen. Muss erkannt werden.
        return object.hashCode();
    }

    public static int businessFunctionWithNullCheck(Object object) {
        //Null erzeugt keine NPE. Darf nicht als Fehler erkannt werden.
        if (object == null) return -1;
        return object.hashCode();
    }

    public static int businessFunctionWithNullCheckAndAnnotation(@NotNull Object object) {
        //Null erzeugt keine NPE, aber der Parameter ist dennoch mit @NonNull annotiert. Muss erkannt werden.
        if (object == null) return -1;
        return object.hashCode();
    }

    @NotNull
    public static Object getNull() {
        //Rückgabe ist mit @NonNull annotiert, Methode darf nicht null zurückgeben. Muss erkannt werden.
        return randomNullOrYolo();
    }

    private static Object randomNullOrYolo() {
        if (Math.random() > 0.5)
            return null;
        else
            return "yolo";
    }

    public static void main(String... args) {

        //Aufruf würde NPE erzeugen. Muss erkannt werden.
        System.out.println(businessFunctionWithNonNullParam(randomNullOrYolo()));

        //Aufruf erzeugt keine NPE. Darf nicht als Fehler erkannt werden.
        System.out.println(businessFunctionWithNullCheck(randomNullOrYolo()));

        //Para, ist mit @NonNull annotiert. Muss erkannt werden.
        System.out.println(businessFunctionWithNullCheckAndAnnotation(randomNullOrYolo()));

        System.out.println(getNull());
    }

}

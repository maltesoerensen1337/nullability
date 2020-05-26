import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Example {

    public static void main(String... args) {

        //das soll eigentlich getan werden:
        businessFunctionWithNonNullParam(null); //aber `null` ist nicht erlaubt

        //CON: kompiliert, aber erzeugt NPE
        //CON: IntelliJ warnt, dass `null` übergeben wird
        //CON: IntelliJ warnt in der Methode, dass der Param vom Typ `Optional` ist
        //CON: die eigentlich auszuführende Logik wird versteckt
        optionalParam(null);

        //CON: keine NPE, dennoch Warnung, dass `null` übergeben wird
        //CON: Null-check hebt Vorteil des Optionals völlig auf
        //CON: IntelliJ warnt in der Methode, dass der Param vom Typ `Optional` ist
        //CON: IntelliJ warnt in der Methode, dass ein Null-check auf ein Optional gemacht wird
        //CON: die eigentlich auszuführende Logik wird versteckt
        optionalParamAndNullCheck(null);

        //ok
        //CON: die eigentlich auszuführende Logik wird versteckt
        nullableParamWithInternalOptional(null);

        //Wie sieht das aus, wenn zwei non-null Paramter gefordert sind?
    }

    public static int optionalParam(Optional<Object> object) {
        return object
                .map(Example::businessFunctionWithNonNullParam)
                .orElse(0);
    }

    public static int optionalParamAndNullCheck(Optional<Object> object) {
        if (object == null) return 0;
        return object
                .map(Example::businessFunctionWithNonNullParam)
                .orElse(0);
    }

    public static int nullableParamWithInternalOptional(Object object) {
        return Optional.ofNullable(object)
                .map(Example::businessFunctionWithNonNullParam)
                .orElse(0);
    }

    public static int businessFunctionWithNonNullParam(Object object) {
        return object.hashCode();
    }

}

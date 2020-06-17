# Nullability in Java

## Warum?
* Eine Sorge wenige
* Code wird einfacher und besser lesbar
  - Keine Nullchecks nötig
  - Keine Tests für Nullchecks

```java
int doSomething(MyObject object) { 
  if(object == null) return 0; 
  //... 
} 
```

```java
@Test 
void shouldReturnZeroWhenNull() { 
  int actual = doSomething(null); 
  AssertEquals(0,actual); 
} 
```

Vielleicht brauchen wir viele Nullchecks gar nicht?
```java
String getAbteilungsKuerzel(Person person) {
  //return person.getJob().getAbteilung().getKuerzel();
  if(person == null) { 
    return null;
  } else {
    if(person.getJob() == null) {
        return null;
    } else {
      if(person.getJob().getAbteilung() == null) {
        return null;
      } else {
        return person.getJob().getAbteilung().getKuerzel()
      }        
    }
  } 
} 
```

### Neuere Sprachen machen das auch:

Das Kind hat verschiedene Namen:
* safe call operator
* optional chaining
* ...

#### Kotlin  
```
var x: String? = null
var l: Int? = x?.length()
```
#### Swift 
```
let x: String? = nil
let l: Int? = x?.count
```
#### c# (announced for version 8) 
```
string? x = null;
int? l = x?.Length
```
#### TypeScript (mit Union-Types)
`const x: string | null = null`

ab Version 3.7:

`let l = x?.length()`
#### JavaScript 
```
const x: string | null = null
let l = x?.length()
```

* https://github.com/tc39/proposal-optional-chaining

* https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Optional_chaining

## Typische Ansätze bei Java

### Optional

Optional wurde erfunden, um besser mit Null-Werten in Streams umgehen zu können. Sie waren ursprünglich nur als Rückgabewerte von Methoden gedacht.

Das Problem bei Optional ist, dass das Problem nicht gelöst, sondern verschlimmert wird:
* Es gibt jetzt drei Ausprägungen (null, Optional.empty(), Optional mit Wert)
* Vor dem Auslesen des Optional muss geprüft werden, ob das Optional einen Wert enthält - ansonsten gibt es eine Exception
* Optional ist eine Klasse (ca. 16 Byte Overhead). Diese Objekte müssen vom Garbage-Collector verwaltet werden.

### Annotationen ohne Überprüfung (JSR 305)

Es gibt einen Entwurf für einen Java-Standard und es drängt sich auf, die `javax.validation`-Annotationen zu verwenden. Damit ist die korrekte Verwendung dokumentiert. Effektiv ist das aber nur ein Wunsch: "ich wünsche mir, dass dieser Parameter niemals null ist".

Allerdings, dieser Standard wird seit 2006 nicht mehr weiterverfolgt. Die meisten statischen Codechecker betrachten die Annotationen des JSR305 als deprecated.

### Annotationen mit Überprüfung zur Laufzeit

Hibernate und Lombok können die Parameter einer Methode zur Laufzeit überprüfen. Das sorgt dafür,
* dass eine bessere Fehlermeldung angezeigt wird
* dass die Fehlermeldung zu einem früheren Zeitpunkt - und damit näher an der Ursache des Fehlers - geworfen wird.

Es bleibt das Problem, dass der Fehler erst in Produktion auffällt.

### Annotation mit Überprüfung zur Compile- bzw. Editierzeit

Eclipse, IntelliJ, Sonarqube und mehrere Maven- bzw. Gradle-Plugins können den Fehler bereits beim Kompilieren entdecken. Bei den IDE fällt der Fehler bereits beim Schreiben des Quelltextes auf.

Nachteil: die IDEs verwenden eigene Annotationen. Zum Kompilieren wird eine weitere Library gebraucht.
Diese Library ist auch zur Laufzeit erforderlich, da die Annotations eine Class- oder Runtime-Retention haben.

#### Scope der Annotationen

| @Retention(RUNTIME)                          | @Retention(CLASS)                 |
| -------------------------------------------- | --------------------------------- |
| `javax.annotation`                           | `android.support.annotation`      |
| `javax.validation.constraints`               | `edu.umd.cs.findbugs.annotations` |
| `org.checkerframework.checker.nullness.qual` | `org.eclipse.jdt.annotation`      |
|                                              | `org.jetbrains.annotations`       |

#### Wo können die Annotationen verwendet werden?

|                                   | FIELD | METHOD | PARAMETER | LOCAL_VARIABLE |
| --------------------------------- | ----- | ------ | --------- | -------------- |
| `android.support.annotation`      | X     | X      | X         |                |
| `edu.umd.cs.findbugs.annotations` | X     | X      | X         | X              |
| `org.jetbrains.annotation`        | X     | X      | X         | X              |
| `lombok`                          | X     | X      | X         | X              |
| `javax.validation.constraints`    | X     | X      | X         |                |


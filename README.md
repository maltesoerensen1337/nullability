# Nullability in Java

## Warum?
* Eine Sorge wenige
* Code wird einfacher
  - Keine Nullchecks nötig
  - Keine Tests für Nullchecks

```
int doSomething(MyObject object) { 
  if(object == null) return 0; 
  //... 
} 
```
```
@Test 
void shouldReturnZeroWhenNull() { 
  int actual = doSomething(null); 
  AssertEquals(0,actual); 
} 
```

### Neuere Sprachen machen das auch:

#### Kotlin  
`var x: String? = null;`
#### Swift 
`var x: String? = nil;`
#### c# (announced for version 8) 
`string? x = null;`
#### TypeScript (mit Union-Types)
`const string: string | null = null`

## Typische Ansätze bei Java

### Optional

Optional wurde erfunden, um besser mit Null-Werten in Streams umgehen zu können. Sie waren ursprünglich nur als Rückgabewerte von Methoden gedacht.

Das Problem bei Optional ist, dass das Problem nicht gelöst, sondern verschlimmert wird:
* Es gibt jetzt drei Ausprägungen (null, Optional.empty(), Optional mit Wert)
* Vor dem Auslesen des Optional muss geprüft werden, ob das Optional einen Wert enthält - ansonsten gibt es eine Exception
* Optional ist eine Klasse (ca. 16 Byte Overhead). Diese Objekte müssen vom Garbage-Collector verwaltet werden.

### Annotationen ohne Überprüfung

Es drängt sich auf, die Javax.validation-Annotationen zu verwenden. Damit ist korrekte Verwendung dokumentiert. Effektiv ist das aber nur ein Wunsch: "ich wünsche mir, dass dieser Parameter niemals null ist".

### Annotationen mit Überprüfung zur Laufzeit

Hibernate und Lombok können die Parameter einer Methode zur Laufzeit überprüfen. Das sorgt dafür,
* dass eine bessere Fehlermeldung angezeigt wird
* dass die Fehlermeldung zu einem früheren Zeitpunkt - und damit näher an der Ursache des Fehlers - geworfen wird.

Es bleibt das Problem, dass der Fehler erst in Produktion auffällt.

### Annotation mit Überprüfung zur Compile- bzw. Editierzeit

Eclipse, IntelliJ, Sonarqube und mehrere Maven- bzw. Gradleplugins können den Fehler bereits beim Kompilieren entdecken. Bei den IDE fällt der Fehler bereits beim Schreiben des Quelltextes auf.

Nachteil: die IDEs verwenden eigene Annotationen. Zum Kompilieren wird eine weitere Library gebraucht. Zur Laufzeit kann die Library weggelassen werden.

### JSR305

Es gibt einen Entwurf für einen Java-Standard, der seit 2006 nicht mehr weiterverfolgt wurde. Die meisten statischen Codechecker betrachten die Annotationen des JSR305 als deprecated.


## Scope der Annotationen

| @Retention(RUNTIME)                        | @Retention(CLASS)               |
| ------------------------------------------ | ------------------------------- |
| javax.annotation                           | android.support.annotation      |
| javax.validation.constraints               | edu.umd.cs.findbugs.annotations |
| org.checkerframework.checker.nullness.qual | org.eclipse.jdt.annotation      |
|                                            | org.jetbrains.annotations       |


## Wo können die Annotationen verwendet werden?

|                                 | FIELD | METHOD | PARAMETER | LOCAL_VARIABLE |
| ------------------------------- | ----- | ------ | --------- | -------------- |
| android.support.annotation      | X     | X      | X         |                |
| edu.umd.cs.findbugs.annotations | X     | X      | X         | X              |
| org.jetbrains.annotation        | X     | X      | X         | X              |
| lombok                          | X     | X      | X         | X              |
| javax.validation.constraints    | X     | X      | X         |                |

## Lukas Eder ist dagegen

Argumentationskette: In Java - vor allem bei den OR-Mappern - gibt es ohnehin schon so viele Annotationen, dass sie eine eigene Programmiersprache bilden. Neue Annotationen sollten nur nach reiflicher Überlegung hinzugefügt werden. NullPointerExceptions sind nach Lukas Eders Meinung nicht wichtig genug. 

Die ausführliche Argumentation ist hier: https://blog.jooq.org/2016/11/24/the-java-ecosystems-obsession-with-nonnull-annotations/
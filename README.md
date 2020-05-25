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
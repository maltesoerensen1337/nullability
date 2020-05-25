# Nullability in Java

## Warum?
* Eine Sorge wenige 
* Code wird einfacher 
** Keine Nullchecks nötig 
** Keine Tests für Nullchecks 

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

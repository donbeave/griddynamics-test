# griddynamics-test

## Jar

1. Prepare jar

```
./gradlew release
```

2. Help

```
java -jar anagram-0.1.jar
```

3. Normal mode

```
java -jar anagram-0.1.jar example1.txt output.txt
```

`example1.txt` - source file
`output.txt` - destination file

4. Interactive mode

```
java -jar anagram-0.1.jar -i
```

## Gradle

1. Loading dependencies and install gradle by wrapper

```
./gradlew
```

2. Run app directly from Gradle

```
./gradlew runApp -PinputFile=example2.txt -PoutputFile=output.txt --info
```

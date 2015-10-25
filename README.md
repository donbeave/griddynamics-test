# griddynamics-test

## Jar

#### 1. Prepare jar

```
./gradlew release
```

#### 2. Print help

```
java -jar anagram-0.1.jar --help
```

#### 3. Normal mode

```
java -jar anagram-0.1.jar example1.txt output.txt
```

`example1.txt` - source file
`output.txt` - destination file

#### 4. Interactive mode

```
java -jar anagram-0.1.jar -i
```

## Gradle

#### 1. Loading dependencies and install gradle by wrapper

```
./gradlew
```

#### 1. Run app directly from Gradle

```
./gradlew runApp -PinputFile=example2.txt -PoutputFile=output.txt
```

#### 2. Run unit tests

```
./gradlew test
```

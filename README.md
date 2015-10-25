# griddynamics-test

## Jar

#### 1. Prepare jar

```
./gradlew release
```

#### 2. Print help

```
java -jar project.jar --help
```

#### 3. Normal mode

```
java -jar project.jar example1.txt output.txt
```

`example1.txt` - source file
`output.txt` - destination file

#### 4. Interactive mode

```
java -jar project.jar -i
```

## Gradle

#### 1. Loading dependencies and install gradle by wrapper

```
./gradlew
```

#### 2. Run app directly from Gradle

```
./gradlew runApp -PinputFile=example2.txt -PoutputFile=output.txt
```

#### 3. Run unit tests

```
./gradlew test
```

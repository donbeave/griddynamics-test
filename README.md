# griddynamics-test

## Jar

#### 1. Print help

```
java -jar project.jar --help
```

#### 2. Normal mode

```
java -jar project.jar example1.txt output.txt
```

`example1.txt` - source file
`output.txt` - destination file

#### 3. Interactive mode

```
java -jar project.jar -i
```

## Gradle

#### 1. Load dependencies and install gradle by wrapper

```
./gradlew
```

#### 2. Prepare project.jar

```
./gradlew release
```

#### 3. Run app directly from Gradle

```
./gradlew runApp -PinputFile=example2.txt -PoutputFile=output.txt
```

#### 4. Run unit tests

```
./gradlew test
```

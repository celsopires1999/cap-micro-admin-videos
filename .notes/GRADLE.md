Execute the following command to run the tests in the `CategoryE2ETest` class:
```bash
./gradlew :infrastructure:test --tests CategoryE2ETest
export $(grep -v '^#' .env | xargs) && ./gradlew bootRun
export $(grep -v '^#' .env | xargs) && ./gradlew bootRun --debug-jvm
```
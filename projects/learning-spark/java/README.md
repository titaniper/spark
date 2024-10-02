

1. brew install gradle


```
gradle --refresh-dependencies; gradle clean build; gradle run
```


javap는 Java 디스어셈블러로, 자바 바이트코드 파일(.class 파일)을 읽어서 바이트코드나 클래스의 구조를 사람이 읽을 수 있는 형태로 출력해주는 명령어 도구입니다. JDK에 포함되어 있으며, 주로 자바 프로그램의 컴파일된 바이트코드를 분석하거나 디버깅할 때 사용됩니다.

javap 명령어
javap는 주어진 클래스 파일의 메서드 서명, 필드 정보, 접근 제어자 등을 확인할 수 있어요. javap는 기본적으로 클래스의 구조를 보여주지만, 다양한 플래그를 사용해 더 상세한 정보를 볼 수 있습니다.

-c 플래그
-c 플래그는 컴파일된 바이트코드를 출력합니다. 즉, 자바 소스 코드를 컴파일한 후 생성된 **JVM 명령어(JVM instructions)**들을 확인할 수 있어요. 바이트코드는 JVM이 실행하는 명령어 집합으로, 프로그램의 실행 흐름을 이해하는 데 매우 유용합니다.
```
javap -c './app/build/classes/java/main/org/example/App.class'             
```
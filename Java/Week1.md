## **1. JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.**

### TO-DO - 자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.
* **JVM이란 무엇인가**
  
  * JVM의 정의
    * Java virtual machine의 줄임말로 자바를 실행하기 위한 가상 컴퓨터
  * JVM의 역할
    * Java Byte Code를 운영체제에 맞게 해석해주는 역할
    * 메모리 관리 담당 (C계열에서는 프로그래머가 메모리 할당 및 해제를 해야 했지만, Java에서는 JVM이 자동으로 관리 해줌)
  * JVM이 나오게 된 계기
    * 일반 애플리케이션은 OS와 바로 맞붙어 있어서 Windows, Mac OS X, Linux등과 같은 OS들은 각 OS에 종속적인데, 이러한 종속적인 결함을 극복하기 위해 Java 애플리케이션은 JVM이라는 가상머신과의 소통을 통해 OS에 종속적이지 않게끔 해줌(쉽게 말해 OS에 상관없이 실행시키기 위해 있는 것)
    * 해당 OS에서 실행가능한 JVM이 필요

* **컴파일 하는 방법**
  1. .java 확장자로 된 파일 생성
  2. Java 파일 컴파일을 위해 **javac filename.java** 를 통해 Java 파일 컴파일
  3. 컴파일이 정상적으로 되면 filename.class 파일이 생성되는데, java filename을 입력하면 main 메소드를 찾아 java파일을 실행
    
* **실행하는 방법**
  1. Java파일을 Java Class 파일로 변환하는 단계
     *  프로그래머가 작성한 Java 파일을 Java 컴파일러가 Java Byte Code로 구성된 Java Class 파일로 변환시킨다. 
     *  Java Class 파일은 JVM이 이해할 수 있는 언어로 변환된 것
     *  JVM이 설치되어 있으면 어떠한 운영체제라도 실행할 수 있음
  2. Java Class 파일을 JVM에 넣는 단계
     * JVM이 Java Class 파일을 해석하고 수행하기 위해 Class loader를 통해 로드하고 Java Class 파일들은 JVM 내의 Execution engine을 통해 해석됨   
  3. 수행되는 단계
     * Execution engine을 통해 해석된 프로그램이 Runtime Data Area에 배치되어 수행이 이루어짐
     * 경우에 따라 Garbage Collection 등의 작업이 수행될 수 있음 
* **바이트코드란 무엇인가**
* **JIT 컴파일러란 무엇이며 어떻게 동작하는지**
* **JVM 구성 요소**
* **JDK와 JRE의 차이**

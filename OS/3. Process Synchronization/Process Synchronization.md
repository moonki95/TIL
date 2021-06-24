#### 21.06.21 임계구역 문제 해결방법 sw방법
#### 21.06.22 Mutex Lock
#### 21.06.24 교착상태(Deadlock)
## **1. 임계구역 문제**

* **임계구역**
  * 임계구역이란 공유자원에 접근하는 코드의 일부를 말함 -> 변수,테이블,파일쓰기 등
  * 보통 한 프로세스가 임계구역에서 수행하고 있으면 다른 프로세스가 이 임계구역에 접근할 수 없는 것이 시스템의 기본 원칙임
    ```c
    do {
        entry section**
            critical section //(임계구역)
        exit section
            remainder section
    } while (true);
    ```
    * entry section - 임계구역에 진입하기 위해 진입 허가 요청하는 코드 -> 프로세스가 이미 임계구역 안에 있다면 이곳에서 대기
    * exit section - 임계구역에서 탈출하는 곳
    * remainder section - entry, exit section 이외 나머지 부분

* **임계구역 문제**
  * 임계구역 문제란, **임계구역으로 포함되어야 할 코드 영역이 임계구역으로 지정되지 않았을 때 발생할 수 있는 문제**를 뜻함

* **임계구역 문제 해결조건**
  * 상호 배제 - 하나의 프로세스가 임계구역에 들어가 있으면, 다른 프로세스들은 들어갈 수 없어야 함
  * 진행 - 자신의 임계구역에서 실행되는 프로세스가 없는데 진입하려는 프로세스들이 많다면, 어떤 프로세스가 들어가야할지 정해줘야하며, 이 선택은 무한정 연기될 수 없음
  * 한정 대기 - 다른 프로세스들의 기아상태를 방지하기 위해서, 한 번 임계구역에 들어간 프로세스는 다음번 임계구역을 들어갈 때 제한을 두어야 함

* **임계구역 문제 해결 방법**
  * SW적인 해결
    * 피터슨의 해결법
      * 두 프로세스가 **두 개의 데이터 항목을 공유**하여 임계구역에 들어가는 것을 구분
        ```c
        int turn;
        bool flag[2];
        ```
        ```c
        do{
            flag[i] = true; // 임계영역에 진입할 준비가 됨
            turn j; // 임계영역에 진입할 순번
            while (flag[j] && turn == j);
            critical section //임계구역
            flag[i] = false;
            reminder section
        }while (true);
      * 상호배제, 진행, 한정된 대기의 요구조건을 만족하기 때문에 방법론으로 쓰이지만, 실제로는 이러한 구조에서 올바르게 실행된다고 보장할 수는 없음
  * HW적인 해결
    * Peterson's solution이 올바르게 동작한다는것을 보장할 수 없으므로 <span style="color:green">**lock**</span> 을 사용해 임계영역 직전에 lock을 획득하고 임계영역을 나오면서 lock을 반환하는 기법을 사용하여 임계영역 문제를 해결한다.
      * Test_and_Set() 명령어 정의
        ```c
        bool test_and_set(bool *target){
          bool rv = *target;
          *target = true;
          return rv
        }
        ``` 
      * Test_and_set() 명령어를 사용한 상호 배제 구현
        ```c
        do{
          while (test_and_set(&lock)); // lock을 test_and_set 함수에 넣어 임계영역에서 수행

          /* critical section */

          lock = flase; // 임계영역 빠져나오면 lock을 false로 바꿔줌

          /* remainder section */
        }while (true);
        ```
      * compare_and_swap() 명령어 정의
        ```c
        int compare and swap(int *value, int expected, int new value){
          int temp = *value;
          if (*value == expected)
            *value = new value;
          return temp;
        }
        ```
      * compare_and_swap() 명령어를 사용한 상호 배제 구현
        ```c
        do{
          while (compare_and_swap(&lock, 0, 1) != 0); // lock을 compare_and_swap에 넣고 임계영역에서 수행

          /* critical section */

          lock = 0; // 임계영역 빠져나오면 lock을 0으로 바꿔줌

          /* remainder section */
        }while (true);
        ```
      * test_and_set() 명령어를 사용해 한정된 대기 조건을 만족시키는 상호 배제
        ```c
        do{
          waiting[i] = true;
          key = true;
          while (waiting[i] && key)
            key = test_and_set(&lock);
          waiting[i] = false; // 임계구역 탈출

          /* critical section */

          j = (i+1) % n;
          while ((j != i) && !waiting[j])
            j = ( j + 1 ) % n;

          if (j == i)
            lock = false;
          else
            waiting[j] = false;

          /* remainder section */
        }
        ```
## **2. 뮤텍스 락**
* **Mutex**
  * Mutual Exclusion의 축약말로, 임계구역을 보호하고 경쟁 조건을 방지하기 위해 Mutex Lock을 사용함
  * 공유된 자원의 데이터 혹은 임계영역 등에 하나의 Process 혹은 Thread가 접근하는 것을 막아줌(동기화 대상이 하나)
  * 프로세스는 임계구역에 들어가기 전에 반드시 lock을 획득해서 들어가고, 나올 때 lock을 반환해야 함
  * 초기값을 0과 1로 가짐
* **Mutex Lock**
  * available 이라는 <span style="color:skyblue">bool</span> 변수를 가지며, 이 변수 값이 lock의 가용 여부를 표시
  * lock이 가용하면 acquire()호출 및 lock은 사용불가로 전환
  * 사용불가 상태의 lock을 획득하려고 시도하는 프로세스는 lock이 반환될 때까지 봉쇄
    ```c
    acquire() {
      while(!available)
        ; /* busy wait*/
        available = false;
    }
    ```

    ```c
    release() {
      available = true;
    }
    ```
  * 임계구역 안에 들어가기를 원하는 프로세스들은 acquire() 함수를 반복적으로 호출해야하는데, 마치 프로세스가 회전한다 해서 <span style = "color:green">**스핀락**</span> 이라고도 부름
  * 이 스핀락은 lock을 기다리는 동안 상당한 시간을 소모하는 context switching을 전혀 필요로 하지 않는다는 장점이 있음
  * 짧은 시간 동안만 스핀락을 소유할 것이라고 예상되면 스핀락이 유용

## **3. 세마포어**
  * 공유된 자원의 데이터 혹은 임계영역 등에 여러개의 Process 혹은 Thread가 접근하는 것을 막아줌(동기화 대상이 여러개)
  * 공유자원의 개수를 나타내는 변수
    * ```c
      typedef struct {
        int value; // 세마포어의 값
        struct process *list; //프로세스 리스트
      } semaphore;
      ```
  * wait() 와 signal() 연산으로만 접근 가능
    * wait() = P 연산 (검사하다라는 의미지만 세마포어의 값을 감소시키는 역할)
    * signal() = V 연산(증가하다는 의미로 세마포어의 값을 증가시키는 역할)
    * ```c
      void wait(semaphore *S){ 
        S -> value--;
        if (S->value <0) {
          프로세스를 S->list 에 넣음;
          block(); // 자기를 호출한 프로세스 중지시킴
        }
      }
      ```
    * ```c
      void signal(semaphore *S){
        S -> value++;
        if (S->value <= 0){
          S->list에서 하나의 프로세스 P를 꺼냄;
          wakeup(P); // 봉쇄된 프로세스 의 실행을 재개시킴
        }
      }
      ```
    * 세마포어 S를 대기하는 프로세스는 다른 프로세스가 signal() 연산을 수행하면 다시 시작
    * 세마포어의 종류
      * 카운팅 세마포어
        * 이진 세마포어와는 다르게 제한없는 영역을 가짐
        * 위에 설명했던것이 카운팅 세마포어
      * 이진 세마포어
        * 뮤텍스가 가진 성질처럼 세마포어의 초기 값이 0 또는 1로만 가질 수 있는 세마포어

## **4. 교착 상태 및 기아(Deadlock and Starvation)**
  * 두 개 이상의 프로세스가 어떤 사건을 무한정 기다리는 상황이 발생할 수 있는데, 이를 **교착상태** 라고 부름
  * **교착상태 발생의 필요 충분 조건**
    * **상호 배제(Mutual Exclusion)**
      * 한 번에 하나의 한 개의 프로세스만이 공유 자원을 사용할 수 있어야 함
    * **점유와 대기(Hold and Wait)**
      * 프로세스는 최소한 하나의 자원을 점유한 채, 현재 다른 프로세스에 의해 점유된 자원을 추가로 얻기 위해 반드시 대기해야 함
    * **비선점(No preemption)**
      * 다른 프로세스에 할당된 자원은 사용이 끝날때까지 강제로 빼앗을 수 없음
    * **환형 대기(Circular wait)**
      * 공유자원을 사용하는 프로세스와 공유자원을 사용하기 위해 대기하는 프로세스들이 원형으로 구성되어 있어 자신에게 할당된 자원을 점유하면서 앞이나 뒤에 있는 프로세스의 자원을 요구해야 함
    * 교착상태가 발생하기 위해 위 네가지 조건이 충족되어야 하는데, 하나라도 조건을 충족하지 않으면 교착상태가 발생하지 않음
  * **교착상태 처리 방법**
    * 예방기법
      * 필요조건들 중 하나가 성립하지 않도록 보장하는 방법
      * 자원낭비가 가장 심한 기법
    * 회피기법
      * 교착상태가 발생할 가능성이 있는지 OS가 검사하고, 빠질 가능성이 없는 경우에만 자원을 할당해서 문제 발생을 회피하는 기법
    * 회복기법
      * 교착상태를 일으킨 프로세스를 종료하거나 교착상태의 프로세스에 할당된 자원을 선점하여 프로세스나 자원을 회복하는 기법
    * 발견기법
      * 시스템에 교착상태가 발생했는지 점검하여 교착상태에 있는 프로세스와 자원을 발견하는 기법

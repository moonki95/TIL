## 1. 프로세스의 개념

* **프로세스**
	* 현재 실행중인 프로그램을 의미
	* 프로그램 그 자체는 프로세스가 아님
		* 프로그램은 명령어 리스트를 내용으로 가진 디스크에 저장된 파일(실행파일)인 수동적인 존재
		* 프로세스는 다음에 실행할 명령어를 지정하는 프로그램 카운터와 관련 자원의 집합을 가진 능동적인 존재

* **프로세스의 구성 요소**
	*	함수의 매개변수
	*	프로세스 스택 (복귀주소, 로컬변수같은 임시적인 자료 저장)
	*	데이터 섹션 (전역변수들을 수록)
	*	힙 (프로세스 실행 중에 동적으로 할당되는 메모리)

## 2. 프로세스의 상태

* 프로세스는 실행되면서 그 상태가 변하는데, 프로세스 상태는 부분적으로 그 프로세스 현재 활동에 따라서 정의됨

	* New : 프로세스가 생성중인 상태
	* Running : CPU를 가지고 명령어들이 실행되고 있는 상태
	* Waiting : 프로세스가 어떠한 사건이 일어나기를 기다리고 있는 상태
	* Ready : 프로세스가 처리기에 할당되기를 기다리는 상태
	* Terminated : 프로세스의 실행이 종료된 상태 

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f1348474-8de1-4c6f-81cd-2ad09e75884f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210524%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210524T062215Z&X-Amz-Expires=86400&X-Amz-Signature=090b9782ef02116936f8b99815e0214b9c3c12bf83eaf0f4a32455446b3ff1da&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

## 3. 프로세스 제어 블록(Process Controll Block)

* **특정 프로세스와 연관된 여러 정보를 수록**
	* **OS가 관리상 사용하는 정보**
		* 프로세스 상태 - New, Ready, Running, Wating, Terminated 정보들 저장
		* CPU 스케줄링 정보 - 프로세스 우선순위, 스케줄링 큐에 대한 포인터와 다른 스케줄링 매개변수들 포함
		
	* **CPU 수행 관련 하드웨어 값**
		* 프로그램 카운터 - 이 프로세스가 다음에 실행할 명령어의 주소를 가리킴
		* CPU 레지스터 - 누산기, 인덱스 레지스터, 스택 레지스터, 범용 레지스터들과 상태 코드 정보를 포함
	* **메모리 관리 정보** - 운영체제에 의해 사용되는 메모리 시스템에 따라 기준 레지스터(base register) 와 한계 레지스터의 값, 운영체제가 사용하는 메모리 시스템에 따라 페이지 테이블 또는 세그먼트 테이블 등과 같은 정보를 포함
	
	* **Accounting 정보** - CPU 사용 시간과 경과된 실시간, 시간제한, 계정 번호, Job 또는 프로세스 번호 등을 포함
	
	* **입출력 상태 정보** - 프로세스에게 할당된 입출력 장치들과 열린 파일의 목록 등을 포함
	
* 각 프로세스는 OS에서 프로세스 제어 블록(PCB)에 의해 표현됨

![enter image description here](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/c96b2dcb-e096-4b98-b227-6142579c2614/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5/20210524/us-west-2/s3/aws4_request&X-Amz-Date=20210524T062441Z&X-Amz-Expires=86400&X-Amz-Signature=a0f5d72b3d5bce4985bbb241b9f81e150ced146eacee2f67a443379dd7a04dcc&X-Amz-SignedHeaders=host&response-content-disposition=filename%20=%22Untitled.png%22)

## 4. 프로세스 스케줄링

* **다중 프로그래밍** - **CPU 이용률을 극대화**시키기 위해 어떤 프로세스가 항상 실행되도록 하는 프로그래밍

* **시분할 프로그래밍** - 프로그램이 실행되는 동안 사용자가 상호 작용할 수 있도록 프로세스들 사이에서 **CPU를 빈번하게 교체하는 것**

* 이 두개의 목적을 달성하기 위해 프로세스 스케줄러가 CPU에서 실행 가능한 여러 프로세스들 중에서 하나의 프로세스를 선택

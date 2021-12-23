# **Chap4. 위젯과 리소스 다루기**

***
## **생명주기**

### 생명주기란?
    * 앱이 실행된 후 다른 액티비티로 화면이 전환되거나 스마트폰이 꺼지고 켜지는 등의 상태 변화가 있을 시 현재 화면에 보이는 액티비티의 생명 주기 메소드를 호출해서 상태 변화를 알려주는 사이클

### 생명주기 메소드

* onCreate
    * asdf
* onStart
* onResume
* onPause
* onStop
* onDestroy 

***
## **레이아웃**

### 레이아웃이란?
    * lay something out의 펼쳐놓다의 준말로, 일정 공간에 시각적 효과를 고려하여 요소를 배치하는 것을 뜻함
    * 스마트폰에서는 화면 위에 요소를 배치 하는 일이라고 생각하면 됨
***
### 레이아웃 파일
    * 처음 프로젝트를 만들면 MainActivity.kt 파일과 activity_main.xml파일이 만들어지는데, 이 중 activity_main.xml파일이 이 프로젝트의 레이아웃 파일이라고 함
  
***
### 레이아웃의 종류
    * Constraint 레이아웃
      * 안드로이드의 기본 레이아웃
      * 간단한 제약조건(Constraint) 설정만으로 전체 화면을 쉽게 구성할 수 있음
<img src="/Kotlin_study/images/constraint.png" width="700" height="370">

    * 리니어 레이아웃
    * 프레임 레이아웃
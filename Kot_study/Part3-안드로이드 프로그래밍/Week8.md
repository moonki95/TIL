# **Chap6. 권한**

## **권한과 권한의 유형**

### **권한이란?**
  * 안드로이드에서는 앱이 동작하면서 카메라, 달력, 전화번호부같은 개인정보를 노출할 우려가 있을 때 사용자에게 자격을 받는 것 

### **권한을 주는 방법**

* 권한 명세(AndroidManifest.xml)
  * <uses-permission />태그를 이용
* 기능 명세 
  * <uses-feature /> 태그를 이용


## **권한의 보호 수준**

### **권한의 종류(수준에 따른)**
  * 일반 권한
    * AndroidManifest.xml파일에 명세하면 사용자에게 권한 승인을 묻는 팝업창을 보여줌
    * 인터넷 사용, 알람 설정 등
    * <users-permission android:name = "android.permission.~"/>
  * 위험 권한
    * 개인정보와 관련된 것에 액세스 하려는 등에 대한 권한
    * <uses-permission android:name = "android.permission.~"/>
  * 서명 권한
    * 앱이 권한을 정의하는 앱과 동일한 인증서로 서명된 경우 시스템은 권한을 자동으로 부여

### **권한 그룹**
  * 캘린더 읽기-쓰기(캘린더), 주소록 읽기-쓰기-계정 가져오기(컨텍츠) 등의 하나의 그룹의 권한을 부여하면 모두 다 부여하는 권한

### **위험한 권한 처리하기**
  * 위험한 권한은 AndroidManifest.xml 파일 수정 후 소스 코드에도 추가로 처리해야 함
  * 보통 3단계를 거침
    * 1단계 : 권한에 대한 사용자 승인 확인
    * 2단계 : 사용자에게 승인 요청
    * 3단계 : 사용자 승인 후 처리

### **BaseActivity 설계하기**
  * 권한 처리같은 반복적인 코드들을 무작정 다 넣기보다는 BaseActivity를 하나 만들어두고 상속받아서 사용하는게 더 효율적
  * 추상클래스로 설계


# **Chap7. 파일 입출력과 SharedPreferences**

## **파일 입출력**

### **저장소의 종류와 권한**
  * 안드로이드는 리눅스 위에 가상머신이 돌아가는 플랫폼
  * 설치된 앱 하나당 UID, 해당하는 디렉터리가 할당, 각각의 디렉터리는 해당 사용자만 접근 가능
  * 내부 저장소
    * 특정 앱의 사용자가 접근할 수 있는 영역
    * 주로 내 앱에서만 사용하는 데이터 저장 
    * 앱 삭제시 함께 삭제
  * 외부 저장소
    * 모든 앱이 공용으로 사용할 수 있는 영역
    * 보통 사진, 벨소리 음악, 화면캡처 등의 파일이 저장됨
    * 사용자의 승인이 필요

### **내부 저장소 파일 읽기**
  * File 클래스 생성 후 각종 정보를 얻거나 기능 사용
    * val file = File("경로")
    * file.exists() : 파일의 존재 여부
    * file.isFile : 파일인지 확인
    * file.isDirectory : 디렉토리인지 확인
    * file.name : 생성된 파일, 디렉토리의 이름반환
    * file.createNewFile() : 해당 경로에 파일이 존재하지 않으면 파일 생성
    * file.delete : 파일 삭제
    * file.absolutePath : 절대경로 반환
    * 실제로 데이터를 읽고 쓰려면 stream 이라는 클래스 사용
      * 읽기전용, 쓰기전용 스트림 따로 사용
      *  InputStream Reader
      *  OutputStream Writer
   * openFileInput으로 읽기 코드를 단축시킬 수 있음

### **내부 저장소 파일 쓰기**
  * 쓰기 파일은 3개의 파라미터 사용(생성할 디렉토리, 파일명, 작성할 내용)
  * openFileOutput으로 쓰기 코드를 단축시킬 수 있음

## **SharedPreferences**
  * 간단한 데이터의 저장 목적(권한 설정이 필요없음)
  * 로그인 정보나 앱의 상태 정보 저장용도

### **SharedPreferneces 사용**
  * 저장
    * 1단계 : SharedPreference 생성
    * 2단계 : Editor 꺼내기
    * 3단계 : putInt(), putString() 메소드로 저장
    * 4단계 : apply()로 값 반영
  * 읽기
    * 1단계 : SharedPreference 생성
    * 2단계 : getInt(), getString() 메소드로 값 읽기

### **getSharedPreferneces 사용**
  * getSharedPreferences(이름,모드)
  * Context를 가지고 있는 모든 컴포넌트에서 접근과 호출이 가능
  
### **getPreferences()**
  * 개별 액티비티이거나 하나밖에 없는앱일때 사용

### **Editor 인터페이스**
  * SharedPreferences로 데이터를 저장하기 위해서 Editor 인터페이스를 사용하는데 edit() 메서드를 호출해 사용 가능
  * 데이터 저장 메소드
    * putFloat(key,value)
    * putLong(key,value)
    * putInt(key,value)
    * putString(key,value)
    * putBoolean(key,value)
    * putStringSet(key,value)
  * 데이터 호출 메소드
    * getFloat(key,value)
    * getLong(key,value)
    * getInt(key,value)
    * getString(key,value)
    * getBoolean(key,value)
    * getStringSet(key,value)
# Intellij 자주쓰는 단축키 정리

Editing
Ctrl + Space : Basic code completion (the name of any class,method or variable)
Ctrl + Shift + Space : Smart code completion (filters the list of methodsand variables by expected type)
Ctrl + Shift + Enter:  Complete statement
Ctrl + P  : 함수호출시 인수 정보 확인 (within method call arguments)
Ctrl + Q  : 코드에 대한 문서창 팝업
Shift + F1 : 코드에 대한 문서 인터넷 브라우저로 팝업
Ctrl + mouse  : 코드를 링크처럼 타고 들어감
Ctrl + F1 : Show descriptions of error or warning at caret
Alt + Insert  : 코드 생성 (Getters, Setters, Constructors,hashCode/equals, toString)
Ctrl + O  : 메서드 오버라이드 구현
Ctrl + I    : 인터페이스 메서드 구현
Ctrl + Alt + T  : 다음으로 코드 감싸기… (if..else, try..catch, for,synchronized, etc.)
Ctrl + / : 줄 단위 주석 토글
Ctrl + Shift + /  : 블럭 단위 주석 토글
Ctrl + W : 가장 안쪽의 괄호부터 선택(점점 확장 된다.)
Ctrl + Shift + W : Decrease current selection to previous state
Alt + Q : Context info
Alt + Enter : Show intention actions and quick-fixes
Ctrl + Alt + L  : 파일 단위 재정렬 (이클립스의 ctrl + shift + f)
Ctrl + Alt + O : import 문 최적화
Ctrl + Alt + I  : 줄단위 재정렬
Tab / Shift + Tab  : 들여쓰기/내어쓰기
Ctrl + X or Shift + Delete : 잘라내기 (블럭 선택이 안되어 있으면 라인을 잘라냄)
Ctrl + C or Ctrl + Insert : 복사하기(블럭 선택이 안되어 있으면 라인을 복사함)
Ctrl + V or Shift + Insert : 붙여넣기
Ctrl + Shift + V : 복사하기 (목록에서 선택하여)
Ctrl + D : 선택된 블럭을 복제
Ctrl + Y : 캐럿을 있는 곳의 라인 삭제
Ctrl + Shift + J : 스마트하게 코드를 한 줄로 합친다.
Ctrl + Enter : 스마트하게 코드를 여러줄로 나눈다.
Shift + Enter : 커서가 어디에 있건 다음 라인을 생성하고 첫줄로 이동
Ctrl + Shift + U : 커서가 있는 곳이나 블럭이 있는 곳을 대문자 및 소문자로 치화
Ctrl + Shift + ] / [  : 가장 가까운 괄호 시작/종료로 이동
Ctrl + Delete : 단어 삭제 (커서 시작부터)
Ctrl + Backspace : Delete to word start
Ctrl + NumPad+/- : Expand/collapse code block
Ctrl + Shift + NumPad+ : Expand all
Ctrl + Shift + NumPad- : Collapse all
Ctrl + F4 : Close active editor tab
Double Shift Search everywhere
Ctrl + F : Find
F3 : Find next
Shift + F3 : Find previous
Ctrl + R : Replace
Ctrl + Shift + F : Find in path
Ctrl + Shift + R : Replace in path
Ctrl + Shift + S : Search structurally (Ultimate Edition only)
Ctrl + Shift + M : Replace structurally (Ultimate Edition only)
Usage Search
Alt + F7 / Ctrl + F7 : Find usages , Find usages in file
Ctrl + Shift + F7 : Highlight usages in file
Ctrl + Alt + F7 : Show usages
Compile and Run
Ctrl + F9 : Make project (compile modifed and dependent)
Ctrl + Shift + F9 : Compile selected file, package or module
Alt + Shift + F10 : Select configuration and run
Alt + Shift + F9 : Select configuration and debug
Shift + F10 : Run
Shift + F9 : Debug
Ctrl + Shift + F10 : Run context configuration from editor
Debugging
F8 : Step over
F7 : Step into
Shift + F7 : Smart step into
Shift + F8 : Step out
Alt + F9 : Run to cursor
Alt + F8 : Evaluate expression
F9 : Resume program
Ctrl + F8 : Toggle breakpoint
Ctrl + Shift + F8 : View breakpoints
Navigation
Ctrl + N : Go to class
Ctrl + Shift + N : Go to file
Ctrl + Alt + Shift + N : Go to symbol
Alt + Right/Left : Go to next/previous editor tab
F12 : Go back to previous tool window
Esc : Go to editor (from tool window)
Shift + Esc : Hide active or last active window
Ctrl + Shift + F4 : Close active run/messages/find/... tab
Ctrl + G : Go to line
Ctrl + E : Recent files popup
Ctrl + Alt + Left/Right : Navigate back/forward
Ctrl + Shift + Backspace : Navigate to last edit location
Alt + F1 : Select current file or symbol in any view
Ctrl + B or Ctrl + Click : Go to declaration
Ctrl + Alt + B : Go to implementation(s)
Ctrl + Shift + I : Open quick definition lookup
Ctrl + Shift + B : Go to type declaration
Ctrl + U : Go to super-method/super-class
Alt + Up/Down : Go to previous/next method
Ctrl + ] / [ : Move to code block end/start
Ctrl + F12 File : structure popup
Ctrl + H Type : hierarchy
Ctrl + Shift + H : Method hierarchy
Ctrl + Alt + H : Call hierarchy
F2 / Shift + F2 : Next/previous highlighted error
F4 / Ctrl + Enter : Edit source / View source
Alt + Home : Show navigation bar
F11 : Toggle bookmark
Ctrl + F11 : Toggle bookmark with mnemonic
Ctrl + #[0-9] : Go to numbered bookmark
Shift + F11 : Show bookmarks
Refactoring
F5 : Copy
F6 : Move
Alt + Delete : Safe Delete
Shift + F6 : Rename
Ctrl + F6 : Change Signature
Ctrl + Alt + N : Inline
Ctrl + Alt + M : Extract Method
Ctrl + Alt + V : Extract Variable
Ctrl + Alt + F : Extract Field
Ctrl + Alt + C:  Extract Constant
Ctrl + Alt + P : Extract Parameter
VCS/Local History
Ctrl + K : Commit project to VCS
Ctrl + T : Update project from VCS
Alt + Shift + C : View recent changes
Alt + BackQuote (`) : ‘VCS’ quick popup
Live Templates
Ctrl + Alt + J : Surround with Live Template
Ctrl + J : Insert Live Template
iter : Iteration according to Java SDK 1.5 style
inst : Check object type with instanceof and downcast it
itco : Iterate elements of java.util.Collection
itit : Iterate elements of java.util.Iterator
itli : Iterate elements of java.util.List
psf : public static final
thr : throw new
General
Alt + #[0-9] : Open corresponding tool window
Ctrl + S : Save all
Ctrl + Alt + Y : Synchronize
Ctrl + Shift + F12 : Toggle maximizing editor
Alt + Shift + F : Add to Favorites
Alt + Shift + I : Inspect current file with curre?nt profile
Ctrl + BackQuote (`) : Quick switch current scheme
Ctrl + Alt + S : Open Settings dialog
Ctrl + Alt + Shift + S : Open Project Structure dialog
Ctrl + Shift + A : Find Action
Ctrl + Tab : Switch between tabs and tool window
메인메소드 생성 및 실행
- 디렉토리, 패키지, 클래스 등 생성 목록 보기
맥 : Command + n
윈도우 : Alt + Insert

- 코드 템플릿
메인 메소드 : psm
System.out.println() : sout
if Null 구문 : ifn

실행환경 실행
- 현재포커스
맥 : Command + Shift + R
윈도우, 리눅스 : Shift + Ctrl + F10
- 이전실행
맥 : Ctrl + R
윈도우 : Shift + F10
라인 수정하기
-라인 복사
맥 : Command + D
윈도우 : Ctrl + D

-라인 삭제
맥 : Command + 백스페이스
윈도우 : Ctrl + Y

-라인 합치기(라인단위)
맥 : Command + Shift + J
윈도우 : Ctrl + Shift + J

라인 단위로 옮기기
- 구문 이동
맥 : Command + Shift + 위,아래
윈도우 : Ctrl + Shift + 위,아래
- 라인 이동
맥 : Option + Shift + 위,아래
윈도우 : Alt + Shift + 위,아래

- Element 단위로 옮기기
맥 : Option + Shift + Command+ 왼쪽,오른쪽
윈도우 : Alt + Ctrl + Shift + 왼쪽,오른쪽
코드 즉시보기
- 인자값 즉시 보기
맥 : Command + P
윈도우 : Ctrl + P

- 코드 구현부 즉시 보기
맥 : Option + Space
윈도우 : Shift + Ctrl + I

- Doc 즉시 보기
맥 : F1
윈도우 : Ctrl + Q
포커스 에디터
- 단어별 이동
맥 : Alt + <, >
윈도우, 리눅스 : Ctrl + <, >

- 단어별 선택
맥 : Shift + Alt + <, >
윈도우, 리눅스 : Shift + Ctrl + <, >

- 라인 첫/끝 이동
맥 : Fn + <, >
윈도우 : Home, End

- 라인 전체 선택
맥 : Shift + Command + <, >
윈도우, 리눅스 : Shift + Home, End

- Page Up/Down
맥 : Fn + 위/아래
윈도우 : Page Up / Down
포커스 특수키
- 포커스 범위 한 단계씩 늘리기
맥 : Alt + 위/아래 화살표
윈도우, 리눅스 : Ctrl + W(위) / Shift + Ctrl + W(아래) 

- 포커스 뒤로/앞으로 가기
맥 : Command + [ , ]
윈도우, 리눅스 : Ctrl + Alt + 좌,우

- 멀티 포커스
맥 : Alt + Alt + 아래
윈도우, 리눅스 : Ctrl + Ctrl + 아래

- 오류 라인 자동 포커스
맥 : F2
윈도우, 리눅스 : F2
검색 텍스트
- 현재 파일에서 검색
맥 : Command + F
윈도우 : Ctrl + F

- 현재 파일에서 교체
맥 : Command + R
윈도우 : Ctrl + R

- 전체 검색
맥 : Command + Shift + F
윈도우 : Ctrl + Shift + F

- 정규표현식으로 검색, 교체
맥, 윈도우 : Regex 체크
검색기타
- 파일 검색
맥 : Shift + Command + O
윈도우 : Shift + Ctrl +  N

- 메소드 검색
맥 : Alt + Command + O
윈도우 : Shift + Ctrl + Alt + N

- Action 검색
맥 : Shift + Command + A
윈도우 : Shift + Ctrl + A

- 최근 열었던 파일 목록 보기
맥 : Command + E
윈도우 : Ctrl + E

- 최근 수정했던 파일 목록 보기
맥 : Command + Shift+ E
윈도우 : Ctrl + Shift + E

- 변수/필드의 데이터 변경 지점 찾기
변경되는 포인트 : 변수나 필드에 커서를 놓고 action 에서 "dataflow" 입력 후 "Analyze Dataflow to Here" 선택
영향주는 포인트 : 변수나 필드에 커서를 놓고 action 에서 "dataflow" 입력 후 "Analyze Dataflow from Here" 선택

- 중복된 코드 찾기
action에서 " Locate Duplicate" 입력

자동완성
- 스마트 자동완성
맥 : control + Shift + Space
윈도우 : control + Shift + Space

- 스태틱 메소드 자동완성
맥 : control + Shift * 2
윈도우 : control + Shift * 2

- Getter/Setter/생성자 자동완성
맥 : Command + N
윈도우 : Alt + Insert

- 자동완성
맥 : control + I
윈도우 : Ctrl + I
Live Template
- Live Template 목록 보기
맥 : Command + J
윈도우, 리눅스 : Ctrl + J

- Live Template 메뉴에서 나만의 템플릿 추가 가능
리팩토링 Extract
- 변수 추출하기
맥 : Command + Option + V
윈도우, 리눅스 :  Ctrl + Alt + V

- 파라미터 추출하기
맥 : Command + Option + P
윈도우, 리눅스 : Ctrl + Alt + P

- 메소드 추출하기
맥 : Command + Option + M
윈도우, 리눅스 : Ctrl + Alt + M

- 이너클래스 추출하기
맥 : F6
윈도우, 리눅스 : F6
리팩토링 기타
- 이름 일괄 변경 하기
맥 : Shift + F6
윈도우, 리눅스 : Shift + F6

- 메소드 일괄 변경하기
맥 : Shift + Command + F6
윈도우, 리눅스 : Shift + Ctrl + F6

- Import 정리하기
맥 : control + Option + O
윈도우, 리눅스 : Ctrl + Alt + O

- Import 자동 정리하기
Settings | Editor | General | Auto Import에서 Optimize imports on the fly 선택

- 코드 자동 정렬하기
맥 : Command + Option + L
윈도우, 리눅스 : Ctrl + Alt + L
디버깅
- Debug 모드로 실행하기(현재 위치의 메소드)
맥 : control + Shift + D
윈도우, 리눅스 : 없음

- Debug 모드로 실행하기(이전에 실행한 메소드)
맥 : control + D
윈도우, 리눅스 : Shift + F9

- Resume(다음 브레이크 포인트로 이동하기)
맥 : Command + Option + R
윈도우, 리눅스 : F9

- Step Over(현재 브레이크에서 다음 한줄로 이동하기)
맥 : F8
윈도우, 리눅스 : F8

- Step Into(현재 브레이크의 다음 메소드로 이동)
맥 : F7
윈도우, 리눅스 : F7

- Step Out(현재 메소드의 밖으로 이동)
맥 : Shift + F8
윈도우, 리눅스 : Shift + F8

- Evaluate Expression(브레이크된 상태에서 코드 사용하기)
맥 : Option+ F8
윈도우, 리눅스 : Alt + F8

- Watch(브레이크 이후의 코드 변경 확인하기)
맥 : 없음
윈도우, 리눅스 : 없음
Git 기본 기능 사용하기
- Git View On
맥 : Command + 9
윈도우, 리눅스 : Alt + 9

- Git Option Popup
맥 : control + V
윈도우, 리눅스 : Alt + '(Tab 위 버튼)

- Git History
맥 : control + V --> 4
윈도우, 리눅스 : Alt + '(Tab 위 버튼) --> 4

- Branch
맥 : control + V --> 7
윈도우, 리눅스 : Alt + '(Tab 위 버튼) --> 7

- Commit
맥 : Command + k
윈도우, 리눅스 : Ctrl + k

- Push
맥 : Command + Shift + k
윈도우, 리눅스 : Ctrl + Shift + k

- Pull
맥 : Command + Shift + A --> git pull
윈도우, 리눅스 : Ctrl + Shift + A --> git pull
GitHub 연동하기
- GitHub 연동하기
맥 : Command + Shift + A --> Share github
윈도우, 리눅스 : Command + Shift + A --> Share github

- GitHub Clone
메인 화면에서 Check out from Version Control 선택 후 Git 선택

클래스
- 클래스 구조 확인
맥 : command+7
윈동, : Alt + 7

플러그인
- 플러그인 설치
맥 : Command + Shift + A --> Plugins(Preferences)
윈도우, 리눅스 : Command + Shift + A --> Plugins(Preferences)

- Terminal
맥 : Option+ F12
윈도우, 리눅스 : Alt + F12

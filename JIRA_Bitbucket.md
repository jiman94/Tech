# JIRA와 Bitbucket을 사용해 이슈로부터 개발을 추적 사용 가이드   

### 시나리오
> 버그로부터 이슈를 추적하기 위한 간단한 시나리오는 다음과 같다. 

#### 1. 버그 이슈 등록
#### 2. 버그를 해결하기 위해, 개발 Branch 생성하여 버그 수정
#### 3. 수정된 버그를 메인 branch에 반영하기 위해 Pull Request 생성
#### 4. 코드 리뷰 및 토론
#### 5. 코드 Merge 및 이슈로부터 추적 

---

#### 1. 버그 이슈 등록
만약 QA 조직으로부터 하나의 버그가 발견되었다고 가정해보자. QA 조직에서는 이슈 트래커에 해당 버그를 등록하게 될 것이다. 


#### 2. Branch 생성
개발자는 자신에게 할당된 버그를 확인하고 그 버그를 수정할 것이다. JIRA와 Git 저장소 관리 솔루션인 Bitbucket을 사용하면, JIRA의 Development 섹션에서 Create branch를 확인할 수 있다. 이를 통해 다음과 같이 버그 수정을 위한 Branch를 생성한다. 
해당 Branch에 버그 픽스를 위한 개발을 수행하고 수정된 코드를 Commit 및 Push를 한다. 

#### 3. Pull Request 생성
모든 코드가 수정이 완료되면 수정된 코드를 리뷰 및 머지하기 위해 Pull Request를 생성한다. Pull Request를 Bitbucket에서 생성할 수 있으며, 다음 그림은 그 예시를 보여준다. 

Pull Request의 처음에는 어느 Branch의 코드를 어디로 Merge할 것인지를 보여준다. Branch를 선택하게되면 해당 Branch의 모든 Commit들의 리스트를 확인할 수 있다. 계속 진행을 위해 Continue 버튼을 클릭한다.


다음 페이페이지에서는 Pull Request를 위한 제목(Title), 설명(Description), 리뷰어(Reviewers)를 입력해주고 Create 버튼을 클릭 해준다.

#### 4. 코드 리뷰 및 토론

생성된 Pull Request를 통해 코드를 리뷰 및 토론을 수행할 수 있다. 

Overview : Pull Request의 전체 현황을 확인할 수 있다.
Diff : 코드를 비교 및 인라인 주석을 작성할 수 있다.
Commits : Pull Request와 연관된 전체 Commit을 확인할 수 있다. 


Diff 탭의 예시를 보여주고 있다. 
수정된 코드에 인라인 코멘트를 작성하여 팀원들과 수정된 코드를 리뷰 및 토론할 수 있으며, 해당 주석과 함께 Task를 할당할 수 있다. 코드의 변경 사항을 확인하기 위해서는 Side-by-Side로 혹은 Unified Diff 모두를 제공한다.

#### 5. Code Merge
수정된 코드에 대한 모든 리뷰와 토론을 마치게되면, 해당 코드는 Pull Request에 따라 메인 Branch로 Merge할 수 있다.

소스 코드를 Merge를 하면 다음과 같이 Commits 리스트에서 확인할 수 있다. 


JIRA의 해당 Issue에서도 해당 이슈와 관련된 생성된 Branch, Commit, Pull Request의 리스트를 추적할 수 있으며, 클릭 시 Bitbucket에서 해당 정보를 확인할 수 있다.  


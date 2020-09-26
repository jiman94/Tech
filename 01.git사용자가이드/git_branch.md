<h3 id="git-flow-branch-전략">Git flow branch 전략</h3>

<table>
<thead>
<tr>
<th>Branch</th>
<th>목적</th>
</tr>
</thead>

<tbody>
<tr>
<td>master</td>
<td>제품 출시</td>
</tr>

<tr>
<td>develop</td>
<td>다음 버전 개발</td>
</tr>

<tr>
<td>feature/{feature_name}</td>
<td>기능 개발</td>
</tr>

<tr>
<td>release</td>
<td>제품 출시 준비</td>
</tr>

<tr>
<td>hotfix</td>
<td>출시된 버전 버그 수정</td>
</tr>
</tbody>
</table>


<h3 id="changes-branch-전략">Changes branch 전략</h3>

<table>
<thead>
<tr>
<th>Branch</th>
<th>목적</th>
<th>특징</th>
</tr>
</thead>

<tbody>
<tr>
<td>master</td>
<td>제품 출시</td>
<td>tag, DevOps 도구에 의해 자동 빌드 및 배포</td>
</tr>

<tr>
<td>develop</td>
<td>다음 버전 개발</td>
<td>Changes 취합, 정적 분석 및 테스트 100% 성공 시만 병합</td>
</tr>

<tr>
<td>release</td>
<td>제품 출시 준비</td>
<td>제품 Versioning, QA</td>
</tr>

<tr>
<td>hotfix</td>
<td>출시된 버전 버그 수정</td>
<td>열심히 고쳐야함!</td>
</tr>
</tbody>
</table>

![ex_screenshot](./images/git-flow_overall_graph.png)


# gitflow 

upstream/feature-user 브랜치에서 작업 브랜치(bfm-100_login_layout)를 생성합니다.

git checkout -b bfm-100_login_layout --track upstream/feature-user

git commit 

커밋 2개 합친다. 

git rebase -i HEAD~2

git pull --rebase upstream feature-user

#### 작업 브랜치를 origin에 push합니다.
(bfm-100_login_layout)]$ git push origin bfm-100_login_layout

#### Github에서 bfm-100_login_layout 브랜치를 feature-user에 merge하는 Pull Request를 생성합니다.
같은 feature를 개발하는 동료에게 리뷰 승인을 받은 후 자신의 Pull Request를 merge합니다. 만약 혼자 feature를 개발한다면 1~2명의 동료에게 리뷰 승인을 받은 후 Pull Request를 merge합니다.

#### feature-user 브랜치에 upstream/develop 브랜치를 merge 합니다.
(feature-user)]gitfetchupstream(feature−user)] git merge --no-ff upstream/develop

#### upstream/develop의 변경사항이 merge된 feature-user를 upstream에 push 합니다.
(feature-user)]$ git push upstream feature-user

#### develop 브랜치에 upstream/feature-user 브랜치를 merge 합니다.
(develop)]gitfetchupstream(develop)] git merge --no-ff upstream/feature-user

#### upstream/feature-user 기능이 merge된 develop를 upstream에 push 합니다.
(develop)]$ git push upstream develop

#### release-1.0.0 브랜치를 생성합니다.
(develop)]gitfetchupstream(develop)] git checkout -b release-1.0.0 --track upstream/develop

#### release-1.0.0 브랜치를 upstream에 push합니다.
(release-1.0.0)]$ git push upstream release-1.0.0

#### release 브랜치에서 버그 티켓에 대한 브랜치를 생성합니다.
(release-1.0.0)]$ git checkout -b bfm-101_bug_login_id_max_length

#### 작업 브랜치를 origin에 push 합니다.
git push origin bfm-101_bug_login_id_max_length

Github에서 bfm-101_bug_login_id_max_length 브랜치를 release-1.0.0에 merge 하는 Pull Request를 생성합니다.
동료에게 리뷰 승인을 받은 후 자신의 Pull Request를 merge 합니다.

#### release 브랜치를 최신 상태로 갱신합니다.
(release-1.0.0)]$ git pull upstream release-1.0.0

#### release 브랜치를 develop 브랜치에 merge 합니다.
(develop)] git pull upstream develop
(develop)]$ git merge --no-ff release-1.0.0

#### develop 브랜치를 upstream에 push 합니다.
(develop)]$ git push upstream develop

#### release 브랜치를 master 브랜치에 merge 합니다.
(develop)]gitcheckoutmaster(master)] git pull upstream master
(master)]$ git merge --no-ff release-1.0.0

#### 1.0.0 태그를 추가합니다.
(master)]$ git tag 1.0.0

master 브랜치와 1.0.0 태그를 upstream에 push 합니다.
(master)]$ git push upstream master 1.0.0



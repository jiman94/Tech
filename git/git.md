touch README.md

git add .

git commit -m "add README"

git push -u origin master




git remote 
git remote show origin

git remote add test https://github.com/studi.git

git remote -v 

git remote rename test temp

git log orgin/master

git remote rm temp

git remote -v

---

# branch 

git branch 

git branch develop

git checkout develop

git branch 

git add  .

git commit -m "add "

git log

git checkout master 

git merge develop

git put 

git branch -d develop

git branch 


git branche develop

git checkou develop

git add .

git commit -m "add "

git log

git checkout master

git commit -m "AA"

git checkout develop

git log

--- 

# merge  충돌 해결 

git checkout master 

git merge develop

# 코드 수정 후 저장 

git add .

git commit  -m "AA"

git merge develop 

git branche -d develop 

--- 

# 원격 저장소 관리 

git remote

git remote show origin

git remote add test https://github.com/jiman94/tdd.git

git remote -v

git remote rename test temp 

git log  origin/master

git remote rm temp

git remote -v

---
## 로그 다루기 

git log

git log --stat

git log graph

git log -p -3
git log --pretty=oneline
git log --pretty=formant:"%h -> %an, %ar : %s" --graph

git archive --format=zip master -o Master.zip 

git archive --format=zip master -o ../Master.zip 

---
## git rebase 

git add .
git commit -m "aa"

git add . 
git commit -m "bb"

# commit 메시지 수정 
git rebase -i HEAD~3 

git reabse -i 특정커밋 

git log

git rebase -i HEAD~3 

drop 

git log 

--- 

git config --list
user.name=
user.email=

git config --global user.name "test"
git config --global user.email "test@gmail.com"


cat .gitconfig 
[user]
name = 
email = 

git config --global core.editor vi 
git config --list


.git/config 
[core]

git config user.name "test3"
git config --global --list

GIT_COMMITER_DATE="Oct 1 10:00 2019 +0000" git commit --amend -no-edit --date "Oct 1 10:00:00 2019 +0000"

git rebase --continue 

git filter-branch -f --env-filter 




















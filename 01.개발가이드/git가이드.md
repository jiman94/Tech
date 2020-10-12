git checkout develop # 이 시점 sha1 값이 ABC123 이라고 해보겠음
git checkout -b feature/foo
# 뭔가 졸라열심히 작업을 한 다음
git add .
git commit
git checkout develop
git merge feature/foo
git checkout feature/foo
git merge develop
git checkout develop
git reset --hard ABC123

git reset --hard <기억해둔shar1>


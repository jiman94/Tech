# Windows 전용 패키지 매니저 Chocolatey 설치 및 사용하기

```bash
@powershell -NoProfile -ExecutionPolicy Bypass -Command "iex ((new-object net.webclient).DownloadString('https://chocolatey.org/install.ps1'))" && SET PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin
```

```bash 
choco upgrade chocolatey

```

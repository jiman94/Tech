# SK에너지 CCP 개발가이드 
* https://github.com/Azure/azure-sdk-for-java/tree/master/sdk/spring

Active Directory Domain Services ( AD DS )
Azure Active Directory ( AAD )





https://github.com/Azure/azure-libraries-for-java/blob/master/AUTH.md

az ad sp create-for-rbac --sdk-auth > my.azureauth
Azure azure = Azure.authenticate(new File("my.azureauth")).withDefaultSubscription();


git clone https://github.com/Azure-Samples/cdn-java-manage-cdn.git



1. 네트워크
* Azure DNS 영역 및 레코드 만들기
    -  https://docs.microsoft.com/ko-kr/azure/dns/dns-getstarted-portal

2. 보안
* JWT 관련 가이드

* MSAL JWT msal-obo-sample
    - https://github.com/AzureAD/microsoft-authentication-library-for-java.git


* ms-identity-java-webapp
    - https://github.com/Azure-Samples/ms-identity-java-webapp.git

3. Storage
* Azure Blob Storage 사용 가이드
    - https://docs.microsoft.com/ko-kr/azure/storage/common/storage-samples-java?toc=/azure/storage/blobs/toc.json
    - https://github.com/Azure/azure-sdk-for-java/tree/master/sdk/storage/azure-storage-blob/src/samples/java/com/azure/storage/blob

4. Compute
* VM생성 가이드 
    - https://docs.microsoft.com/ko-kr/azure/virtual-machines/windows/quick-create-portal

* 디스크 스토리지 확장 가이드
* Windows
    - https://docs.microsoft.com/ko-kr/azure/virtual-machines/windows/expand-os-disk
* Linux 
    - https://docs.microsoft.com/ko-kr/azure/virtual-machines/linux/expand-disks

5. 컨테이너
* Auto-Scaling 가이드
    - https://docs.microsoft.com/ko-kr/azure/aks/cluster-autoscaler

6. 데이터베이스
* Azure PaaS DB 앞단에 배치되는 3rd-Party 솔루션 검토는 프로젝트 수행사에서 진행 필요
* Transparent Data Encryption (TDE)는 SQL 서버만 지원 
    - https://docs.microsoft.com/ko-kr/sql/relational-databases/security/encryption/transparent-data-encryption

7. CDN
* Azure CDN 생성 가이드
    - https://docs.microsoft.com/ko-kr/azure/cdn/cdn-create-new-endpoint

8. sample code search 
- https://docs.microsoft.com/ko-kr/samples/browse/?languages=java&products=azure

9. 무료 인증서 적용 
- https://docs.microsoft.com/ko-kr/azure/app-service/configure-ssl-certificate#create-a-free-certificate-preview



git clone https://github.com/Azure-Samples/ms-identity-java-webapi.git

Microsoft Authentication Library (MSAL) 

#### Azure Active Directory & Spring Boot Starter

https://docs.microsoft.com/ko-kr/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory
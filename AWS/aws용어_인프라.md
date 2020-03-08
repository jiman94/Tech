
기존 인프라들이 AWS용어로 어떻게 되는지 비교해보자.

1. 네트워크는  VPC라 불린다.
2. 보안장비는 SECURITY GROUP이라 불린다.
3. L4는 ELB라 부른다.
4. 서버는 EC2라 부른다.
5. DNS는 ROUTE53이라 부른다.
6. DB는 RDS를 보통 많이 사용한다. RDS는 관계형 데이터베이스로 Mysql , Oracle 등을 이야기한다.
7. 스토리지는 S3를 많이 사용한다.
8. CDN은 CLOUD FRONT라 한다. 


1. 네트워크  = VPC (Virtual Private Cloud)
네트워크 할당할 때 사용한다.

2. 보안장비/방화벽 Firewall = Security group (시큐리티 그룹)
인터넷에서 네트워크를 차단, 허용하는 데 사용한다.

3.  L4  = ELB(Elastic Load balancer) = 로드 밸런서  = 서버 이중화 장비.
서버 이중화를 위해 부하 분산 시 사용한다.

4. 서버  = EC2 (Elastic Compute Cloud)

5.  DNS = ROUTE 53  
도메인을 관리하고 글로벌 이중화 서비스가 되도록
GSLB기능도 제공한다.

6. DB = RDS (관계형 데이터 배이스 서비스 Mysql, Oracle)와 NoSQL

7. 스토리지(NAS)  = S3 (Simple Storage Service) = 오브젝트 스토리지.
이미지, 동영상, MP3와 같은 콘텐츠 저장 서비스.
NAS와 같은 역할.

8. CDN(Contents Delivery Network)  = CloudFront (클라우드 프런트), 대규모 파일/이미지 서비스.

9. EBS (Elastic Block Store) = 블록 스토리지로  하드디스크이다.  보통 데이터 DB 영역에 사용한다.

10. AMI(Amazon Machine Image) = OS, 리눅스와 윈도 운영체제의 서버 이미지

11. Public IP => 변경되는 공인 IP
외부에서 접속 가능하나 AWS 인스턴스 시작할 때 임의 IP로 할당된다. 변경이 되는 ip.
인터넷 서비스 목적이라면 변경이 되지 않는 EIP를 할당받아 사용하도록 한다.

12. Elastic IP (EIP) => Public IP이나 한번 할당되면 변하지 않는 공인 IP이다.
고정 공인 IP
인터넷 서비스할 때 EIP를 사용한다.
DNS에 매칭 한다.

13. 인터넷 게이트웨이(IGW) = 인터넷으로 나가기 위한 통로.  
VPC는 폐쇄된 네트워크인데  외부와 통신하기 위해서  
인터넷 게이트웨이(IGW)를 통해 통신한다.

14. Routes = 라우팅. 통신 경로를 잡아주는 것이다.


15. 다이렉트 커넥트 = 전용선

https://aws.amazon.com/ko/directconnect/partners/



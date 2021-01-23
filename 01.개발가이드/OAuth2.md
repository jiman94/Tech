

### Authentication(인증) & Authorization(허가)

#### OAuth2 역할은 크게 4가지로 분류된다
1. 자원 소유자 (Resource Owner)

정보의 소유권을 가진 사용자입니다.

2. 자원 서버 (Resource Server)

자원 소유자(Resource Owner)의 정보를 보관하고 있는 서버입니다.
구글, 페이스북, 네이버, 카카오 등이 있습니다.

3. 인가 서버 (Authorization Server)

자원 소유자 (Resource Owner) 을 인증합니다.
Client 에게 Access token 을 발행합니다.

4. 클라이언트 (Client)

제 3자 어플리케이션을 의미합니다.
이는 사용자 동의하에 Resource Server 에 사용자의 특정 정보를 요청 할 수 있습니다.


### Authorization Server 로 부터 발급된 랜덤한 문자열입니다. 위 이미지에서 4번 단계에 열쇠 모양으로 표시되고 있으며 토큰은 2가지 종류가 있습니다.

1. Access Token

Client 가 Resource Server 에게 사용자 정보를 요청하기 위한 입장권 같은 것입니다.
입장권에는 유효기간이 있습니다. 각 provider 마다 다릅니다.
유효 기간이 지나면 더 이상 이 토큰을 사용 할 수 없습니다.

3. Refresh Token

위 Access Token 이 유효기간이 만료 되면, 새로운 Access Token 을 발급 받기 위해 필요한 토큰입니다.
이 토큰에도 유효기간이 있습니다. 각 provider 마다 다릅니다. Access Token 보다는 유효기간이 훨씬 깁니다.

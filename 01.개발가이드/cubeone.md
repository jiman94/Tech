# CubeOneAPI 

```java
	/** CubeOne 암호화 Util **/
	private final CubeOneUtil cubeOneUtil;


		List<Club> clubMbshList = this.clubService.selectClubMbshList(paramClub);
		for(int i=0; i< clubMbshList.size(); i++) {
			clubMbshList.get(i).setMoblNo(MaskUtil.getMaskString(cubeOneUtil.decrypt(clubMbshList.get(i).getMoblNo(), Const.Cubeone.ITEM_PHONE), MaskType.CELLPHONE));
			clubMbshList.get(i).setMbshFulnm(MaskUtil.getMaskString(clubMbshList.get(i).getMbshFulnm(), MaskType.NAME));
		}

        mobileNo = cubeOneUtil.decrypt(StringUtil.nvl(intgMbsh.getMoblNo()), Const.Cubeone.ITEM_PHONE);

      	o2oCustInf.setHm1Addr(cubeOneUtil.encrypt(zipAddr, Const.Cubeone.ITEM_ADDRESS));
        o2oCustInf.setHm2Addr(cubeOneUtil.encrypt(dtlAddr, Const.Cubeone.ITEM_ADDRESS));
        o2oCustInf.setPntCardno(cubeOneUtil.encrypt(mbscdNo, Const.Cubeone.ITEM_CARD));
        o2oCustInf.setCustEmail(cubeOneUtil.encrypt(eml, Const.Cubeone.ITEM_MAIL));
        o2oCustInf.setMobileTelno(cubeOneUtil.encrypt(moblNo, Const.Cubeone.ITEM_PHONE));

        // 세션 셋팅
        Map<String, Object> sessionMap = new HashMap();
        sessionMap.put("eml", cubeOneUtil.decrypt(loginInfo.getEml(), Const.Cubeone.ITEM_MAIL));
        sessionMap.put("mbscd_no", cubeOneUtil.decrypt(loginInfo.getMbscdNo(), Const.Cubeone.ITEM_CARD));
        sessionMap.put("mobl_no", cubeOneUtil.decrypt(loginInfo.getMoblNo(), Const.Cubeone.ITEM_PHONE));
        sessionMap.put("mbsh_cust_id", cubeOneUtil.decrypt(loginInfo.getMbscdNo(), Const.Cubeone.ITEM_CARD));

        
```


```yaml 

cubeone:
  dev.url.encrypt: http://localhost:28080/cubeone/encrypt?plain={0}&item={1}
  dev.url.decrypt: http://localhost:28080/cubeone/decrypt?cryptogram={0}&item={1}

```

```java

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cubeone.CubeOneAPI;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : 공통 업무</li>
 * <li>서브 업무명 : CubeOne 암호화</li>
 * <li>설     명 : Application 실행시 최초 한번만 실행되는 초기화 함수</li>
 * <li>작  성  자 :  </li>
 * <li>작  성  일 :  </li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/
@Component
public class CubeOneInit implements CommandLineRunner {

    /**************************************************************
     * CubeOne 초기화
     *
     * @param args application 파라미터
     * @throws Exception Exception
     **************************************************************/
    @Override
    public void run(String... args) throws Exception {
        String activeProfile = System.getProperty("spring.profiles.active");

        if (!"dev".equals(activeProfile)) {
            CubeOneAPI.coinit("API");
        }
    }

}

```

```java

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.chicor.api.member.common.util.RestTemplateUtil;
import com.chicor.api.member.common.util.StringUtil;

import com.cubeone.CubeOneAPI;

import lombok.extern.slf4j.Slf4j;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : 공통 업무</li>
 * <li>서브 업무명 : CubeOne 암호화</li>
 * <li>설	 명 : profiles 에 따른 암/복호화 함수</li>
 * <li>작  성  자 : </li>
 * <li>작  성  일 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/
@Component
@Slf4j
public class CubeOneUtil {

	/** 큐브원 개발용 암호화 url */
	@Value("${cubeone.dev.url.encrypt}")
	String cubeoneDevUrlEncrypt;

	/** 큐브원 개발용 복호화 url */
	@Value("${cubeone.dev.url.decrypt}")
	String cubeoneDevUrlDecrypt;

	/**************************************************************
	 * 암호화
	 *
	 * @param plain 평문
	 * @param item 암호화 구분 값
	 * @return String
	 **************************************************************/
	public String encrypt(String plain, String item) {
		String activeProfile = System.getProperty("spring.profiles.active");
		String cryptogram = "";

		if(plain == null || plain.isEmpty()) {
			return cryptogram;
		}

		if ("dev".equals(activeProfile)) {
			// restAPI 호출
			HashMap result = RestTemplateUtil.connecterSendGET(StringUtil.getArgumentsString(cubeoneDevUrlEncrypt, new String[] {plain, item})
						, HashMap.class
						, null
						, MediaType.APPLICATION_JSON
						, MediaType.APPLICATION_JSON_VALUE
						, null);
			if("00000".contentEquals(StringUtil.nvl(result.get("resultMessage")))) {
				cryptogram = StringUtil.nvl(result.get("cryptogram"));
			}
		} else {
			// 암호화
			byte[] resultCode = new byte[5];
			cryptogram = CubeOneAPI.coencchar(plain, item, 10, null, null, resultCode);
		}

		return cryptogram;
	}

	/**************************************************************
	 * 복호화
	 *
	 * @param cryptogram 암호문
	 * @param item 암호화 구분 값
	 * @return String
	 **************************************************************/
	public String decrypt(String cryptogram, String item) {
		String activeProfile = System.getProperty("spring.profiles.active");
		String plain = "";

		if(cryptogram == null || cryptogram.isEmpty()) {
			return plain;
		}

		if ("dev".equals(activeProfile)) {
			// restAPI 호출
			HashMap result = RestTemplateUtil.connecterSendGET(StringUtil.getArgumentsString(cubeoneDevUrlDecrypt, new String[] {cryptogram, item})
					, HashMap.class
					, null
					, MediaType.APPLICATION_JSON
					, MediaType.APPLICATION_JSON_VALUE
					, null);
			if("00000".contentEquals(StringUtil.nvl(result.get("resultMessage")))) {
				plain = StringUtil.nvl(result.get("plain"));
			}
		} else {
			// 복호화
			byte[] resultCode = new byte[5];
			plain = CubeOneAPI.codecchar(cryptogram, item, 10, null, null, resultCode);
		}

		return plain;
	}

}
```
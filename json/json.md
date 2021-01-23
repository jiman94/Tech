
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/api/kakao")
public class KakaoController {

 

    private JsonNode getUserInfo(String accessToken) throws IOException {
        log.debug("accessToken : {}", accessToken);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSON()
                .setHeader("Authorization", "Bearer " + accessToken)
                .build();

        String response = restTemplate.postForObject(USER_INFO_URI, request, String.class);
        log.debug("request : {}", request);
        log.debug("response : {}", response);

        JsonNode userInfo = mapper.readTree(response);
        log.debug("UserInfo : {}", userInfo);
        return userInfo;
    }
}




import riverway.web.support.HtmlFormDataBuilder;

    @GetMapping("/{orderId}/approve")
    public String approve(@PathVariable Long orderId, HttpSession session, String pg_token) {
        PaymentDto payment = paymentService.getPayment(orderId);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSONKakao()
                .setHeader("Authorization", "KakaoAK " + ADMIN_KEY)
                .addParameter("cid", "TC0ONETIME")
                .addParameter("tid", session.getAttribute("tid"))
                .addParameter("partner_order_id", payment.getOrderId())
                .addParameter("partner_user_id", payment.getUsername())
                .addParameter("pg_token", pg_token)
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity(PAYMENT_APPROVE_URI, request, String.class);
        mailService.send("rlfghksop@naver.com", "결제완료", payment.getItemName());
        log.debug("Success : {}", response);
        return "redirect:/";
    }



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

public class HtmlFormDataBuilder {
    private HttpHeaders headers;
    private MultiValueMap<String, Object> params;

    private HtmlFormDataBuilder(HttpHeaders headers) {
        this.headers = headers;
        this.params = new LinkedMultiValueMap<>();
    }

    public HtmlFormDataBuilder addParameter(String key, Long value) {
        this.params.add(key, value + "");
        return this;
    }

    public HtmlFormDataBuilder addParameter(String key, int value) {
        this.params.add(key, value + "");
        return this;
    }

    public HtmlFormDataBuilder addParameter(String key, Object value) {
        this.params.add(key, value);
        return this;
    }

    public HtmlFormDataBuilder put() {
        params.add("_method", "put");
        return this;
    }

    public HtmlFormDataBuilder delete() {
        params.add("_method", "delete");
        return this;
    }

    public HtmlFormDataBuilder setHeader(String key, String value) {
        headers.set(key, value);
        return this;
    }

    public HttpEntity<MultiValueMap<String, Object>> build() {
        return new HttpEntity<MultiValueMap<String, Object>>(params, headers);
    }

    public static HtmlFormDataBuilder urlEncodedForm() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HtmlFormDataBuilder(headers);
    }

    public static HtmlFormDataBuilder multipartFormData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HtmlFormDataBuilder(headers);
    }

    public static HtmlFormDataBuilder urlEncodeFormJSON() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HtmlFormDataBuilder(headers);
    }

    public static HtmlFormDataBuilder urlEncodeFormJSONKakao() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Content-Type" ,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        return new HtmlFormDataBuilder(headers);
    }
}
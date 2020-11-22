

```java
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {
    	
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "DNT,X-AUTH-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        if (serverWebExchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
            return Mono.empty();
        } else {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "DNT,X-AUTH-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
            return webFilterChain.filter(serverWebExchange);
        }
    }
}
```



#### X-Forwarded-For

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SessionFilter implements WebFilter {

	private PathPattern basePattern;

	private List<PathPattern> excludePatterns;

	public SessionFilter() {
		basePattern = new PathPatternParser().parse("/api/**");
		excludePatterns = new ArrayList<>();
		excludePatterns.add(new PathPatternParser().parse("/api/auth/sign*"));
		excludePatterns.add(new PathPatternParser().parse("/api/ping/**"));
	}

	@Override
	public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {

		ServerHttpRequest request = serverWebExchange.getRequest();
		log.info("{} : {} {}",
				request.getHeaders().getFirst("X-Forwarded-For") == null ? request.getRemoteAddress()
						: request.getHeaders().getFirst("X-Forwarded-For"),
				request.getMethodValue(), request.getURI().toString());

		if (basePattern.matches(request.getPath().pathWithinApplication()) && !excludePatterns.stream()
				.anyMatch(pathPattern -> pathPattern.matches(request.getPath().pathWithinApplication()))) {

			return serverWebExchange.getSession()
					.doOnNext(session -> Optional.ofNullable(session.getAttribute("user"))
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
									"Not found session, Please Login again.")))
					.then(webFilterChain.filter(serverWebExchange));

		} else {

			return webFilterChain.filter(serverWebExchange);
		}
	}
}
```

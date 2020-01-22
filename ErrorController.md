<p><strong>각 Spring MVC Exception 설명</strong></p>
<table>
<thead>
<tr>
<th>Exception</th>
<th>설명</th>
<th>응답코드</th>
</tr>
</thead>
<tbody>
<tr>
<td>HttpRequestMethodNotSupportedException</td>
<td>요청 경로는 있으나 지원하지 않는 Method인 경우 발생</td>
<td>405 - Method Not Allowed</td>
</tr>
<tr>
<td>HttpMediaTypeNotSupportedException</td>
<td>요청의 Content Type을 핸들러가 지원하지 않는 경우 발생</td>
<td>415 - Unsupported Media Type</td>
</tr>
<tr>
<td>HttpMediaTypeNotAcceptableException</td>
<td>핸들러가 Client가 요청한 Type으로 응답을 내려줄 수 없는 경우 발생</td>
<td>406 - Not Acceptable</td>
</tr>
<tr>
<td>MissingPathVariableException</td>
<td>핸들러가 URL에서 기대한 Path Variable을 찾지 못한 경우 발생</td>
<td>500 - Internal Server Error</td>
</tr>
<tr>
<td>MissingServletRequestParameterException</td>
<td>핸들러가 기대한 요청 Parameter를 찾지 못한 경우 발생</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>ServletRequestBindingException</td>
<td>복구 불가능한 치명적인 간주할 binding exception<br>Filter 등의 Servlet Resource에서 던지기 쉽도록 ServletException을 상속하고 있음</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>ConversionNotSupportedException</td>
<td>bean property로 요청 내용을 변경하기 위한<br><code>editor</code> 혹은 <code>converter</code>를 찾지 못한 경우 발생</td>
<td>500 - Internal Server Error</td>
</tr>
<tr>
<td>TypeMismatchException</td>
<td>bean property로 값을 변경할 때, 핸들러가 예상한 class로 변경할 수 없는 경우 발생</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>HttpMessageNotReadableException</td>
<td><code>HttpMessageConverter</code>에서 발생하며 <code>read</code> 메서드가 실패한 경우 발생</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>HttpMessageNotWritableException</td>
<td><code>HttpMessageConverter</code>에서 발생하며 <code>write</code> 메서드가 실패한 경우 발생</td>
<td>500 - Internal Server Error</td>
</tr>
<tr>
<td>MethodArgumentNotValidException</td>
<td><code>@Valid</code>가 붙은 파라미터에 대해 검증 실패시 발생</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>MissingServletRequestPartException</td>
<td><code>multipart/form-data</code> 요청의 일부가 손실(can’t be found)되었을 때 발생</td>
<td>400 - Bad Request</td>
</tr>
<tr>
<td>NoHandlerFoundException</td>
<td>Dispatcher Servlet에서 핸들러를 찾지 못한 경우 기본적으로 404 응답을 내리지만<br>Dispatcher Servlet의 <code>throwExceptionIfNoHandlerFound</code> 값이 true인 경우 해당 예외를 발생</td>
<td>404 - Not Found</td>
</tr>
<tr>
<td>AsyncRequestTimeoutException</td>
<td>비동기 요청의 응답시간이 초과될 때 발생</td>
<td>503 - Service Unavailable</td>
</tr>
</tbody>
</table>

<p><strong>GET /pilot - json 응답</strong></p>
<ul>
<li>Content-Type: application/json</li>
</ul>
<figure class="highlight json"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br></pre></td><td class="code"><pre><span class="line">&#123;</span><br><span class="line">  <span class="attr">"timestamp"</span>: <span class="string">"2020-01-15T21:48:44.447+0000"</span>,</span><br><span class="line">  <span class="attr">"status"</span>: <span class="number">404</span>,</span><br><span class="line">  <span class="attr">"error"</span>: <span class="string">"Not Found"</span>,</span><br><span class="line">  <span class="attr">"message"</span>: <span class="string">"No message available"</span>,</span><br><span class="line">  <span class="attr">"path"</span>: <span class="string">"/pilot"</span></span><br><span class="line">&#125;</span><br></pre></td></tr></table></figure>
<p>위에서 살펴본 것과 같이 별다른 설정 없이 spring boot에서 웹 어플리케이션을 실행하면 기본적으로 오류 처리가 되고 있음을 알 수 있다.<br>그렇다면 어떠한 설정으로 spring boot에서 오류를 처리하는지 먼저 spring boot의 오류 처리에 대한 properties를 살펴보자.</p>
<p><strong>Spring Boot의 기본 오류 처리 properties</strong></p>
<figure class="highlight yaml"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br></pre></td><td class="code"><pre><span class="line"><span class="comment"># spring boot의 기본 properties</span></span><br><span class="line"><span class="string">server.error:</span></span><br><span class="line"><span class="attr">  include-exception:</span> <span class="literal">false</span></span><br><span class="line"><span class="attr">  include-stacktrace:</span> <span class="string">never</span> <span class="comment"># 오류 응답에 stacktrace 내용을 포함할 지 여부</span></span><br><span class="line"><span class="attr">  path:</span> <span class="string">'/error'</span> <span class="comment"># 오류 응답을 처리할 Handler의 경로</span></span><br><span class="line">  <span class="string">whitelabel.enabled:</span> <span class="literal">true</span> <span class="comment"># 서버 오류 발생시 브라우저에 보여줄 기본 페이지 생성 여부</span></span><br></pre></td></tr></table></figure>
<ul>
<li><code>server.error.include-exception</code> : 응답에 exception의 내용을 포함할지 여부</li>
<li><code>server.error.include-stacktrace</code> : 응답에 stacktrace 내용을 포함할지 여부</li>
<li><code>server.error.path</code> : 오류 응답을 처리할 핸들러(ErrorController)의 path</li>
<li><code>server.error.whitelabel.enabled</code> : 브라우저 요청에 대해 서버 오류시 기본으로 노출할 페이지를 사용할지 여부</li>
</ul>

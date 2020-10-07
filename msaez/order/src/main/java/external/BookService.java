
package .external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="bookInventory", url="http://bookInventory:8080")
public interface BookService {

    @RequestMapping(method= RequestMethod.GET, path="/books")
    public void queryBook(@RequestBody Book book);

}
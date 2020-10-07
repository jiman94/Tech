package ;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Custormer_table")
public class Custormer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;

    @PostPersist
    public void onPostPersist(){
        Registered registered = new Registered();
        BeanUtils.copyProperties(this, registered);
        registered.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        Changed changed = new Changed();
        BeanUtils.copyProperties(this, changed);
        changed.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        Deleted deleted = new Deleted();
        BeanUtils.copyProperties(this, deleted);
        deleted.publishAfterCommit();


    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }




}

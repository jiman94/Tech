package ;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Deliverable_table")
public class Deliverable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @PrePersist
    public void onPrePersist(){
        DeliveryPrepared deliveryPrepared = new DeliveryPrepared();
        BeanUtils.copyProperties(this, deliveryPrepared);
        deliveryPrepared.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}

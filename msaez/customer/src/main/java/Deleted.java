package ;

public class Deleted extends AbstractEvent {

    private Long customerId;

    public Deleted(){
        super();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

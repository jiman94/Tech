package ;

public class Registered extends AbstractEvent {

    private Long customerId;

    public Registered(){
        super();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

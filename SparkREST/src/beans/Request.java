package beans;

public class Request {
	private String deliverer;
	private Order order;
	private boolean accepted;
	
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(String deliverer, Order order, boolean accepted) {
		super();
		this.deliverer = deliverer;
		this.order = order;
		this.accepted = accepted;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}

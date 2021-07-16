package beans;

public class Request {
	private String deliverer;
	private Order order;
	
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(String deliverer, Order order) {
		super();
		this.deliverer = deliverer;
		this.order = order;
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
	
	
	
}

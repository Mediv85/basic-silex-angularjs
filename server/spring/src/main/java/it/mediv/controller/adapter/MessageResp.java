package it.mediv.controller.adapter;

public class MessageResp {
	private String responce;

	@Override
	public String toString() {
		return "MessageResp [responce=" + responce + "]";
	}

	public MessageResp() {
		super();
	}

	public MessageResp(String responce) {
		super();
		this.responce = responce;
	}

	public String getResponce() {
		return responce;
	}

	public void setResponce(String responce) {
		this.responce = responce;
	}

}

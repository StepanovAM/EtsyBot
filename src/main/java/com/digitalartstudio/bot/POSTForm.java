package com.digitalartstudio.bot;

public class POSTForm {
	
	private String listing_id;
	private String ref;
	private String listing_inventory_id;
	private String shipping_method_id;
	private int quantity;
	private String _nnc;
	public String getListing_id() {
		return listing_id;
	}
	public void setListing_id(String listing_id) {
		this.listing_id = listing_id;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getListing_inventory_id() {
		return listing_inventory_id;
	}
	public void setListing_inventory_id(String listing_inventory_id) {
		this.listing_inventory_id = listing_inventory_id;
	}
	public String getShipping_method_id() {
		return shipping_method_id;
	}
	public void setShipping_method_id(String shipping_method_id) {
		this.shipping_method_id = shipping_method_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String get_nnc() {
		return _nnc;
	}
	public void set_nnc(String _nnc) {
		this._nnc = _nnc;
	}
}

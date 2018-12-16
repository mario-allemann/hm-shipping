package fhnw.hmshipping.domain;

import org.hibernate.LockMode;

import java.util.Date;
import java.util.List;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name="shipping")
public class Shipping implements Serializable {

	
	public Shipping(Date estimatedDate, Integer orderId) {
		super();
		this.estimatedDate = estimatedDate;
		this.orderId = orderId;
	}

	@Column(name="trackingId", nullable=false)	
	@Id	
	@GeneratedValue(generator="SHIPPING_TRACKINGID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="SHIPPING_TRACKINGID_GENERATOR", strategy="native")	
	private int trackingId;
	
	@Column(name="estimatedDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date estimatedDate;
	
	@Column(name="effectiveDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date effectiveDate;
	
	@Column(name="orderId", nullable=true)	
	private Integer orderId;
	
	private void setTrackingId(int value) {
		this.trackingId = value;
	}
	
	public int getTrackingId() {
		return trackingId;
	}
	
	public int getORMID() {
		return getTrackingId();
	}
	
	public void setEstimatedDate(java.util.Date value) {
		this.estimatedDate = value;
	}
	
	public java.util.Date getEstimatedDate() {
		return estimatedDate;
	}
	
	public void setEffectiveDate(java.util.Date value) {
		this.effectiveDate = value;
	}
	
	public java.util.Date getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setOrderId(int value) {
		setOrderId(new Integer(value));
	}
	
	public void setOrderId(Integer value) {
		this.orderId = value;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public String toString() {
		return String.valueOf(getTrackingId());
	}
	
}

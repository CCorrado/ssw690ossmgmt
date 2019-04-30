package bao.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrderStatus {
	public long userid;
	public long orderid;
	public String status;
}
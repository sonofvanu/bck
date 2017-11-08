package com.stackroute.activitystream.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Circle {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int circleId;
	@NotEmpty @NotBlank
	private String circleName;
	private boolean circleStatus=true;
	@Temporal(TemporalType.DATE) @Column
	private Date createdOn;
	
	public int getCircleId() {
		return circleId;
	}
	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public boolean isCircleStatus() {
		return circleStatus;
	}
	public void setCircleStatus(boolean circleStatus) {
		this.circleStatus = circleStatus;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn() {
		this.createdOn = new Date();
	}
	

}

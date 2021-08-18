package com.service.pet.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pet")
public class Pet {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "UUID")
	private String UUID;

	@Column(name = "petname")
	private String petName;

	@Column(name = "age")
	private int age;

	@Column(name = "place")
	private String place;

	@Column(name = "available_buy")
	private Boolean isAvailableToBuy = true;

	@Column(name = "owned_by")
	private String ownedBy;

	@Column(name = "created_date")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updateDate;

	public Pet() {
	}

	public Pet(String uUID, String petName, int age, String place, Boolean isAvailableToBuy, String ownedBy,
			Date createDate, Date updateDate) {
		super();
		UUID = uUID;
		this.petName = petName;
		this.age = age;
		this.place = place;
		this.isAvailableToBuy = isAvailableToBuy;
		this.ownedBy = ownedBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Boolean getIsAvailableToBuy() {
		return isAvailableToBuy;
	}

	public void setIsAvailableToBuy(Boolean isAvailableToBuy) {
		this.isAvailableToBuy = isAvailableToBuy;
	}

	public String getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@PrePersist
	public void prePersist() {
		this.createDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
	}

	@Override
	public String toString() {
		return "Pet [UUID=" + UUID + ", petName=" + petName + ", age=" + age + ", place=" + place
				+ ", isAvailableToBuy=" + isAvailableToBuy + ", ownedBy=" + ownedBy + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}

package com.edwingamboa.busesApi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;

	private String motor;

	private String brakes;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "concessionaire_id")
	private Concessionaire consessionaire;

	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Device> device;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public Concessionaire getConsessionaire() {
		return consessionaire;
	}

	public void setConsessionaire(Concessionaire consessionaire) {
		this.consessionaire = consessionaire;
	}

}

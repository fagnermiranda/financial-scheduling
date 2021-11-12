package br.com.fagner.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_account")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name = "code")
    private Integer code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
}

package com.openclassrooms.ApiRentals.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    
    @JsonIgnore
    private String password;
    
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDateTime created_at;
    
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDateTime updated_at;
	

    // Constructeurs, getters et setters
    public UserDTO(Long id, String name, String email,String password, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
    
    @JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	 public void setPassword(String password) {
	        this.password = password;
	    }
}

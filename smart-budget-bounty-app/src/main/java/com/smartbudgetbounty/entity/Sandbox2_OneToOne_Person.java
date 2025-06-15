package com.smartbudgetbounty.entity;

import jakarta.persistence.*;

//Take note for this one to one r/s example, passport is the OWNER (w/ person_id, a foreign key to person table)
//-> In short,
//(1) Owning side: uses @JoinColumn, generates FK
//(2) Inverse side: uses mappedBy, reflects relationship -- no extra DB column
//(3) In one to one r/s, not both side need fk, only owner side. If not, there will be circular dependencies

@Entity
@Table(name= "s2_one_to_one_person")
public class Sandbox2_OneToOne_Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String name;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // mappedBy side is just the mirror and does not create a new FK column
	private Sandbox2_OneToOne_Passport passport;

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

	public Sandbox2_OneToOne_Passport getPassport() {
		return passport;
	}

	public void setPassport(Sandbox2_OneToOne_Passport passport) {
		this.passport = passport;
	}
	
	
}

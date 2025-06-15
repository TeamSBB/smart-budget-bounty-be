package com.smartbudgetbounty.entity;

import jakarta.persistence.*;


// Take note for this one to one r/s example, passport is the OWNER (w/ person_id, a foreign key to person table)
//-> In short,
//(1) Owning side: uses @JoinColumn, generates FK
//(2) Inverse side: uses mappedBy, reflects relationship -- no extra DB column
//(3) In one to one r/s, not both side need fk, only owner side. If not, there will be circular dependencies

@Entity
@Table(name= "s2_one_to_one_passport")
public class Sandbox2_OneToOne_Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String passportNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")					// The OWNER of the relationship, is the one that have this @JoinColumn and name is person_id
	private Sandbox2_OneToOne_Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Sandbox2_OneToOne_Person getPerson() {
		return person;
	}

	public void setPerson(Sandbox2_OneToOne_Person person) {
		this.person = person;
	}
	
	
}

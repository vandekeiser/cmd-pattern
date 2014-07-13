package cla.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	public Customer() {}
    public Customer(String name) { this.name = name; }

	@Id
    @GeneratedValue
    Long id;

    @Column//(nullable = false)
    String name;

}

package cla.domain.potatoes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Potato implements Serializable {

	public Potato() {}
    public Potato(String race) { this.race = race; }

	@Id
    @GeneratedValue
    Long id;

    @Column//(nullable = false)
    String race;

}

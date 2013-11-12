/**
 * 
 */
package com.wouldya.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONSerializer;

/**
 * @author ian
 *
 */
@Entity
@Configurable
public class Combination {
	
	public enum AgeBracket {
		TEEN,
		YOUNG_ADULT,
		MIDDLE_AGE,
		OLD		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "combinationId")
	private Long combinationId;
	
	private Long personOneId;
	
	private Long personTwoId;
	
	private AgeBracket ageBracket;

	public Long getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(Long combinationId) {
		this.combinationId = combinationId;
	}

	public Long getPersonOneId() {
		return personOneId;
	}

	public void setPersonOneId(Long personOneId) {
		this.personOneId = personOneId;
	}

	public Long getPersonTwoId() {
		return personTwoId;
	}

	public void setPersonTwoId(Long personTwoId) {
		this.personTwoId = personTwoId;
	}

	public AgeBracket getAgeBracket() {
		return ageBracket;
	}

	public void setAgeBracket(AgeBracket ageBracket) {
		this.ageBracket = ageBracket;
	}



	@PersistenceContext
    transient EntityManager entityManager;
	
	public static final EntityManager entityManager() {
        EntityManager em = new Person().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
	
	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
	
	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
	
	public static Combination findRandomEntry() {
        return entityManager().createQuery("SELECT o FROM Combination o ORDER BY RANDOM()", Combination.class).setMaxResults(1).getSingleResult();
    }
	
	public static Combination find(Long combinationId){
		if (combinationId == null) return null;
		return entityManager().find(Combination.class, combinationId);
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
	
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

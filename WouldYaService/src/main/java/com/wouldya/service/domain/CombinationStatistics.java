package com.wouldya.service.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;

@Entity
@Configurable
public class CombinationStatistics {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "combinationStatId")
	private Long combinationStatId;
	
	private Long combinationId;
	
	private Long personChosen;
	
	private Long personNotChosen;

	public Long getCombinationStatId() {
		return combinationStatId;
	}

	public void setCombinationStatId(Long combinationStatId) {
		this.combinationStatId = combinationStatId;
	}

	public Long getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(Long combinationId) {
		this.combinationId = combinationId;
	}

	public Long getPersonChosen() {
		return personChosen;
	}

	public void setPersonChosen(Long personChosen) {
		this.personChosen = personChosen;
	}

	public Long getPersonNotChosen() {
		return personNotChosen;
	}

	public void setPersonNotChosen(Long personNotChosen) {
		this.personNotChosen = personNotChosen;
	}
	
	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new CombinationStatistics().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
	
	public static long countStats() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CombinationStatistics o", Long.class).getSingleResult();
    }
	
	public static List<CombinationStatistics> getCombinationStatisticsByCombinationId(Long combinationId){
		TypedQuery<CombinationStatistics> query = entityManager().createNamedQuery("SELECT o FROM Combination_Statistics o where combination_Id == ?", CombinationStatistics.class);
		query.setParameter(1, combinationId);
		return query.getResultList();
	}
	
	public static Long countCombinationPersonChosen(Long combinationId, Long personId) {
		return entityManager().createQuery("SELECT COUNT(o) FROM CombinationStatistics o where combinationId = ? and personChosen = ?", Long.class)
				.setParameter(1,  combinationId)
				.setParameter(2, personId).getSingleResult();
    }
	
	public static Long countPersonChosen(Long personId) {
		return entityManager().createQuery("SELECT COUNT(o) FROM CombinationStatistics o where personChosen = ?", Long.class)
				.setParameter(1,  personId).getSingleResult();
    }
	
	public static Long countPersonTotal(Long personId) {
		return entityManager().createQuery("SELECT COUNT(o) FROM CombinationStatistics o where personChosen = ? or personNotChosen = ?", Long.class)
				.setParameter(1, personId)
				.setParameter(2, personId)
				.getSingleResult();
	}
	
	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
	
	@Transactional
    public CombinationStatistics merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CombinationStatistics merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
	
	public static CombinationStatistics fromJsonToCombinationStatistics(String json) {
        return new JSONDeserializer<CombinationStatistics>().use(null, CombinationStatistics.class).deserialize(json);
    }
	
}

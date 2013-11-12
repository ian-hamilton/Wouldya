/**
 * 
 */
package com.wouldya.service.domain;

import flexjson.JSONSerializer;

/**
 * @author ian
 *
 */
public class StatisticsSummary {
	
	private Long combinationId;
	
	private Person personOne;
	
	private Person personTwo;
	
	private Long personOneComboCountChosen;
	
	private Long personTwoComboCountChosen;
	
	private Long personOneTotalCountChosen;
	
	private Long personTwoTotalCountChosen;
	
	private Long personOneTotalCount;
	
	private Long personTwoTotalCount;

	

	public Long getCombinationId() {
		return combinationId;
	}



	public void setCombinationId(Long combinationId) {
		this.combinationId = combinationId;
	}



	public Person getPersonOne() {
		return personOne;
	}



	public void setPersonOne(Person personOne) {
		this.personOne = personOne;
	}



	public Person getPersonTwo() {
		return personTwo;
	}



	public void setPersonTwo(Person personTwo) {
		this.personTwo = personTwo;
	}



	public Long getPersonOneComboCountChosen() {
		return personOneComboCountChosen;
	}



	public void setPersonOneComboCountChosen(Long personOneComboCountChosen) {
		this.personOneComboCountChosen = personOneComboCountChosen;
	}



	public Long getPersonTwoComboCountChosen() {
		return personTwoComboCountChosen;
	}



	public void setPersonTwoComboCountChosen(Long personTwoComboCountChosen) {
		this.personTwoComboCountChosen = personTwoComboCountChosen;
	}



	public Long getPersonOneTotalCountChosen() {
		return personOneTotalCountChosen;
	}



	public void setPersonOneTotalCountChosen(Long personOneTotalCountChosen) {
		this.personOneTotalCountChosen = personOneTotalCountChosen;
	}



	public Long getPersonTwoTotalCountChosen() {
		return personTwoTotalCountChosen;
	}



	public void setPersonTwoTotalCountChosen(Long personTwoTotalCountChosen) {
		this.personTwoTotalCountChosen = personTwoTotalCountChosen;
	}



	public Long getPersonOneTotalCount() {
		return personOneTotalCount;
	}



	public void setPersonOneTotalCount(Long personOneTotalCount) {
		this.personOneTotalCount = personOneTotalCount;
	}



	public Long getPersonTwoTotalCount() {
		return personTwoTotalCount;
	}



	public void setPersonTwoTotalCount(Long personTwoTotalCount) {
		this.personTwoTotalCount = personTwoTotalCount;
	}



	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

}

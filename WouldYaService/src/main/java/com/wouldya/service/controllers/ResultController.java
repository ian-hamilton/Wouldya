/**
 * 
 */
package com.wouldya.service.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wouldya.service.domain.Combination;
import com.wouldya.service.domain.CombinationStatistics;
import com.wouldya.service.domain.Person;
import com.wouldya.service.domain.StatisticsSummary;

/**
 * @author ian
 *
 */
@Controller
@RequestMapping("/result")
public class ResultController {

	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> postFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        CombinationStatistics selection = CombinationStatistics.fromJsonToCombinationStatistics(json);
        selection.persist();
        System.out.println("Here");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "sum/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showSummedJson(@PathVariable("id") Long combinationId, @RequestParam("callback") String callback) {
    	Combination combo = Combination.find(combinationId);
    	StatisticsSummary statisticsSummary = new StatisticsSummary();
    	statisticsSummary.setPersonOne(Person.findPerson(combo.getPersonOneId()));
    	statisticsSummary.setPersonTwo(Person.findPerson(combo.getPersonTwoId()));
    	statisticsSummary.setPersonOneComboCountChosen(CombinationStatistics.countCombinationPersonChosen(combinationId, combo.getPersonOneId()));
    	statisticsSummary.setPersonTwoComboCountChosen(CombinationStatistics.countCombinationPersonChosen(combinationId, combo.getPersonTwoId()));
    	statisticsSummary.setPersonOneTotalCountChosen(CombinationStatistics.countPersonChosen(combo.getPersonOneId()));
    	statisticsSummary.setPersonTwoTotalCountChosen(CombinationStatistics.countPersonChosen(combo.getPersonTwoId()));
    	statisticsSummary.setPersonOneTotalCount(CombinationStatistics.countPersonTotal(combo.getPersonOneId()));
    	statisticsSummary.setPersonTwoTotalCount(CombinationStatistics.countPersonTotal(combo.getPersonTwoId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(callback + "(" + statisticsSummary.toJson() + ")", headers, HttpStatus.OK);
    }
	
}

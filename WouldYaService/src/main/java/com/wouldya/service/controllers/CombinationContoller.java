package com.wouldya.service.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wouldya.service.domain.Combination;

@Controller
@RequestMapping("/combination")
public class CombinationContoller {
	
    @RequestMapping(value = "/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id, @RequestParam("callback") String callback) {
        Combination combination = Combination.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (combination == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(callback + "(" + combination.toJson() + ")", headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/random", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> RandomshowJson(@RequestParam("callback") String callback) {
        Combination combination = Combination.findRandomEntry();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (combination == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(callback + "(" + combination.toJson() + ")", headers, HttpStatus.OK);
    }

}

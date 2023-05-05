package com.example.yallahride.Controller;


import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Service.Interface.RateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRate(@PathVariable Long id) {
        return new ResponseEntity<>(rateService.findRateById(id).get(), OK);
    }

    @PostMapping("/save_page_rate")
    public ResponseEntity<Rate> savePage(@Valid @RequestBody Rate Rate) {
        return new ResponseEntity<>(rateService.saveRate(Rate), CREATED);
    }

    @DeleteMapping("/delete_rate/{id}")
    public ResponseEntity<HttpStatus> deleteRate(@PathVariable Long id) {
        rateService.deleteRateById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rate>> getRates() {
        return new ResponseEntity<>(rateService.findAllRates(), HttpStatus.OK);
    }

    @GetMapping("/user_rates/{id}")
    public ResponseEntity<Collection<Rate>> getUserRates(@PathVariable Long id) {
        return new ResponseEntity<>(rateService.findUserRates(id), HttpStatus.OK);
    }

    @GetMapping("/user_rate/{id}")
    public ResponseEntity<Double> getUserRate(@PathVariable Long id) {
        return new ResponseEntity<>(rateService.getUserRateByUserId(id), HttpStatus.OK);
    }


    @DeleteMapping("/delete_all_rate")
    public ResponseEntity<HttpStatus> deleteAllRats() {
        rateService.deleteAllRates();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/number_of_rates")
    public ResponseEntity<Long> getNumberOfRate() {
        return new ResponseEntity<>(rateService.getNumberOfRate(), HttpStatus.OK);
    }
}

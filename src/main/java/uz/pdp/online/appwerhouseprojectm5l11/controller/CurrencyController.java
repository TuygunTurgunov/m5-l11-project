package uz.pdp.online.appwerhouseprojectm5l11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Currency;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result post(@RequestBody Currency currency){
        Result result = currencyService.addCurrency(currency);
        return  result;
    }

    @GetMapping
    public List<Currency> getAllCurrencyList(){
        List<Currency> allCurrency = currencyService.getAllCurrency();
        return allCurrency;
    }

    @GetMapping("/{id}")
    public Currency getOneCurrency(@PathVariable Integer id){

        Currency oneCurrency = currencyService.getOneCurrency(id);
        return oneCurrency;
    }
    @PutMapping("/{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currency){

        Result result = currencyService.editCurrency(id, currency);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        Result result = currencyService.deleteCurrency(id);
        return result;
    }




}

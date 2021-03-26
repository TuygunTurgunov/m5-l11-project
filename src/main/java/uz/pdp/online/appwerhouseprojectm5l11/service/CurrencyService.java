package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Currency;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addCurrency(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("This currency already exists", false);

        currencyRepository.save(currency);
        return new Result("Currency successfully saved", true);
    }

    public List<Currency> getAllCurrency() {
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList;
    }

    public Currency getOneCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return null;

        return optionalCurrency.get();
    }

    public Result editCurrency(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found by id", false);

        boolean b = currencyRepository.existsByName(currency.getName());
        if (b)
            return new Result("currency already exists",false);

        Currency editCurrency = optionalCurrency.get();
        editCurrency.setName(currency.getName());
        editCurrency.setActive(currency.getActive());
        currencyRepository.save(editCurrency);
        return new Result("Currency edited", true);
    }

    public Result deleteCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            Currency currency = optionalCurrency.get();
            currency.setActive(false);
            currencyRepository.save(currency);
            return new Result("Currency active is false",true);
        }
        return new Result("Currency not found by id",false);



    }


}
package com.example.IPRWCbackend.controllers;


import com.example.IPRWCbackend.daos.PromoCodeDao;
import com.example.IPRWCbackend.models.PromoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend_url}")
@RequestMapping("/api/promo-code")
public class PromoCodeController {

    private final PromoCodeDao promoCodeDao;

    @Autowired
    public PromoCodeController(PromoCodeDao promoCodeDao) {
        this.promoCodeDao = promoCodeDao;
    }

    @GetMapping
    public List<PromoCode> getPromoCodes() {
        return promoCodeDao.getPromoCodes();
    }

    @PostMapping
    public PromoCode addPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeDao.addPromoCode(promoCode);
    }

    @PutMapping
    public PromoCode updatePromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeDao.updatePromoCode(promoCode);
    }

    @DeleteMapping(path = "{id}")
    public void deletePromoCode(@PathVariable("id") String id) {
        promoCodeDao.deletePromoCode(id);
    }
}

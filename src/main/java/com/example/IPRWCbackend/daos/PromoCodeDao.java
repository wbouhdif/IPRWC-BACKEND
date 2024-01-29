package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.PromoCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;


@Component
@CrossOrigin(origins = "${frontend_url}")
public class PromoCodeDao {


    private final PromoCodeRepository promoCodeRepository;


    public PromoCodeDao(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }


    public List<PromoCode> getPromoCodes() {
        return promoCodeRepository.findAll();
    }

    public PromoCode addPromoCode(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    public PromoCode updatePromoCode(PromoCode promoCode) {
        promoCodeRepository.update(promoCode.getCode(), promoCode.getDiscount(), promoCode.isActive(), promoCode.getExpirationDate(), promoCode.getId());
        return promoCode;
    }

    public void deletePromoCode(String id) {
        promoCodeRepository.deleteById(UUID.fromString(id));
    }
}

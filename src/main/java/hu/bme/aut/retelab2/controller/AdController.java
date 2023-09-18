package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    /*@GetMapping
    public List<Ad> getAll(@RequestParam(required = false,defaultValue = "")String keyword){
        return keyword.equals("") ? adRepository.findAll() : adRepository.findByKeyword(keyword);

    }*/

    @GetMapping
    public List<Ad> getMinMax(@RequestParam(required = false,defaultValue = "0")int min,@RequestParam(required = false,defaultValue = "10000000")int max){
        List<Ad> temp =  adRepository.findByMinMax(min,max);
        for(int i = 0; i < temp.size();i++)
        {
            temp.get(i).setSecretCode(null);
        }
        return temp;
    }

    @PostMapping
    public Ad Save(@RequestBody Ad ad)
    {
        ad.setId(null);
        ad.setPublishDate();
        ad.setSecretCode(SecretGenerator.generate());
        return adRepository.save(ad);
    }

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad ad)
    {
        Ad a = adRepository.findWithSecretCode(ad);
        if (a == null)
            return new ResponseEntity<>(ad, HttpStatus.FORBIDDEN);
        a = adRepository.save(ad);
        return ResponseEntity.ok(a);
    }
}

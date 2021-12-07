package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {
    @Autowired
    ISmartphoneService smartphoneService;
@PostMapping
//@RequestBody thực hiện gán dữ liệu từ json nhận được vào các trường tương ứng của smartphone
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone smartphone){
    return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.CREATED);
}
@GetMapping("/list")
    public ModelAndView getAllSmartphonePage(){
    ModelAndView modelAndView = new ModelAndView("/phones/list");
    modelAndView.addObject("smartphoneList", smartphoneService.findAll());
    return modelAndView;
}
//Phương thức hiển thị danh sách sản phẩm
@GetMapping
    public ResponseEntity<Iterable<Smartphone>>allPhones(){
    return new ResponseEntity<>(smartphoneService.findAll(),HttpStatus.OK);
}

@DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> deleteSmartphone(@PathVariable Long id){
    Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
    if(!smartphoneOptional.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    smartphoneService.remove(id);
    return new ResponseEntity<>(smartphoneOptional.get(),HttpStatus.NO_CONTENT);
}

}


package com.alan.yx.springInAction.Chapter_09.thymeleaf.src.main.java.spittr.web;

import com.alan.yx.springInAction.Chapter_09.thymeleaf.src.main.java.spittr.Spitter;
import com.alan.yx.springInAction.Chapter_09.thymeleaf.src.main.java.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/spitter")
public class SpitterController {

  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }
  
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spitter());
    return "registerForm";
  }
  
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
      @Valid Spitter spitter, 
      Errors errors) {
    if (errors.hasErrors()) {
      return "registerForm";
    }
    
    spitterRepository.save(spitter);
    return "redirect:/spitter/" + spitter.getUsername();
  }
  
  @RequestMapping(value="/me", method=GET)
  public String me() {
    System.out.println("ME ME ME ME ME ME ME ME ME ME ME");
    return "home";
  }

  @RequestMapping(value="/{username}", method=GET)
  public String showSpitterProfile(@PathVariable String username, Model model) {
    Spitter spitter = spitterRepository.findByUsername(username);
    model.addAttribute(spitter);
    return "profile";
  }
  
}
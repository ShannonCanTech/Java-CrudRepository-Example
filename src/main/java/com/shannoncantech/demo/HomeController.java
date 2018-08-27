package com.shannoncantech.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    PersonRepsitory personRepsitory;

    @RequestMapping("/people")
    public String listPeople(Model model){
        model.addAttribute("people", personRepsitory.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        return "registrationform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Person person, BindingResult result){
        if(result.hasErrors()){
            return "registrationform";
        }
        personRepsitory.save(person);
        return "redirect:/people";
    }

    @RequestMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepsitory.findById(id));
        return "registrationform";
    }

    @RequestMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") long id){
        personRepsitory.deleteById(id);
        return "redirect:/people";
    }

    @RequestMapping("/details/{id}")
    public String showPersonInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepsitory.findById(id).get());
        return "person";
    }

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
}

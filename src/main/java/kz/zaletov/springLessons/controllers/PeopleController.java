package kz.zaletov.springLessons.controllers;

import kz.zaletov.springLessons.dao.PersonDao;
import kz.zaletov.springLessons.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("people", personDao.index());
        //получим всех людей из DAO и передадим на отображение в представление
        return "people/index";
    }
    @GetMapping("/{id}") //используем этот id из пути в @PathVariable
    public String show(@PathVariable("id") int id, Model model){
        //получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person",personDao.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("newPerson", new Person());
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDao.save(person);
        return "redirect:/people";
    }
}

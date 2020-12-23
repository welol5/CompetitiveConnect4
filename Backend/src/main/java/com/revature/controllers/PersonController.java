package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true", methods = { RequestMethod.GET,RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping(path="/users")
public class PersonController {
    private PersonService personServ;

    @Autowired
    public PersonController(PersonService p) {
        personServ = p;
    }

    @GetMapping
    public ResponseEntity<Person> checkLogin(HttpSession session) {
        Person loggedPerson = (Person) session.getAttribute("user");
        if (loggedPerson == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(loggedPerson);
    }

    @PutMapping
    public ResponseEntity<Person> logIn(HttpSession session, @RequestParam("user") String username,
                                        @RequestParam("pass") String password) {
        Person person = personServ.getPersonByUsername(username);
        if (person != null) {
            if (person.getPassword().equals(password)) {
                session.setAttribute("user", person);
                return ResponseEntity.ok(person);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(HttpSession session, @RequestParam("user") String username,
                                             @RequestParam("pass") String password) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        personServ.addPerson(person);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> logOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
    @PutMapping(path="/{id}")
    public ResponseEntity<Void> updateUser(HttpSession session, @PathVariable("id") Integer id,
                                           @RequestBody Person person) {
        Person loggedPerson = (Person) session.getAttribute("user");
        if (loggedPerson != null && loggedPerson.getId() == id) {
            personServ.updatePerson(person);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

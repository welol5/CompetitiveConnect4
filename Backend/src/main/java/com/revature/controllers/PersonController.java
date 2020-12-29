package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

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
        try {
            Person person = personServ.getPersonByUsername(username);
            if (person != null) {
                if (person.getPassword().equals(password)) {
                    session.setAttribute("user", person);
                    return ResponseEntity.ok(person);
                }
                return ResponseEntity.badRequest().build();
            }
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build(); //should never reach this
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
    @PostMapping(path="/picture/{username}")
    public ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file, @PathVariable("username") String username) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + username);
                if (!dir.exists())
                    dir.mkdirs();
//                System.out.println(file.getContentType().split("\\\\"));
                System.out.println(file.getContentType().split("/")[0]);
//                System.out.println(file.getContentType().split("\\\\").toString());

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + username + "." + file.getContentType().split("/")[1]);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println(serverFile.getAbsolutePath());
                return ResponseEntity.ok(serverFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else return ResponseEntity.badRequest().build();

        return null;
    }
}

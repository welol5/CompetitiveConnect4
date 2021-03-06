package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
                                             @RequestParam("pass") String password,
                                             @RequestParam("filepath")String filepath) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        person.setProfilePicFilePath(filepath);
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

    @GetMapping(path="/person/{id}")
    public ResponseEntity<Person> getPersonById(HttpSession session, @PathVariable("id") Integer id){
        Person person = personServ.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Person> retrievePersonProfile(HttpSession session, @PathVariable("id") Integer id,
    	                             	@RequestBody Person loggedPerson){
    	Person person = personServ.getPersonById(id);
    	if (person == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	else if (loggedPerson.getId() == id) return ResponseEntity.ok(loggedPerson);
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(path="/opponent/{id}")
    public ResponseEntity<Person> retrieveOpponentStats(HttpSession session, @PathVariable("id") Integer id,
    	                             	@RequestBody Person loggedPerson){
    	Person opponent = personServ.getPersonById(id);
    	if (opponent == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	else return ResponseEntity.ok(opponent);

    }
//file uploader is functional but not enough time to make everything right in the app

//    @PostMapping(path="/picture")
//    public ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
////                File dir = new File(PathToProject.path + File.separator + username);
//                if (!dir.exists())
//                    dir.mkdirs();
//
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + username + "." + file.getContentType().split("/")[1]);
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//                System.out.println(serverFile.getAbsolutePath());
//                return ResponseEntity.ok(serverFile.getAbsolutePath());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else return ResponseEntity.badRequest().build();
//
//        return null;
//    }
}

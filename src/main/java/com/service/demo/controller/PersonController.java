package com.service.demo.controller;



import com.service.demo.model.Person;
import com.service.demo.model.PersonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
//@RestController - inc RequestBody
public class PersonController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    private PersonStorage repo;

//    @GetMapping("/person")
//    public ModelAndView showForm() {
//        return new ModelAndView("personHome", "person", new Person());
//    }

    @PostMapping("/addPerson")
    public String addPerson(@ModelAttribute Person pers,
                                  BindingResult result,
                                  ModelMap model){
        if(result.hasErrors()){
            return "error";
        }
        Long id = repo.savePerson(pers);
        model.addAttribute("id", id);
        //model.addAttribute("success");
        //model.addAttribute("success");
        return "result";
    }

//    @GetMapping("/success")
//    public String success(Model model) {
//        return "result";
//    }

//    @RequestMapping(value = "{firstName}", method = RequestMethod.POST)
//    public String savePerson(@PathVariable("firstName") String firstName){
//        Long id = repo.saveNewPerson(firstName);
//        return "result";
//    }

    @RequestMapping( "/item" )
    public String getResult() {
        return "It's working...!";
    }


//    @RequestMapping("/greeting")
//    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return ("" + counter.incrementAndGet() + String.format(template, name));
//    }

    @RequestMapping("/greeting2")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name,
                                 ModelMap model){

        //model.addAttribute("success");
        //model.addAttribute("success");
        return "/result";
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value="name", defaultValue="World") String name,
                           ModelMap model) {

        model.addAttribute("msg", name);
        return "result" ;
       // return "hello";
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


//    @GetMapping("/displayAll")
//    //@Produces(MediaType.APPLICATION_JSON_VALUE)
//    public String displayAll(ModelMap model) {
//
//        Optional<List<Person>> collection = repo.returnPeople();
//
//        collection.get().stream().forEach(p -> System.out.print(p.toString()));
//        if(collection.isPresent()) {
//            List<Person> list = collection.get().stream().collect(Collectors.toList());
//            model.addAttribute("people", list);
//        } else {
//            model.addAttribute("result", "Nothing to display");
//        }
//
//        //return new ModelAndView("displayCollection" , model);
//        return "displayCollection";
//    }

    //JSON
    @GetMapping("/displayAll3")
    @ResponseBody
    //@Produces(MediaType.APPLICATION_JSON_VALUE)
    public Optional<List<Person>> displayAll3(Model model) {
        Optional<List<Person>> collection = repo.returnPeople();
        return collection;
    }

    @GetMapping("/findById")
    @ResponseBody
    //@Produces(MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView findById(@RequestParam(value="id", required = true) String id,
                                 ModelMap model) {
        Optional<Person> found = repo.findById(id);
        if(found.isPresent()){
            model.addAttribute(found);
        } else {
            model.addAttribute("result" , "Nothing to display");
        }

        return new ModelAndView("displayCollection" , model);
    }

    @GetMapping("/removePerson")
    @ResponseBody
    public ModelAndView removePerson(@RequestParam(value="id", required = true) String id,
                                 BindingResult error,
                                 ModelMap model) {
        if(error.hasErrors()){
            return  new ModelAndView("error", model);
        }
        boolean result = repo.removePerson(Long.parseLong(id));
        if(result){
            model.addAttribute("result" , "Succesfully Removed Person");
        } else {
            model.addAttribute("result" , "Unable to remove person");
        }

        return new ModelAndView("displayCollection" , model);
    }

}

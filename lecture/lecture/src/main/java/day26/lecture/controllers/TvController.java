package day26.lecture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import day26.lecture.services.TvService;

@Controller
@RequestMapping
public class TvController {

    @Autowired
    TvService tSrc;

    @GetMapping("/")
    public String getTvShowTypes(Model model) {
        model.addAttribute("types", tSrc.getAllTypes(null));
        return "index";
    }

    @GetMapping("/{type}")
    public String getTvShowsByType(@PathVariable(name = "type") String type, Model model) {

        System.out.println(tSrc.findAllTvShowsByType(type));

        model.addAttribute("shows", tSrc.findAllTvShowsByType(type));
        model.addAttribute("type", type);

        return "bytype";
    }

    @GetMapping("/shows/{id}")
    public String getTvShowById(@PathVariable(name = "id") int id, Model model) {

        model.addAttribute("show", tSrc.findShowById(id));
        return "showpage";
    }

    @GetMapping("/tv")
    public String getTvShow(@RequestParam(name = "lang", defaultValue = "English") String lang, Model model) {

        model.addAttribute("show", tSrc.findAllTvShowsByLang(lang));
        model.addAttribute("language", lang);
        return "result";

    }
    
}

package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String user(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return"newUser";
        } else {
            userService.add(user);
        }
        return "redirect:/";
    }

    @GetMapping("/change")
    public String change(ModelMap model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "change";
    }

    @PostMapping("/change/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return "change";
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @RequestMapping("/deleteUser")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}

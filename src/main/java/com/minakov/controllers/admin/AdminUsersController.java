package com.minakov.controllers.admin;

import com.minakov.dto.RoleChangingDto;
import com.minakov.entities.RoleEnum;
import com.minakov.entities.User;
import com.minakov.services.RoleService;
import com.minakov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(HttpSession session, Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users/users";
    }

    @GetMapping("/{id}")
    public String showDetails(HttpSession session, Model model, @PathVariable("id") Long userId) {
        if (!userService.existsById(userId)) {
            return "redirect:/admin/users";
        }
        model.addAttribute("usr", userService.getUserById(userId));
        return "admin/users/about";
    }

    @GetMapping("/{id}/role")
    public String showRoleChanging(HttpSession session, Model model, @PathVariable("id") Long userId){
        if (!userService.existsById(userId)) {
            return "redirect:/admin/users";
        }
        User user = userService.getUserById(userId);
        model.addAttribute("usr", user);
        model.addAttribute("roleDto", new RoleChangingDto(roleService.getRoleByName(user.getRole().getName()).getId()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/users/roleChanging";
    }

    @PatchMapping("/{id}/role")
    public String changeRole(HttpSession session, Model model, @PathVariable("id") Long userId,
                             @ModelAttribute("roleDto") RoleChangingDto roleChangingDto){
        userService.updateUserRole(userId, roleChangingDto);
        return "redirect:/admin/users/{id}";
    }
}

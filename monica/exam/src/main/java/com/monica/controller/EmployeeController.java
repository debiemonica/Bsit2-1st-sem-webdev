package com.monica.controller;

import com.monica.model.Employee;
import com.monica.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ✅ Root (/) and /employees → show index.html with employee list
    @GetMapping({"/", "/employees"})
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "index"; // must exist in templates/
    }

    // ✅ Show create form
    @GetMapping("/employees/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";
    }

    // ✅ Handle create
    @PostMapping("/employees")
    public String createEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }
        try {
            employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("email", "error.employee", "Email must be unique");
            return "create";
        }
        return "redirect:/employees";
    }

    // ✅ Show edit form
    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
        model.addAttribute("employee", e);
        return "edit";
    }

    // ✅ Handle update
    @PostMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        try {
            employee.setId(id);
            employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("email", "error.employee", "Email must be unique");
            return "edit";
        }
        return "redirect:/employees";
    }

    // ✅ Handle delete
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
        employeeRepository.delete(e);
        return "redirect:/employees";
    }

    // ✅ Serve login.html (needed for SecurityConfig)
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

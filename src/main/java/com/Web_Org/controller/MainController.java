package com.Web_Org.controller;

import com.Web_Org.model.Department;
import com.Web_Org.service.DepService;
import com.Web_Org.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


//--------------------------------------------------
//          Контроллер основной страницы
//-------------------------------------------------
@Controller
public class MainController {

    // Сотрудники
    @Autowired
    private EmpService empservice;

    // Отделы
    @Autowired
    private DepService depService;

    // Главная страница
    @GetMapping("/")
    public String main_page(Model model){

        // Список всех сотрудников
        model.addAttribute("depList", depService.getAllDepartments());
        return "home";
    }
}

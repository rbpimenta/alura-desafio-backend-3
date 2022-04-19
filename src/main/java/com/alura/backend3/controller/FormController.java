package com.alura.backend3.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/form")
@Slf4j
public class FormController {
    
    @GetMapping("")
    public String listForm(HttpServletRequest request) {
        return "form-list";
    }
    
    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        log.info("Filename: {}, Size: {}", file.getOriginalFilename(), FileUtils.byteCountToDisplaySize(file.getSize()));
        
        redirectAttributes.addFlashAttribute("message", "form.error");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (file.isEmpty()) {
            return "redirect:/form";
        }
        redirectAttributes.addFlashAttribute("message", "form.success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/form";
    }
}

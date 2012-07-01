package com.gordondickens.manny.web;

import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.service.BundleService;
import com.gordondickens.manny.service.JarDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RequestMapping("/jardirectorys")
@Controller
public class JarDirectoryController {

    @Autowired
    BundleService bundleService;
    @Autowired
    JarDirectoryService jarDirectoryService;

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException ignored) {
        }
        return pathSegment;
    }

    void populateEditForm(Model uiModel, JarDirectory jarDirectory) {
        uiModel.addAttribute("jarDirectory", jarDirectory);
        uiModel.addAttribute("bundles", bundleService.findAllBundles());
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JarDirectory jarDirectory = jarDirectoryService.findJarDirectory(id);
        jarDirectoryService.deleteJarDirectory(jarDirectory);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jardirectorys";
    }

    // UPDATE - FORM PAGE
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, jarDirectoryService.findJarDirectory(id));
        return "jardirectorys/update";
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JarDirectory jarDirectory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, jarDirectory);
            return "jardirectorys/update";
        }
        uiModel.asMap().clear();
        jarDirectoryService.updateJarDirectory(jarDirectory);
        return "redirect:/jardirectorys/" + encodeUrlPathSegment(jarDirectory.getId().toString(), httpServletRequest);
    }


    // LIST
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size;
            final int firstResult = page == null ? 0 : (page - 1) * sizeNo;
            uiModel.addAttribute("jardirectorys", jarDirectoryService.findJarDirectoryEntries(firstResult, sizeNo));
            float nrOfPages = (float) jarDirectoryService.countAllJarDirectorys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jardirectorys", jarDirectoryService.findAllJarDirectorys());
        }
        return "jardirectorys/list";
    }

    // SHOW
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("jardirectory", jarDirectoryService.findJarDirectory(id));
        uiModel.addAttribute("itemId", id);
        return "jardirectorys/show";
    }

    // CREATE FORM
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new JarDirectory());
        return "jardirectorys/create";
    }

    // CREATE NEW
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JarDirectory jarDirectory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, jarDirectory);
            return "jardirectorys/create";
        }
        uiModel.asMap().clear();
        jarDirectoryService.saveJarDirectory(jarDirectory);
        return "redirect:/jardirectorys/" + encodeUrlPathSegment(jarDirectory.getId().toString(), httpServletRequest);
    }
}

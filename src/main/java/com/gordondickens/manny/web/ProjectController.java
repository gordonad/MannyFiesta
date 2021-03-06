package com.gordondickens.manny.web;

import com.gordondickens.manny.domain.Project;
import com.gordondickens.manny.service.JarDirectoryService;
import com.gordondickens.manny.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@RequestMapping("/projects")
@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    JarDirectoryService jarDirectoryService;

    @Value("${pagination_records_per_page}")
    String maxRecordsPerPage = "25";

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

    void populateEditForm(Model uiModel, Project project) {
        uiModel.addAttribute("project", project);
        uiModel.addAttribute("jarDirectories", jarDirectoryService.findAllJarDirectories());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Project project = projectService.findProject(id);
        projectService.deleteProject(project);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? maxRecordsPerPage : size.toString());
        return "redirect:/projects";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, projectService.findProject(id));
        return "projects/update";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Project project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, project);
            return "projects/update";
        }
        uiModel.asMap().clear();
        projectService.updateProject(project);
        return "redirect:/projects/" + encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? Integer.parseInt(maxRecordsPerPage) : size;
            final int firstResult = page == null ? 0 : (page - 1) * sizeNo;
            uiModel.addAttribute("projects", projectService.findProjectEntries(firstResult, sizeNo));
            float nrOfPages = (float) projectService.countAllProjects() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("projects", projectService.findAllProjects());
        }
        return "projects/list";
    }


    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("project", projectService.findProject(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("jarDirectories", projectService.findJarDirectories(id));
        return "projects/show";
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Project());
        return "projects/create";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Project project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, project);
            return "projects/create";
        }
        uiModel.asMap().clear();
        projectService.saveProject(project);
        return "redirect:/projects/" + encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
    }
}

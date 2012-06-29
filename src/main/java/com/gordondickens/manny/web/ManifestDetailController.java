package com.gordondickens.manny.web;

import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.service.ManifestDetailService;
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

@RequestMapping("/manifestdetails")
@Controller
public class ManifestDetailController {
    @Autowired
    ManifestDetailService manifestDetailService;

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    void populateEditForm(Model uiModel, ManifestDetail manifestDetail) {
        uiModel.addAttribute("manifestDetail", manifestDetail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ManifestDetail manifestDetail = manifestDetailService.findManifestDetail(id);
        manifestDetailService.deleteManifestDetail(manifestDetail);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/manifestdetails";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, manifestDetailService.findManifestDetail(id));
        return "manifestdetails/update";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid ManifestDetail manifestDetail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, manifestDetail);
            return "manifestdetails/update";
        }
        uiModel.asMap().clear();
        manifestDetailService.updateManifestDetail(manifestDetail);
        return "redirect:/manifestdetails/" + encodeUrlPathSegment(manifestDetail.getId().toString(), httpServletRequest);
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("manifestdetails", manifestDetailService.findManifestDetailEntries(firstResult, sizeNo));
            float nrOfPages = (float) manifestDetailService.countAllManifestDetails() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("manifestdetails", manifestDetailService.findAllManifestDetails());
        }
        return "manifestdetails/list";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("manifestdetail", manifestDetailService.findManifestDetail(id));
        uiModel.addAttribute("itemId", id);
        return "manifestdetails/show";
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new ManifestDetail());
        return "manifestdetails/create";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid ManifestDetail manifestDetail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, manifestDetail);
            return "manifestdetails/create";
        }
        uiModel.asMap().clear();
        manifestDetailService.saveManifestDetail(manifestDetail);
        return "redirect:/manifestdetails/" + encodeUrlPathSegment(manifestDetail.getId().toString(), httpServletRequest);
    }
}

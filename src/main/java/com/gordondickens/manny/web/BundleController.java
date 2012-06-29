package com.gordondickens.manny.web;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.service.BundleService;
import com.gordondickens.manny.service.ManifestDetailService;
import com.gordondickens.manny.service.PkgService;
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

@RequestMapping("/bundles")
@Controller
public class BundleController {
    @Autowired
    PkgService pkgService;
    @Autowired
    ManifestDetailService manifestDetailService;
    @Autowired
    BundleService bundleService;

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

    void populateEditForm(Model uiModel, Bundle bundle) {
        uiModel.addAttribute("bundle", bundle);
        uiModel.addAttribute("manifestdetails", manifestDetailService.findAllManifestDetails());
        uiModel.addAttribute("pkgs", pkgService.findAllPkgs());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Bundle bundle = bundleService.findBundle(id);
        bundleService.deleteBundle(bundle);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/bundles";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, bundleService.findBundle(id));
        return "bundles/update";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Bundle bundle, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bundle);
            return "bundles/update";
        }
        uiModel.asMap().clear();
        bundleService.updateBundle(bundle);
        return "redirect:/bundles/" + encodeUrlPathSegment(bundle.getId().toString(), httpServletRequest);
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bundles", bundleService.findBundleEntries(firstResult, sizeNo));
            float nrOfPages = (float) bundleService.countAllBundles() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bundles", bundleService.findAllBundles());
        }
        return "bundles/list";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("bundle", bundleService.findBundle(id));
        uiModel.addAttribute("itemId", id);
        return "bundles/show";
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Bundle());
        return "bundles/create";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Bundle bundle, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bundle);
            return "bundles/create";
        }
        uiModel.asMap().clear();
        bundleService.saveBundle(bundle);
        return "redirect:/bundles/" + encodeUrlPathSegment(bundle.getId().toString(), httpServletRequest);
    }
}

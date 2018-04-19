package org.smartsupply.web;

import org.smartsupply.api.service.SetupService;
import org.smartsupply.model.enums.Setup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.theme.AbstractThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InstanceThemeResolver extends AbstractThemeResolver {

    @Autowired
    private SetupService setupService;

    @Override
    public String resolveThemeName(HttpServletRequest httpServletRequest) {
        Setup setup = setupService.setup();
        return setup != null ? setup.getPrefix() : Setup.UTAMU.getPrefix();
    }

    @Override
    public void setThemeName(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {

    }
}

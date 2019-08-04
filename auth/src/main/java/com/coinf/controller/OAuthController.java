package com.coinf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@SessionAttributes({"authorizationRequest"})
@Controller
public class OAuthController {

    @GetMapping("/oauth/confirm_access")
    public String getOAuthPage(Model model, HttpServletRequest request) {

        addRequestPathToModel(model, request);
        addCsrfTokenToModel(model, request);
        addClientIdToModel(model);

        Object o = model.containsAttribute("scopes") ? model.asMap().get("scopes") : request.getAttribute("scopes");
        Map scopes = o instanceof Map ? (Map)o : null;
        if (scopes != null && !scopes.isEmpty()) {
            log.warn("WARNING! Scopes were not empty but view has been rendered disregarding that.");
        }

        model.addAttribute("oauthForm", true);

        return "oauth";
    }

    private void addClientIdToModel(Model model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)model.asMap().get("authorizationRequest");
        String clientId = HtmlUtils.htmlEscape(authorizationRequest.getClientId());
        model.addAttribute("clientId", clientId);
    }

    private void addCsrfTokenToModel(Model model, HttpServletRequest request) {
        String csrfTokenParamName = null;
        String csrfTokenToken = null;
        CsrfToken csrfToken = (CsrfToken)(model.containsAttribute("_csrf") ? model.asMap().get("_csrf") : request.getAttribute("_csrf"));
        if (csrfToken != null) {
            csrfTokenParamName = HtmlUtils.htmlEscape(csrfToken.getParameterName());
            csrfTokenToken = HtmlUtils.htmlEscape(csrfToken.getToken());
        }
        model.addAttribute("csrfTokenParamName", csrfTokenParamName);
        model.addAttribute("csrfTokenToken", csrfTokenToken);
    }

    private void addRequestPathToModel(Model model, HttpServletRequest request) {
        String requestPath = ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
        requestPath = (requestPath == null ? "" : requestPath) + "/oauth/authorize";
        model.addAttribute("requestPath", requestPath);
    }

}

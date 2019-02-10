package com.coinf.controller;

import com.coinf.dto.Foo;
import com.coinf.util.AuthExtractionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class FooController {

    @Autowired
    private AuthExtractionUtil authExtractUtil;

    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
    @ResponseBody
    public Foo findById(@PathVariable final long id, Authentication auth) {
        return new Foo(id,"random: " + UUID.randomUUID() + ", guild: " + authExtractUtil.getGuild(auth) + ", name: " + authExtractUtil.getAuthName(auth));
    }

}
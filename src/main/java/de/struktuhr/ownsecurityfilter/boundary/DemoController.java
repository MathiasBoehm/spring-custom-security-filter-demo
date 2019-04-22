package de.struktuhr.ownsecurityfilter.boundary;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 */

@RestController
public class DemoController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "demo")
    public Map<String, Object> demo() {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("message", "Hello World!");
        data.put("timstamp", Instant.now());
        data.put("authentication", SecurityContextHolder.getContext().getAuthentication());

        return data;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "admin")
    public Map<String, Object> adminOnly() {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("adminonly", Boolean.TRUE);
        data.put("message", "Hello World!");
        data.put("timstamp", Instant.now());
        data.put("authentication", SecurityContextHolder.getContext().getAuthentication());

        return data;
    } 
}
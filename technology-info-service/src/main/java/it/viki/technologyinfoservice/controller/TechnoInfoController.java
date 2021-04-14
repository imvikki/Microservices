package it.viki.technologyinfoservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.viki.technologyinfoservice.interfaces.Technology;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technologies")
public class TechnoInfoController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/{technoId}")
    public Technology getTechnoInfo(@PathVariable final String technoId) {
        LOG.debug("Inside TechnoInfoController 2 !!!!!!!!");
        return new Technology(technoId, "Spring");
    }

}

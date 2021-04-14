package it.viki.employeeinfoservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import it.viki.employeeinfoservice.interfaces.EmployeeSkills;
import it.viki.employeeinfoservice.interfaces.Technology;
import it.viki.employeeinfoservice.interfaces.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeInfoController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{empId}")
    @HystrixCommand(fallbackMethod = "getFallback")
    public List<EmployeeSkills> getEmployee(@PathVariable("empId") final String empId) {
        LOG.debug("Inside EmployeeInfoController !!!!!!!!!");
        final UserRating userRating = restTemplate.getForObject("http://RatingInfoService/ratingsdata/employee/" + empId, UserRating.class);

        return userRating.getRatings().stream().map(rating -> {
            final Technology technology = restTemplate.getForObject("http://TechnologyInfoService/technologies/" + rating.getTechnoId(), Technology.class);

            /*final Technology technology = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/technologies/" + rating.getTechnoId())
                    .retrieve()
                    .bodyToMono(Technology.class)
                    .block();*/
            LOG.debug("Name :: ", technology.getTechnoName());
            return new EmployeeSkills(technology.getTechnoName(), "Spring", rating.getRating());
        })
                .collect(Collectors.toList());

        //return Collections.singletonList(new EmployeeSkills("viki", "Spring", "7"));
    }

    public List<EmployeeSkills> getFallback(@PathVariable("empId") final String empId) {
        return Arrays.asList(new EmployeeSkills("100", "", "0"));
    }
}

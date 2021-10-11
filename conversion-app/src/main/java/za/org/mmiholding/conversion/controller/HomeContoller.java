package za.org.mmiholding.conversion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
    public class HomeContoller {

    private static final Logger logger =  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()) ;

    @RequestMapping("/")
        public String index() {
            logger.info("In Home Controller block .............");
            return "Metric - Imperial conversion";
        }
    }
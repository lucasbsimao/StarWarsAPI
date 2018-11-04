package br.com.b2w.controllers.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/planet", produces = "application/json")
public interface IGenericController {

}

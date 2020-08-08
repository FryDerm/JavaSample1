package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/address")
public class AddressController {
  @Autowired 
  private AddressRepository addressRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewAddress (
    @RequestParam String street1, 
    @RequestParam(required = false) String street2,
    @RequestParam String city,
    @RequestParam String state,
    @RequestParam String zip) {

    Address a = new Address();
    a.setStreet1(street1);
    a.setStreet2(street2);
    a.setCity(city);
    a.setState(state);
    a.setZip(zip);
    addressRepository.save(a);
    return "Saved";
  }

  @GetMapping(path="")
  public @ResponseBody Iterable<Address> getAllAddresses() {
    // This returns a JSON or XML with the users
    return addressRepository.findAll();
  }
}

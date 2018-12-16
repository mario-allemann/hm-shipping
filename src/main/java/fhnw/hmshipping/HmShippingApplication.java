package fhnw.hmshipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import fhnw.hmshipping.domain.Shipping;
import fhnw.hmshipping.domain.ShippingRepo;

@SpringBootApplication
@Controller
@EnableAutoConfiguration
public class HmShippingApplication {
	@Autowired
	private ShippingRepo shiRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(HmShippingApplication.class, args);
	}
	
	
	// Map homepage
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "This is the homepage, faggots";
	}

	// Map test page
	@RequestMapping("/shipping")
	@ResponseBody
	Iterable<Shipping> getAllShippings() {
		return shiRepo.findAll();
	}
	
	@RequestMapping("/removeFromWarehouse")
	@ResponseBody
	private static void getEmployees(@RequestParam int prodId, @RequestParam int amount)
	{
	    final String uri = "http://hm-inventory.herokuapp.com/getFromWarehouse";
	    String parms = "?prodId="+ prodId + "&amount=" + amount;

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject((uri+parms), String.class);
	    System.out.println(result);
	}
}




package fhnw.hmshipping;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
		return "This is the homepage";
	}

	// Map /shipping to display the table
	@RequestMapping("/shipping")
	@ResponseBody
	Iterable<Shipping> getAllShippings() {
		return shiRepo.findAll();
	}
	
	// test
	@RequestMapping("/removeFromWarehouse")
	@ResponseBody
	private static void getEmployees(@RequestParam int prodId, @RequestParam int amount) {
	    String uri = "http://hm-inventory.herokuapp.com/getFromWarehouse";
	    String parms = "?prodId="+ prodId + "&amount=" + amount;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getForObject((uri+parms), String.class);
	}
	
	@RequestMapping("/shipOrder")
	@ResponseBody
	void ship(@RequestParam int orderId) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);		
		cal.add(Calendar.DATE, 1);
		
		Date tomorrow = cal.getTime();
		Shipping ship = new Shipping(tomorrow, orderId);
		
		shiRepo.save(ship);
	}
}




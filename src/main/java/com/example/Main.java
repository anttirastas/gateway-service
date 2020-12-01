package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class Main {

  @RequestMapping("/")
  String index() {
    return "Running";
  }

  public static void main(String[] args) throws Exception {
    // Gateway service uses:
    Subscription.Sender globalSender = Service.service(new Subscription[]{
        new Subscription("display", "display", (body, sender) -> {
          System.out.println("display: display");
          System.out.println(body);
          // Push message over socket to the user
        })
    });
    globalSender.send("display", "Everything works!");

    SpringApplication.run(Main.class, args);
  }

}

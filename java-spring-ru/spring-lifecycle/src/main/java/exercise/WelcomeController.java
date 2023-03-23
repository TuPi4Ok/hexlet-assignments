package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    Meal meal;
    @Autowired
    Daytime time;
    @GetMapping("/daytime")
    public String good(){
        return "It is " + time.getName() + " now. Enjoy your " + meal.getMealForDaytime(time.getName());
    }
}
// END

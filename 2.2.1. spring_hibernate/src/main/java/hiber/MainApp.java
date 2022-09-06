package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        /*
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));*/

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("carUser1", 1);
        Car car2 = new Car("carUser2", 2);
        Car car3 = new Car("carUser3", 3);
        Car car4 = new Car("carUser4", 4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        System.out.println("Users");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        System.out.println("Cars");
        List<Car> cars = context.getBean(CarService.class).listCar();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        System.out.println("userByModelAndSeries");
        Optional<User> userOptional = userService.getUserByModelAndSeries("carUser1", 1);
        if (userOptional.isPresent()) {
            User userByModelAndSeries = userOptional.get();
            System.out.println("Id = " + userByModelAndSeries.getId());
            System.out.println("First Name = " + userByModelAndSeries.getFirstName());
            System.out.println("Last Name = " + userByModelAndSeries.getLastName());
            System.out.println("Email = " + userByModelAndSeries.getEmail());
        } else {
            System.out.println("Пользователя с указанной машиной нет в базе");
        }

        context.close();
    }
}

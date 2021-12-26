package jm.spring.rest.client;

import jm.spring.rest.client.config.AppConfig;
import jm.spring.rest.client.entities.UserRole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean(Communication.class);

        System.out.println(communication.getCodeJmpp314());

        System.exit(0);


        List<UserRole> users = communication.getUsers();
        System.out.println("\n=== !)Получен список пользователей в количестве " + users.size() + " человек(а)");
        for (UserRole u: users) {
            System.out.println(u);
        }

        int id = users.get(users.size()-1).getId();
        System.out.println("\nДанные последнего пользователя из списка с id=" + id);
        UserRole user = communication.getUser(id);
        System.out.println(user);

        user = new UserRole("James", "Brown", 33);
        System.out.println("\n=== 1) Сохраняю пользователя " + user);
        communication.saveUser(user);
        System.out.println("+++ 1) первая часть кода");
        // получили первую часть кода

        //этот код для тестирования
        users = communication.getUsers();
        Optional<UserRole> optional = users.stream().filter(x -> x.getName().equals("James")).findFirst();
        user = optional.get();
        final int ID_USER = user.getId();

        //final int ID_USER = 3;

        user.setId(ID_USER);
        user.setName("Thomas");
        user.setLastName("Shelby");
        System.out.println("\n=== 2) Заменяю пользователя " + user);
        communication.saveUser(user);
        System.out.println("+++ 2) третья часть кода");
        //получили вторую часть кода

        System.out.println("\n=== 3) Удаляю пользователя " + ID_USER);
        communication.deleteUser(ID_USER);
        System.out.println("+++ 3) третья часть кода");
        //получили третью часть кода

        context.close();
    }
}

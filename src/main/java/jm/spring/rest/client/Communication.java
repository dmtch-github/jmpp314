package jm.spring.rest.client;

import jm.spring.rest.client.entities.UserRole;
import jm.spring.rest.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "localhost:8080/api/users";


    private String getSessionId(HttpHeaders headers) {
        final String JSID = "JSESSIONID="; //set-cookie = "JSESSIONID=AE714538887F1886586128D616C6E0D5; Path=/; HttpOnly"
        String sessionId = "";
        String[] strings = headers.getFirst(HttpHeaders.SET_COOKIE).split(";");
        for (String s: strings) {
            if(s.startsWith(JSID)) {
                sessionId = s;
                break;
            }
        }
        return sessionId;
    }

    public String getCodeJmpp314Entity() {
        final String URL = "http://91.241.64.178:7081/api/users";
        String sessionId ="";
        String str1 = "";
        String str2 = "";
        String str3 = "";

        //получаем идентификатор сессии
        ResponseEntity<List<User>> responseGet =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        System.out.println("0) ResponseEntity = " + responseGet);
        sessionId = getSessionId(responseGet.getHeaders());
        System.out.println("ID для сессии \"" + sessionId + "\"\n");

//готовим заголовок
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.COOKIE,sessionId);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

//сохраняем
        User user = new User((long)3, "James", "Brown", (byte)23);
        //String jsonParams = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":23}";
        HttpEntity<User> requestEntity = new HttpEntity(user, requestHeaders);
        System.out.println("1) RequestEntity = " + requestEntity);

        ResponseEntity<String> responsePost = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        System.out.println("1) ResponseEntity = " + responsePost);
        str1 = responsePost.getBody();
        System.out.println("1) str1 = \"" + str1 + "\"");
        System.out.println();


//обновляем
//        jsonParams = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":23}";
        user = new User((long)3, "Thomas", "Shelby", (byte)23);
        requestEntity = new HttpEntity(user, requestHeaders);
        System.out.println("2) RequestEntity = " + requestEntity);

        ResponseEntity<String> responsePut = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        System.out.println("2) ResponseEntity = " + responsePut);
        str2 = responsePut.getBody();
        System.out.println("2) str2 = \"" + str2 + "\"");
        System.out.println();

//удаляем
        HttpEntity requestEntityDel = new HttpEntity(requestHeaders);
        System.out.println("3) RequestEntityDel = " + requestEntityDel);

        ResponseEntity<String> responseDel = restTemplate.exchange(URL+"/3", HttpMethod.DELETE, requestEntityDel, String.class);
        System.out.println("3) ResponseEntity = " + responseDel);
        str3 = responseDel.getBody();
        System.out.println("3) str3 = \"" + str3 + "\"");

        return str1 + str2 + str3;
    }

    public String getCodeJmpp314() {
        final String URL = "http://91.241.64.178:7081/api/users";
        String sessionId ="";
        String str1 = "";
        String str2 = "";
        String str3 = "";

        //получаем идентификатор сессии
        ResponseEntity<List<User>> responseGet =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        System.out.println("0) ResponseEntity = " + responseGet);
        sessionId = getSessionId(responseGet.getHeaders());
        System.out.println("ID для сессии \"" + sessionId + "\"\n");

//готовим заголовок
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.COOKIE,sessionId);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

//сохраняем
        String jsonParams = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":23}";
        HttpEntity<User> requestEntity = new HttpEntity(jsonParams, requestHeaders);
        System.out.println("1) RequestEntity = " + requestEntity);

        ResponseEntity<String> responsePost = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        System.out.println("1) ResponseEntity = " + responsePost);
        str1 = responsePost.getBody();
        System.out.println("1) str1 = \"" + str1 + "\"");
        System.out.println();


//обновляем
        jsonParams = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":23}";
        requestEntity = new HttpEntity(jsonParams, requestHeaders);
        System.out.println("2) RequestEntity = " + requestEntity);

        ResponseEntity<String> responsePut = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        System.out.println("2) ResponseEntity = " + responsePut);
        str2 = responsePut.getBody();
        System.out.println("2) str2 = \"" + str2 + "\"");
        System.out.println();

//удаляем
        HttpEntity requestEntityDel = new HttpEntity(requestHeaders);
        System.out.println("3) RequestEntityDel = " + requestEntityDel);

        ResponseEntity<String> responseDel = restTemplate.exchange(URL+"/3", HttpMethod.DELETE, requestEntityDel, String.class);
        System.out.println("3) ResponseEntity = " + responseDel);
        str3 = responseDel.getBody();
        System.out.println("3) str3 = \"" + str3 + "\"");

        return str1 + str2 + str3;
    }



    public List<UserRole> getUsers() {
        List<UserRole> users;
        ResponseEntity<List<UserRole>> response =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserRole>>() {
                });
        users = response.getBody();
        return users;
    }

    public UserRole getUser(int id) {
        UserRole user = restTemplate.getForObject(URL + "/" + id, UserRole.class);
        return user;
    }

    public void saveUser(UserRole user) {
        int id = user.getId();
        if(id == 0) {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(URL, user, String.class);
            System.out.println("Пользователь добавлен в БД" + response.getBody());

        } else {
            restTemplate.put(URL, user);
            System.out.println("Пользователь обновлен " + user);
        }
    }

    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" +id);
    }

}

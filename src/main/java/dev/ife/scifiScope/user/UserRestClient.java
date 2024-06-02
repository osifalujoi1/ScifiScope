package dev.ife.scifiScope.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    //RestClient implements  Builder class
    //get an instance of builder
    //This isn't actually the api (the low-level mechanism that is making the api call across HTTP)
    //There is a default http client underneath the hood, you can swap that out for sth else using "requestFactory"
    // Spring will turn all these into an implementation at runtime.
    public UserRestClient(RestClient.Builder builder){
        this.restClient = builder.baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<User> findAll() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(Integer id){
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class); //model it to the User class we already have
    }
}

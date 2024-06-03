package dev.ife.scifiScope;

import dev.ife.scifiScope.user.User;
import dev.ife.scifiScope.user.UserHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class ScifiScopeApplication {

	public static final Logger log = LoggerFactory.getLogger(ScifiScopeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ScifiScopeApplication.class, args);
	}
	//Tell Spring about your http client, so it can set it up for you instead of setting it up yourself
	@Bean
	UserHttpClient userHttpClient() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	//Test out connectivity to client info using command line runner
	//Get an instance of the client -> UserRestClient client or UserHttpClient client
	//create a list of all the clients and print it out
	@Bean
	CommandLineRunner runner(UserHttpClient client){
		return args -> {
			List<User> users = client.findAll();
			System.out.println(users);

			User user = client.findById(1);
			System.out.println(user);
		};
	}

}

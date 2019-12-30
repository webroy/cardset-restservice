package ch.cardset.restservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.cardset.restservice.entity.User;
import ch.cardset.restservice.repository.UserRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	protected CommandLineRunner init(final UserRepository userRepository) {

		return args -> {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setName("Administrator");
			user.setEmail("admin@javahelps.com");
			userRepository.save(user);

		};
	}
        
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**");
                }
            };
        }
}

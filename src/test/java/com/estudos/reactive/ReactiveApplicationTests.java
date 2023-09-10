package com.estudos.reactive;

import com.estudos.reactive.api.dto.user.NewUserRequest;
import com.estudos.reactive.api.dto.user.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	@DisplayName("Should create user")
	public void testCreateUserSuccess() {
		String name = "Name valid";
		String email = "email@valid";
		String password = "password valid";

		NewUserRequest newUserRequest = new NewUserRequest(name, email, password);

		webTestClient.post().uri("/users").bodyValue(newUserRequest)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(UserResponse.class)
				.value(postResponse -> {
					assertNotNull(postResponse.getId());
					assertEquals(postResponse.getName(), name);
					assertEquals(postResponse.getEmail(), email);
				});

	}

	@Test
	@DisplayName("Should not create user with duplicate email")
	public void testCreateUserDuplicateEmail() {
		String name = "Name duplicated";
		String email = "emailduplicated@valid";
		String password = "password duplicated";

		NewUserRequest newUserRequest = new NewUserRequest(name, email, password);

		webTestClient.post().uri("/users").bodyValue(newUserRequest)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(UserResponse.class)
				.value(postResponse -> {
					assertNotNull(postResponse.getId());
					assertEquals(postResponse.getName(), name);
					assertEquals(postResponse.getEmail(), email);

					webTestClient.post().uri("/users").bodyValue(newUserRequest)
							.exchange()
							.expectStatus().isBadRequest();
				});
	}

	@Test
	@DisplayName("Should not create user with invalid email")
	public void testCreateUserInvalidEmail() {
		String name = "Email invalid";
		String email = "emailInvalid";
		String password = "123123";

		NewUserRequest newUserRequest = new NewUserRequest(name, email, password);

		webTestClient.post().uri("/users").bodyValue(newUserRequest).exchange().expectStatus().isBadRequest();
	}

	@Test
	@DisplayName("Should not create user with blank fields")
	public void testCreateUserBlankFields() {
		webTestClient.post().uri("/users").bodyValue(new NewUserRequest("", "", ""))
				.exchange()
				.expectStatus().isBadRequest();
	}
}

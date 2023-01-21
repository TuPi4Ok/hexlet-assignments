package exercise;

import io.ebeaninternal.server.util.Str;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void  beforeAll() {
        app = App.getApp();
        app.start();
        String port = String.valueOf(app.port());
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void  afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void createUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan")
                .field("lastName", "Prikhodko")
                .field("email", "bebra228@gmail.com")
                .field("password", "112358")
                .asString();
        assertThat(response.getStatus()).isEqualTo(302);

        User bdUser = new QUser()
                .email.eq("bebra228@gmail.com")
                .findOne();
        assertThat(bdUser).isNotNull();
        assertThat(bdUser.getFirstName()).isEqualTo("Ivan");
        assertThat(bdUser.getLastName()).isEqualTo("Prikhodko");
    }
    @Test
    void createUser_Negative() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan")
                .field("lastName", "Prikhodko")
                .field("email", "bebra228gmail.com")
                .field("password", "11")
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User bdUser = new QUser()
                .email.eq("bebra228gmail.com")
                .findOne();
        assertThat(bdUser).isNull();

        assertThat(response.getBody()).contains("Ivan").contains("Prikhodko");
        assertThat(response.getBody()).contains("Пароль должен содержать не менее 4 символов");

    }
    // END
}

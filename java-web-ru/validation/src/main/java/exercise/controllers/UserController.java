package exercise.controllers;

import io.ebeaninternal.server.util.Str;
import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

public final class UserController{

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        User user = new User(ctx.formParam("firstName"), ctx.formParam("lastName"), ctx.formParam("email"), ctx.formParam("password"));

        Validator<String> firstNameValid = ctx.formParamAsClass("firstName", String.class)
                .check(name -> !name.isEmpty(), "firstName can not be empty");
        Validator<String> lastNameValid = ctx.formParamAsClass("lastName", String.class)
                .check(name -> !name.isEmpty(), "lastName can not be empty");
        Validator<String> emailValid = ctx.formParamAsClass("email", String.class)
                .check(email -> !email.isEmpty(), "Email is not valid for email")
                .check(email -> {

                    EmailValidator validator = EmailValidator.getInstance();
                    if (validator.isValid(email)) {
                        return  true;
                    } else {
                        return false;
                    }
                },"Email is not valid!");
        Validator<String> passwordValid = ctx.formParamAsClass("password", String.class)
                .check(password -> password.length() > 4, "password is too short")
                .check(StringUtils::isNumeric, "Password must contain only numbers");

        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
                firstNameValid,
                lastNameValid,
                emailValid,
                passwordValid
        );

        if(!errors.isEmpty()){
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);
            ctx.render("users/new.html");
            return;
        }

        user.save();
        ctx.redirect("/users");
        // END
    };
}

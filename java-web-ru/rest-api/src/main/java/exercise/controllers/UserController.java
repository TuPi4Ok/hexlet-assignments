package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .findList();
        String jUsers = DB.json().toJson(users);
        ctx.json(jUsers);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.eq(Long.valueOf(id))
                .findOne();
        String jUser = DB.json().toJson(user);
        ctx.json(jUser);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        String body = ctx.body();

        User newUser = DB.json().toBean(User.class, body);
        newUser.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User newUser = DB.json().toBean(User.class, ctx.body());
        new QUser()
                .id.eq(Long.valueOf(id))
                .asUpdate()
                .set("firstName", newUser.getFirstName())
                .set("lastName", newUser.getLastName())
                .set("email", newUser.getEmail())
                .set("password", newUser.getPassword())
                .update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.eq(Long.valueOf(id))
                .delete();
        // END
    };
}

package com.cases.cases.Services;

import com.cases.cases.Models.Comment;
import com.cases.cases.Models.Role;
import com.cases.cases.Models.SupportCase;
import com.cases.cases.Models.Users;
import com.cases.cases.Repositories.CommentRepository;
import com.cases.cases.Repositories.SupportCaseRepository;
import com.cases.cases.Repositories.UserRepository;
import com.cases.cases.UI.DashboardView;
import com.cases.cases.UI.LoginView;
import com.cases.cases.UI.RegistrationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SupportCaseRepository supportCaseRepository;
    private final CommentRepository commentRepository;
    private Users authenticatedUser;

    @Autowired
    public UserService(UserRepository userRepository, SupportCaseRepository supportCaseRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.supportCaseRepository = supportCaseRepository;
        this.commentRepository = commentRepository;
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users authenticate(String username, String password) throws AuthException {
        Users user = userRepository.findByUsername(username);
        if (user != null && user.checkPassword(password)) {
            authenticatedUser = user;
            createRoutes(user.getRole());
            return user;
        } else {
            throw new AuthException();
        }
    }

    public Users getAuthenticatedUser() {
        return authenticatedUser;
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).forEach(route ->
                RouteConfiguration.forSessionScope().setRoute(route.route, route.view));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        var routes = new ArrayList<AuthorizedRoute>();

        if (role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("dashboard", "Dashboard", DashboardView.class));
            routes.add(new AuthorizedRoute("login", "Login", LoginView.class));
            routes.add(new AuthorizedRoute("register", "Register", RegistrationView.class));
        }

        return routes;
    }

    @Transactional
    public List<SupportCase> getAllCases() {
        List<SupportCase> cases = supportCaseRepository.findAll();
        cases.forEach(supportCase -> Hibernate.initialize(supportCase.getComments()));
        return cases;
    }



    public List<SupportCase> getCasesByUserId(Long userId) {
        return supportCaseRepository.findByUserId(userId);
    }

    public SupportCase saveSupportCase(SupportCase supportCase) {
        return supportCaseRepository.save(supportCase);
    }

    public List<Comment> getCommentsByCaseId(Long caseId) {
        return commentRepository.findBySupportCaseId(caseId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteUser(Users user) {
        userRepository.delete(user);
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }

    public static class AuthException extends Exception {
    }

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {
    }
}

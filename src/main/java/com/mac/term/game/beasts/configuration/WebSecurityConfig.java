package com.mac.term.game.beasts.configuration;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import com.mac.term.game.beasts.game_utils.UserInfoControl;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Configuration
@Order(1)
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/static/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll().and()
                .csrf().disable();
    }

//    private BeastGenerator beastGenerator = new BeastGenerator();

    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo, BeastGenerator beastGenerator) {
        UserInfoControl userInfoControl = new UserInfoControl(userRepo);
        return map -> {
            String id = (String) map.get("sub");
            User u = userRepo.findById(id).orElseGet(() -> {
                User nu = new User();
                nu.setId(id);
                nu.setEmail((String) map.get("email"));
                nu.setMoney(4200);
                nu.setExp(0);
                nu.setLevel(0);
                nu.setNick((String) map.get("name"));
                nu.setIcon((String) map.get("picture"));
                nu.setAuthVia("google");
                nu.setCreatures(null);
                nu.setDescription("Герой явился.");
                nu.setVictoryNumber(0);
                nu.setRegistered(LocalDateTime.now());
                return nu;
            });
            if (u.getCreatures() == null && ChronoUnit.SECONDS.between(LocalDateTime.now(), u.getRegistered()) < 50 ){
                userRepo.save(u);
                userInfoControl.created(u.getId());
                Set<Creature> set = beastGenerator.generateForNewUser(id);
                u.setCreatures(set);
            }
            return userRepo.save(u);
        };

    }
}

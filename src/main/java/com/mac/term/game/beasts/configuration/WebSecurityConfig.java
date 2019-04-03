package com.mac.term.game.beasts.configuration;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.Date;
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
                .and()
                .csrf().disable();
    }

    private BeastGenerator beastGenerator = new BeastGenerator();

    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo, CreatureRepo creatureRepo) {
        return map -> {
            String id = (String) map.get("sub");
            User u = userRepo.findById(id).orElseGet(() -> {
                User nu = new User();
                nu.setId(id);
                nu.setEmail((String) map.get("email"));
                nu.setMoney(42);
                nu.setExp(0);
                nu.setLevel(0);
                nu.setNick((String) map.get("name"));
                nu.setIcon((String) map.get("picture"));
                nu.setAuthVia("google");
                return nu;
            });
            u.setLastVisit(LocalDateTime.now());
//            Set<Creature> set = beastGenerator.generateForNewUser(id, creatureRepo, userRepo);
//            u.setCreatures(set);
            return userRepo.save(u);
        };
    }
}

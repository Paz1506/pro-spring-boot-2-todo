package com.apress.directory.security;

import com.apress.directory.domain.Person;
import com.apress.directory.repository.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Pavel Zaytsev
 */
public class DirectoryUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public DirectoryUserDetailsService(PersonRepository personRepository) {this.personRepository = personRepository;}

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            final Person person = this.personRepository.findByEmailIgnoreCase(s);

            if (person != null) {
                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                String password = encoder.encode(person.getPassword());

                return User.withUsername(person.getEmail())
                           .accountLocked(!person.isEnabled())
                           .password(password)
                           .roles(person.getRole())
                           .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new UsernameNotFoundException(s);
    }
}

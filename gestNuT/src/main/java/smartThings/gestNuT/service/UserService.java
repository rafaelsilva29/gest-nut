package smartThings.gestNuT.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Service;

import smartThings.gestNuT.model.User;
import smartThings.gestNuT.repo.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostConstruct
	protected void initialize() {
		User userFlag = userRepository.findByEmail("shop.nut.2019@gmail.com");
		if(userFlag == null){
			saveUser(new User("shop.nut.2019@gmail.com", bCryptPasswordEncoder.encode("admin"), "admin", "admin", "admin", "ROLE_ADMIN"));
		}
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", this);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User not found...");
		}
		return createUser(user);
	}

	public void signIn(User user) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}
	
	private UsernamePasswordAuthenticationToken authenticate(User user) {
		return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));
	}
	
	private org.springframework.security.core.userdetails.User createUser(User user) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(createAuthority(user)));
	}

	private GrantedAuthority createAuthority(User user) {
		return new SimpleGrantedAuthority(user.getRole());
	}

}
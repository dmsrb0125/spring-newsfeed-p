package com.sparta.springnewsfeed.security;

/*

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("This method should not be used");
    }

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with userId: " + userId));
        return UserPrincipal.create(user);
    }
}*/

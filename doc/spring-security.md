# Core Components
## SecurityContextHolder
What: store details of the present security context of the application. uses a ThreadLocal to store by default.
```java
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
String username = ((UserDetails)principal).getUsername();
} else {
String username = principal.toString();
}
```

## UserDetailsService
the adapter between yourown user database and what Spring Security needs inside the SecurityContextHolder
```java
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
```

## The AuthenticationManager, ProviderManager and AuthenticationProvider
The default implementation in Spring Security(AuthenticationManager) is called ProviderManager: it delegates to a list of configured AuthenticationProvider
```xml
<bean id="authenticationManager"
class="org.springframework.security.authentication.ProviderManager">
<constructor-arg>
<list>
<ref local="daoAuthenticationProvider"/>
<ref local="anonymousAuthenticationProvider"/>
<ref local="ldapAuthenticationProvider"/>
</list>
</constructor-arg>
</bean>
```

## Secure Objects and the AbstractSecurityInterceptor
![Security interceptors and the "secure object" model](https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/html/images/security-interception.png)

## AccessDecisionManager
authorization decision
![Voting Decision Manager](https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/html/images/access-decision-voting.png)


### ref:
https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/pdf/spring-security-reference.pdf
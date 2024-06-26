package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Optional;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameUpdate, Object> {
    private AppUserRepository userRepository;
    private String username;
    private String userId;
    private String message;
    @Override
    public void initialize(UniqueUsernameUpdate constraintAnnotation) {
        userId = constraintAnnotation.userId();
        username = constraintAnnotation.username();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object usernameO = new BeanWrapperImpl(value).getPropertyValue(username);

        Object userIdO = new BeanWrapperImpl(value).getPropertyValue(userId);
        AppUser appUser = userRepository.findByUsername(userIdO.toString()).orElse(null);

        boolean isValid = appUser != null && usernameO != null && userIdO.equals(appUser.getId());

        if (!isValid) {

            context.disableDefaultConstraintViolation();

            context

                    .buildConstraintViolationWithTemplate(message)

                    .addPropertyNode(username)

                    .addConstraintViolation();

        }


        return isValid;
    }
}

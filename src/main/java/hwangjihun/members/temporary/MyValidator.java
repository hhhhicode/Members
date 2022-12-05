package hwangjihun.members.temporary;

import hwangjihun.members.model.dto.MemberAddDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberAddDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberAddDto targetObject = (MemberAddDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "required");

        if (1000 < targetObject.getMemo().length()) {
            errors.rejectValue("memo", "max", new Object[]{1000}, null);
        }
    }
}

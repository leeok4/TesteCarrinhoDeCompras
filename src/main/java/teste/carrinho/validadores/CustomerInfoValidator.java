package teste.carrinho.validadores;
 
import org.apache.commons.validator.routines.EmailValidator;
import teste.carrinho.modelos.CustomerInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
// @Component: As a Bean.
@Component
public class CustomerInfoValidator implements Validator {
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    // Validador referente a classe do comprador.
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerInfo.class;
    }
 
    public void validate(Object target, Errors errors) {
        CustomerInfo custInfo = (CustomerInfo) target;
 
        // Checa os campos de informacao do comprador.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
 
        if (!emailValidator.isValid(custInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
 
}
package org.web2017.jsr303;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:applicationContext-validator.xml")
public class BeanValidatorsTest extends AbstractJUnit4SpringContextTests {
  @BeforeClass
  public static void beforeClass() {
    // To avoid the non-English environment test failure on message asserts.
    Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
  }
  @Autowired
  private Validator validator;
  Set<ConstraintViolation<Customer>> violations;

  @Before
  public void setUp() {
    this.violations = getConstraintViolations();
  }

  @Test
  public void validateWithException() throws Exception {


  }

  @Test
  public void extractMessage() throws Exception {
    assertThat(violations).hasSize(2);
    // extract message as list
    List<String> result = BeanValidators.extractMessage(violations);
    System.out.println(result);
    // assertThat(result).containsOnly("not a well-formed email address", "may not be empty");

  }


  @Test
  public void extractPropertyAndMessage() throws Exception {
    // extract message as list
    Map<String, String> resultMap = BeanValidators.extractPropertyAndMessage(violations);
    System.out.println(resultMap);
    // assertThat(resultMap).containsOnly(entry("email", "not a well-formed email address"),
    //     entry("name", "may not be empty"));

  }

  private Set<ConstraintViolation<Customer>> getConstraintViolations() {
    Customer customer = new Customer();
    customer.setEmail("abc");
    return validator.validate(customer);
  }


  @Test
  public void extractPropertyAndMessageAsList() throws Exception {
    final List<String> msgList = BeanValidators.extractPropertyAndMessageAsList(violations);
    System.out.println(msgList);
  }


  private static class Customer {

    private String name;

    private String email;

    @NotBlank
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Email
    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

  }

}
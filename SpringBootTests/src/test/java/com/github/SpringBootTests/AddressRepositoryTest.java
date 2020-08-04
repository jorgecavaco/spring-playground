package com.github.SpringBootTests;

import com.github.SpringBootTests.entities.Address;
import com.github.SpringBootTests.repository.AddressRepository;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create"})
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  TransactionDbUnitTestExecutionListener.class
})
public class AddressRepositoryTest {

  @Autowired
  AddressRepository addressRepository;

  @Autowired private DataSource dataSource;
  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private EntityManager entityManager;

  @Test
  void injectedComponentsAreNotNull() {
    Assertions.assertThat(dataSource).isNotNull();
    Assertions.assertThat(jdbcTemplate).isNotNull();
    Assertions.assertThat(entityManager).isNotNull();
    Assertions.assertThat(addressRepository).isNotNull();
  }

  @Test
  void saveAddress() {
    Address addressEntity = new Address();

    addressEntity.setId(1L);
    addressEntity.setAddress("New Address");
    addressRepository.save(addressEntity);

    Assertions.assertThat(addressRepository.findById(1L)).isNotNull();
  }

  @Test
  @DatabaseSetup("classpath:createAddress.xml")
  void queryDbData() {

    Optional<Address> address = addressRepository.findById(4L);

    Assertions.assertThat(address.get().getAddress()).isEqualTo("address 1");
  }
}

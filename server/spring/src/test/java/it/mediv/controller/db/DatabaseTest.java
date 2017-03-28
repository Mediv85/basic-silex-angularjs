package it.mediv.controller.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import it.mediv.persistence.Users;
import it.mediv.repository.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)

public class DatabaseTest {

   // @Autowired
   // private TestEntityManager entityManager;

    @Autowired
    private UsersRepository repository;

    @Test
    public void testExample() throws Exception {

//    	this.entityManager.persist(new Users((long) 1, "admin", "password", "ROLE_ADMIN"));
        Users user = this.repository.findByUsername("admin");
        
        assertThat(user.getId()).isEqualTo((long)1);
        assertThat(user.getUsername()).isEqualTo("admin");
        assertThat(user.getPassword()).isEqualTo("$2a$10$Oufah91CGRx4BXpuHunOzO.WjY9m00yw2SmFdldrzzVnq6fcnlqPy");
        assertThat(user.getRoles()).isEqualTo("ROLE_ADMIN");

    }

}



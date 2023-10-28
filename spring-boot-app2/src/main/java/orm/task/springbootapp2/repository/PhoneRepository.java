package orm.task.springbootapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orm.task.springbootapp2.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}

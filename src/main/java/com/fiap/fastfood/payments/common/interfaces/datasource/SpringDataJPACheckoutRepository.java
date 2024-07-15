package com.fiap.fastfood.payments.common.interfaces.datasource;

import com.fiap.fastfood.payments.external.orm.CheckoutORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJPACheckoutRepository extends JpaRepository<CheckoutORM, String> {
    List<CheckoutORM> findAllByOrderByCreatedAtAsc();
}

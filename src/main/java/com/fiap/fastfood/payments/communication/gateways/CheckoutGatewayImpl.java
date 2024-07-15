package com.fiap.fastfood.payments.communication.gateways;

import com.fiap.fastfood.payments.common.builders.CheckoutBuilder;
import com.fiap.fastfood.payments.common.interfaces.datasource.SpringDataJPACheckoutRepository;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.core.entity.Checkout;
import com.fiap.fastfood.payments.external.orm.CheckoutORM;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CheckoutGatewayImpl implements CheckoutGateway {

    private final SpringDataJPACheckoutRepository springDataJPACheckoutRepository;

    public CheckoutGatewayImpl(SpringDataJPACheckoutRepository springDataJPACheckoutRepository) {
        this.springDataJPACheckoutRepository = springDataJPACheckoutRepository;
    }

    @Override
    public CheckoutORM save(Checkout checkout) {
        final var orm = CheckoutBuilder.fromDomainToOrm(checkout);
        return springDataJPACheckoutRepository.save(orm);
    }

    @Override
    public List<Checkout> findAll() {
        final var orms = springDataJPACheckoutRepository.findAllByOrderByCreatedAtAsc();
        return orms.stream().map(CheckoutBuilder::fromOrmToDomain).collect(Collectors.toList());
    }
}

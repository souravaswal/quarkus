package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Laptop;

@ApplicationScoped
public class LaptopRepository implements PanacheRepository<Laptop> {
}

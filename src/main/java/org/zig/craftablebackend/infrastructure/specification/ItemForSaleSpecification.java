package org.zig.craftablebackend.infrastructure.specification;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;

public class ItemForSaleSpecification {

    public static Specification<ItemForSale> hasCreatorId(Integer creatorId) {
        return (root, query, cb) -> creatorId == null ? null : cb.equal(root.get("creatorId").get("id"), creatorId);
    }

    public static Specification<ItemForSale> hasProfession(String professionName) {
        return (root, query, cb) -> {
            if (professionName == null) return null;
            Join<Object, Object> professions = root.join("creatorId").join("professions");
            return cb.equal(professions.get("profession"), professionName); // <-- poprawione
        };
    }

    public static Specification<ItemForSale> priceBetween(Integer minPrice, Integer maxPrice) {
        return (root, query, cb) -> {
            if (minPrice != null && maxPrice != null)
                return cb.between(root.get("price"), minPrice, maxPrice);
            if (minPrice != null)
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            if (maxPrice != null)
                return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            return null;
        };
    }
}

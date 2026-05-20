package co.edu.uco.ucoparking.application.usecase.specification.generics;

import co.edu.uco.ucoparking.crosscuting.helper.UUIDHelper;
import co.edu.uco.ucoparking.crosscuting.specification.base.Specification;

import java.util.UUID;

public class IsUUIDPresentSpecification extends Specification<UUID> {
    @Override
    public boolean isSatisfiedBy(UUID data) {
        return !UUIDHelper.getUUIDHelper().isDefaultUUID(data);
    }
}

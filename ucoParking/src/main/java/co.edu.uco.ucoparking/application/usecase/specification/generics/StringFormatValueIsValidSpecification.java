package co.edu.uco.ucoparking.application.usecase.specification.generics;

import co.edu.uco.ucoparking.crosscuting.helper.TextHelper;
import co.edu.uco.ucoparking.crosscuting.specification.base.Specification;

public class StringFormatValueIsValidSpecification extends Specification<String> {

    private String pattern;
    private boolean mustApplyTrim;

    public StringFormatValueIsValidSpecification(String pattern, boolean mustApplyTrim) {
        super();
        this.pattern = pattern;
        this.mustApplyTrim = mustApplyTrim;
    }

    @Override
    public boolean isSatisfiedBy(String data) {
        return TextHelper.formatIsValid(data, pattern, mustApplyTrim);
    }
}

package taskflowapi.infra.repository.predicates;

import com.querydsl.core.BooleanBuilder;
import org.springframework.util.ObjectUtils;

public class PredicateBase {

    protected BooleanBuilder builder;

    public PredicateBase() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    protected boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

    protected boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public void add(BooleanBuilder booleanBuilder) {
        builder.and(booleanBuilder);
    }
}

package com.eolivenza.modules.baseProject.application.annotations;


import com.eolivenza.modules.baseProject.application.TransactionManagerNames;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * Targets annotated with this annotation will use the {@link TransactionManagerNames#DOMAIN} transaction manager.
 * <p>
 * This annotation has the same behaviour that {@link org.springframework.transaction.annotation.Transactional}, but
 * it has an additional feature: it rollbacks when checked exceptions are thrown too.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagerNames.DOMAIN)
public @interface DomainStrictTransactional {
}

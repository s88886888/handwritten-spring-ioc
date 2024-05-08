package org.handwriting.anno;

import java.lang.annotation.*;

/**
 * @author Linson
 * &#064;description  用于依赖注入
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DI {
}

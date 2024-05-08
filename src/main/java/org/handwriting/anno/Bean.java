package org.handwriting.anno;

import java.lang.annotation.*;

/**
 * @author Linson
 * &#064;description  用于创建对象
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

}

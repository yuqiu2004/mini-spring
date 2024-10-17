package org.example.aop;

/**
 * JoinCut表达方式
 */
public interface PointCut {

    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}

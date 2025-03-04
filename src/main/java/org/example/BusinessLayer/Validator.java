package org.example.BusinessLayer;

/**
 * Interface that contains the validate method
 * @param <T>
 */
public interface Validator<T> {
    public void validate(T t);
}

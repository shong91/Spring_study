package ch03.studyspring.template;

public interface LineCallback<T> {
    T doSthWithLine(String line, T value);
}

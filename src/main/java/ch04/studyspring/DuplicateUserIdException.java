package ch04.studyspring;

// 런타임 예외를 일반화하여 사용하는 방법.
public class DuplicateUserIdException extends RuntimeException {

    // 중첩 예외를 만들 수 있도록 생성자를 추가한다.
    // cause: 메세지나 예외 상황을 전달하는 데 필요한 정보를 추가할 때 사용할 파라미터.
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}

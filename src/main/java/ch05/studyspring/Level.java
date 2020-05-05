package ch05.studyspring;

public enum Level {
    //enum 오브젝트 정의
    BASIC(1), SILVER(2), GOLD(3);

    private final int value;

    // DB에 저장할 값을 넣어줄 생성자
    Level(int value) {
        this.value = value;
    }

    // enum은 오브젝트이므로 DB에 저장될 수 있는 타입이 아님. => INT 형으로 변환
    public int intValue(){
        return value;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1: return BASIC;
            case 2: return SILVER;
            case 3: return GOLD;
            default: throw new AssertionError("Unkwon value: " + value);

        }
    }



}

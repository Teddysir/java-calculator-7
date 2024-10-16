package calculator.exception;

public class Exceptions {

    public void validateCommaDelimiter(String input) {
        if(input.contains(",,")) {
            throw new IllegalArgumentException("[ERROR] 연속된 콤마(,,)는 사용할 수 없습니다.");
        }
    }

    public void validateColonDelimiter(String input) {
        if(input.contains("::")) {
            throw new IllegalArgumentException("[ERROR] 연속된 콜론(::)은 사용할 수 없습니다.");
        }
    }

    public void validateLeadingDelimiter(String input) {
        if((input.charAt(0) == ',') || (input.charAt(0) == ':')) {
            throw new IllegalArgumentException("[ERROR] 문자열의 처음에 구분자가 올 수 없습니다.");
        }
    }

}

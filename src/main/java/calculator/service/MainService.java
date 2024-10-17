package calculator.service;

import calculator.exception.Exceptions;
import calculator.model.InputString;
import calculator.model.ResultNumbers;

import java.util.Arrays;
import java.util.List;

public class MainService {

    Exceptions exceptions = new Exceptions();
    private InputString inputString;
    private ResultNumbers resultNumbers;

    // 커스텀 구분자가 두 개 이상일 수 있다
    // 커스텀 구분자가 / 일 수 있고 // 일 수 있다. 이때 어떻게 처리할 것인가?
    // 커스텀 구분자는 반드시 맨 앞에 선언해주어야 한다, 연속된 커스텀 구분자는 괜찮다,
    public String getCustomDelimiter(InputString inputString) {

        String input = inputString.getInputString();

        exceptions.validateCustomDelimiterSize(inputString.getCustomDelimiter().size());

        if (input.startsWith("//")) { //
            int customLastMark = input.indexOf("\\n"); // //로 시작하지만 \n가 없다면 -1 출력 있다면 해당 인덱스값 반환
            if (customLastMark != -1) {

                String customDelimiter = input.substring(2, customLastMark); // 자르기
                inputString.addCustomDelimiter(customDelimiter);

                String refiendString = input.substring(customLastMark + 2);
                inputString.setInputString(refiendString); // 하나의 커스텀 문자열이 빠진 새로운 String 저장

                return getCustomDelimiter(inputString);

            }
        }
        return input;
    }

    public void extractSlashFromString(InputString inputString) {
        if (inputString.getInputString().startsWith("/")) {
            String refinedString = inputString.getInputString().replaceFirst("^/+", "");
            inputString.setInputString(refinedString);
        }
    }

    public void extractNumbersFromString(InputString input, ResultNumbers resultNumbers) {

        List<String> customDelimiter = input.getCustomDelimiter();

        StringBuilder defaultRegex = new StringBuilder("[,|:]");

        if (!customDelimiter.isEmpty()) {
            for (String delimiter : customDelimiter) {
                defaultRegex.append("|").append(delimiter);
            }
        }

        // 배열에 문자열이 있는지 체크
        // 배열에 음수가 있는지 체크

        String[] numberList = input.getInputString().split(defaultRegex.toString());

        for(String num : numberList) {
            resultNumbers.addNumberList(Integer.parseInt(num));
        }

        System.out.println(Arrays.toString(resultNumbers.getNumberList().toArray()));

    }


}

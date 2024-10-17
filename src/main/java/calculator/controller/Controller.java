package calculator.controller;

import calculator.exception.Exceptions;
import calculator.model.InputString;
import calculator.service.MainService;
import calculator.view.InputMessage;
import camp.nextstep.edu.missionutils.Console;

public class Controller {

    private MainService mainService = new MainService();
    private Exceptions exceptions = new Exceptions();
    private InputString inputString;

    public void start() {
        try {
            getString();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getString() {
        InputMessage.inputString();
        String input = Console.readLine().trim();

        exceptions.validateColonDelimiter(input);
        exceptions.validateCommaDelimiter(input);

        inputString = new InputString(input);
        mainService.getCustomDelimiter(inputString);
    }

    private void getNumberList() {
        String refinedInput = inputString.getInputString();
    }


}

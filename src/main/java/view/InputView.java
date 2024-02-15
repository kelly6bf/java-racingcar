package view;

import domain.racing.RacingCount;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    Scanner sc = new Scanner(System.in);

    public List<String> inputCars() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input = sc.nextLine();

        return Stream.of(input.split(","))
                .map(String::trim)
                .toList();
    }

    public RacingCount inputRacingCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        int input = parseNumber(sc.nextLine());

        return new RacingCount(input);
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }
}

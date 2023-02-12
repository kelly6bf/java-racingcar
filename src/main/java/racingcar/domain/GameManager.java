package racingcar.domain;

import racingcar.datatransfer.CarNamesRequest;
import racingcar.datatransfer.GameResultResponse;
import racingcar.datatransfer.RoundResultResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;
    private final Cars cars;
    private GameRound gameRound;

    public GameManager(InputView inputView, OutputView outputView, Cars cars) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cars = cars;
    }

    public void playGame() {
        createCars();
        createGameRound();
        startEachGameRound();
        endGame();
    }

    private void startEachGameRound() {
        outputView.printResultMessage();
        while (!gameRound.isEnd()) {
            cars.moveCars();
            gameRound.increaseRound();
            RoundResultResponse roundResultResponse = new RoundResultResponse(cars.getCarsState());
            outputView.printRoundResult(roundResultResponse);
        }
    }

    private void createCars() {
        while (true) {
            try {
                CarNamesRequest carNamesRequest = inputView.inputCarName();
                List<String> carNames = carNamesRequest.getCarNames();
                cars.generateCars(carNames);
                return;
            } catch (Exception e) {
                cars.reset();
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void createGameRound() {
        while (true) {
            try {
                int totalRound = inputView.inputGameRound().getRound();
                gameRound = new GameRound(totalRound);
                return;
            } catch (Exception e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void endGame() {
        GameResultResponse gameResultResponse = new GameResultResponse(cars.findWinnerNames());
        outputView.printEndGameResult(gameResultResponse);
    }
}

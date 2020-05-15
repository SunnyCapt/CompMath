package capt.sunny;

import capt.sunny.trapez.ConsoleManager;
import capt.sunny.trapez.IntegrationProblemWrapper;
import capt.sunny.trapez.TrapezoidalRule;

import java.util.function.Function;

public class Application {
    public static void main(String[] args) {
        try {
            ConsoleManager manager = new ConsoleManager();
            Function func = manager.getFunc();
            double lowLimit = manager.getLowLimit();
            double highLimit = manager.getHighLimit();
            double allowableEpsilon = manager.getAllowableEpsilon();
            IntegrationProblemWrapper problem = new IntegrationProblemWrapper(
                    func,
                    lowLimit,
                    highLimit,
                    allowableEpsilon
            );
            TrapezoidalRule.calculate(problem);
            System.out.printf(
                    "\nResult: %f\nEpsilon: %f\nSteps: %d",
                    problem.getResult(),
                    problem.getResultEpsilon(),
                    problem.getN()
            );
        } catch (Exception e) {
            System.out.printf("\nSmth wrong (%s).\nBye :3", e.getMessage());
            System.exit(-1);
        }
    }
}

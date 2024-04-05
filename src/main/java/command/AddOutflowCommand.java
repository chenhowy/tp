package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class AddOutflowCommand extends BaseCommand {

    public AddOutflowCommand(String[] commandParts) throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        super(false, commandParts);
        createOutflow();
    }

    private void createOutflow() throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        //@@author Kishen271828
        String outflowName = null;
        double outflowAmount = 0.0;
        String outflowDate = null;
        String outflowTime = null;
        String outflowCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant outflow information. */
        for (int i = 1 ; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("n/")) {
                outflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                outflowAmount = Double.parseDouble(part.substring(2));
            } else if (part.startsWith("d/")) {
                outflowDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                outflowTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                outflowCategory = part.substring(2);
            } else {
                throw new IncorrectCommandSyntaxException(commandParts[0]);
            }
        }
        String outflowDateTime = outflowDate + " " + outflowTime;

        outflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
        assert outflowCategory != null;
        outflow.setCategory(outflowCategory);
    }

    public String execute(TransactionManager manager) {
        //@@author Kishen271828
        if (!canExecute) {
            return "Sorry, outflow not added.";
        }
        manager.addTransaction(outflow);
        return "Ok. Added outflow";
    }
}

package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class ViewHistoryCommand extends BaseCommand {

    public ViewHistoryCommand(String[] commandParts) {
        super(false,commandParts);
    }

    public String execute(TransactionManager manager) throws Exception{
        //@@author Kishen271828
        //String numTransactionsString = null;
        int numTransactions = 0;
        if (commandParts[1].startsWith("n/")) {
            String numTransactionsString = commandParts[1].substring(2);
            numTransactions = Integer.parseInt(numTransactionsString);
        } else if (commandParts[1].equals("all")) {
            numTransactions = manager.getTransactionListSize();
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        boolean isIncludeBarChart = commandParts.length == 3 && commandParts[2].equals("w/chart");
        return manager.showLastNTransactions(numTransactions, isIncludeBarChart);
    }
}

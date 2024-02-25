package main;

import java.io.IOException;

import parser.Parser;
import processor.Factory;
import task.TaskList;
import ui.Ui;
/**
 * The IreneAI class is a clever chatbot.
 */
public class IreneAI {
    private final Ui chatbotUi;
    private final TaskList taskList;
    private final Factory factory;
    private final Parser parser;
    /**
     * Constructs a Duke object with the specified Ui, TaskList, Processor, and Parser.
     */
    public IreneAI() {
        chatbotUi = new Ui();
        taskList = new TaskList();
        factory = new Factory(taskList, chatbotUi);
        parser = new Parser(factory);
    }
    /**
     * Executes the chatbot.
     * @throws IOException if an I/O error occurs while running the chatbot
     */
    public void run() throws IOException {
        // Greet User
        System.out.print(chatbotUi.greetingBox());
        String userInput;
        do {
            userInput = chatbotUi.getCommand();
            if (userInput.equals("bye")) {
                break;
            } else {
                parser.parse(userInput);
            }
        } while (true);
        System.out.println(chatbotUi.dividerWrapper(Ui.bye()));
    }

    public String run(String userInput) throws IOException {
        if (userInput.equals("bye")) {
            //return chatbotUi.dividerWrapper(Ui.bye());
            System.exit(1);
        } else {
            // Assuming that the parse method updates the chatbotUi with the response
            return chatbotUi.getResponse(userInput, parser);
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        IreneAI chatBot = new IreneAI();
        chatBot.run();
    }
}

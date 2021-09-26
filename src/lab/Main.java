package lab;

import org.apache.commons.cli.*;

import java.io.IOException;

/**
 * Klasa wywolujaca potrzebne metody
 */
public class Main {

    public static void main(String[] args){
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("p","parse", true, "Path to the bibtex file.")
                .addOption(Option.builder("a").longOpt("findAuthors").hasArgs().desc("Authors' names to find.").build())
                .addOption(Option.builder("c").longOpt("findCategories").hasArgs().desc("Categories to find.").build())
                .addOption("h","help",false, "Show help.");
        HelpFormatter formatter = new HelpFormatter();
        BibtexDisplayer displayer = new BibtexDisplayer();
        try {
            CommandLine cmdLine = parser.parse(options,args,false);
            if(cmdLine.hasOption("p")) {
                if (cmdLine.hasOption("a") && cmdLine.hasOption("c"))
                    displayer.display(cmdLine.getOptionValue("p"), cmdLine.getOptionValues("a"), cmdLine.getOptionValues("c"));
                else if (cmdLine.hasOption("a"))
                    displayer.displayA(cmdLine.getOptionValue("p"), cmdLine.getOptionValues("c"));
                else if (cmdLine.hasOption("c"))
                    displayer.displayC(cmdLine.getOptionValue("p"), cmdLine.getOptionValues("c"));
                else
                    displayer.display(cmdLine.getOptionValue("p"));
            }
            else
                formatter.printHelp("help", options);
        }
        catch(IOException e){
            e.getMessage();
        }
        catch(IllegalArgumentException e){
            e.getMessage();
            e.printStackTrace();
        }
        catch(ParseException e){
            System.out.println("Parsing arguments went wrong: " + e.getMessage());
        }
    }

}

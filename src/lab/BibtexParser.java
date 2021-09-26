package lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa wczytujaca i parsujaca podany plik
 */

public class BibtexParser {
    private BibtexEntries bibtexEntries = new BibtexEntries();


    public BibtexEntries getBibtexEntries() {
        return bibtexEntries;
    }


    /**
     * Metoda wczytujaca podany plik
     * @param filePath sciezka do pliku bibtex
     * @return plik bez niepotrzebnych bialych znaków
     */

    public String read(String filePath){
        StringBuilder linesB = new StringBuilder();
        try(BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String line = file.readLine();
            while (line != null) {
                if (line.isEmpty())
                    line = file.readLine();
                if (line != null) {
                    line = line.replaceAll("\\s+", " ");
                    linesB.append(line);
                    line = file.readLine();
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        String lines = linesB.toString();
        lines = lines.replaceAll("\\s+\\{","{");
        return lines;
    }

    /**
     * Metoda parsujaca podany plik
     * @param filePath sciezka do pliku bibtex
     * @throws IOException gdy podano bledna sciezke
     */

    public void parse (String filePath) throws IOException {
        int count = 0;
        String file = null;
        file = read(filePath);
        Pattern pattern1 = Pattern.compile("([^{]+)\\{");
        String[] tabLines = file.split("@");


        EntryDirector director = new EntryDirector();

        for (String tabLine : tabLines) {
            Matcher matcher1 = pattern1.matcher(tabLine);
            if (matcher1.find()) {
                String x = matcher1.group(1);
                x = x.toUpperCase();
                switch (x) {
                    case ("STRING"):{
                        new Strings().addString(tabLine);
                        break;
                    }
                    case ("BOOK"): {
                        count ++;
                        EntryBuilder bookBuilder = new BookBuilder();
                        director.setEntryBuilder(bookBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("ARTICLE"): {
                        count ++;
                        EntryBuilder articleBuilder = new ArticleBuilder();
                        director.setEntryBuilder(articleBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("BOOKLET"): {
                        count ++;
                        EntryBuilder bookletBuilder = new BookletBuilder();
                        director.setEntryBuilder(bookletBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("CONFERENCE"): {
                        count ++;
                        EntryBuilder conferenceBuilder = new InproceedingsBuilder();
                        director.setEntryBuilder(conferenceBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("INBOOK"): {
                        count ++;
                        EntryBuilder inbookBuilder = new InbookBuilder();
                        director.setEntryBuilder(inbookBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("INCOLLECTION"): {
                        count ++;
                        EntryBuilder incollectionBuilder = new IncollectionBuilder();
                        director.setEntryBuilder(incollectionBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("INPROCEEDINGS"): {
                        count ++;
                        EntryBuilder inproceedingsBuilder = new InproceedingsBuilder();
                        director.setEntryBuilder(inproceedingsBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("MANUAL"): {
                        count ++;
                        EntryBuilder manualBuilder = new ManualBuilder();
                        director.setEntryBuilder(manualBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("MASTERSTHESIS"): {
                        count ++;
                        EntryBuilder mastersThesisBuilder = new MastersThesisBuilder();
                        director.setEntryBuilder(mastersThesisBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("PHDTHESIS"): {
                        count ++;
                        EntryBuilder phdThesisBuilder = new PhdThesisBuilder();
                        director.setEntryBuilder(phdThesisBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("PROCEEDINGS"): {
                        count ++;
                        EntryBuilder proceedingsBuilder = new ProceedingsBuilder();
                        director.setEntryBuilder(proceedingsBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("TECHREPORT"): {
                        count ++;
                        EntryBuilder techReportBuilder = new TechReportBuilder();
                        director.setEntryBuilder(techReportBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("UNPUBLISHED"): {
                        count ++;
                        EntryBuilder unpublishedBuilder = new UnpublishedBuilder();
                        director.setEntryBuilder(unpublishedBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("MISC"): {
                        count ++;
                        EntryBuilder MiscBuilder = new MiscBuilder();
                        director.setEntryBuilder(MiscBuilder);
                        director.constructEntry(tabLine,x,count);
                        bibtexEntries.assemble(director.getEntry());
                        break;
                    }
                    case ("COMMENT"): {
                        break;
                    }
                    case ("PREAMBLE"): {
                        break;
                    }
                    default: {
                        count ++;
                        throw new IllegalArgumentException("Błędna nazwa rekordu(nr " + count + ").");
                    }
                }
            }
        }
        new References().checkRef(bibtexEntries.getEntries());
    }

}

package webster;

import java.util.ArrayList;


public class MajorPayne {

    private static final int PARAM_1 = 1;
    private static final int PARAM_2 = 2;
    private static final String OPEN = "open";
    private static final String LINK_BY_HREF = "checkLinkPresentByHref";
    private static final String LINK_BY_NAME = "checkLinkPresentByName";
    private static final String PAGE_TITLE = "checkPageTitle";
    private static final String PAGE_CONTAINS = "checkPageContains";

    public void execute(ArrayList<ArrayList<String>> commands) {

        for (int i = 0; i < commands.size(); i++) {

            String command = commands.get(i).get(0);

            switch (command) {

                case OPEN:

                    String url = commands.get(i).get(PARAM_1);
                    double timeOut = Double.parseDouble(commands.get(i).get(PARAM_2));
                    Commands.open(url, timeOut);
                    break;
                case PAGE_TITLE:

                    String title = commands.get(i).get(PARAM_1);
                    Commands.checkPageTitle(title);
                    break;
                case PAGE_CONTAINS:

                    String pageContains = commands.get(i).get(PARAM_1);
                    Commands.checkPageContains(pageContains);
                    break;
                case LINK_BY_HREF:

                    String href = commands.get(i).get(PARAM_1);
                    Commands.checkLinkPresentByHref(href);
                    break;
                case LINK_BY_NAME:

                    String link = commands.get(i).get(PARAM_1);
                    Commands.checkLinkPresentByName(link);
                    break;
            }
        }
        Commands.showStatistics();
    }
}

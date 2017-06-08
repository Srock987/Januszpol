package views;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Created by HP on 2017-06-08.
 */
public class AllWorkRecordsTest extends ApplicationTest{


    private static final String TITLE_WORKRECORD_LABEL = "#titleLabel";

    private static final String COMPANY_NAME_WORKRECORD_LABEL = "#selectCompanyLabel";

    private static final String DATE_WORKRECORD_LABEL = "#selectDateLabel";

    private static final String TIME_WORKRECORD_LABEL = "#selectHoursLabel";

    private static final String SAVE_WORKRECORD_LABEL = "#saveButton";

    private static final String CANCEL_WORKRECORD_LABEL = "#cancelButton";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);

        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
    }


}

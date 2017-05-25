import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;

public class DataLoaderTests {
    @Test(expected=IllegalArgumentException.class)
    public void loadCompaniesOnEmptyFileNameThrowsException() {
        DataLoader loader = new DataLoader();
        loader.loadData("");
    }
}
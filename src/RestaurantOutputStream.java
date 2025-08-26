import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RestaurantOutputStream extends ObjectOutputStream {
    public RestaurantOutputStream(OutputStream outputStream) throws IOException {
        super(outputStream);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        return;
    }
}

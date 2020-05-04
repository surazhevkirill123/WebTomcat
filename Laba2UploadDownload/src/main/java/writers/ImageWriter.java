package writers;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.UUID;

public class ImageWriter {
    private String dir;

    public ImageWriter(String dir){
        this.dir = dir;
    }

    public void write(FileItem item) throws Exception {
        UUID fileName = UUID.randomUUID();
        File uploadedFile = new File(this.dir.concat(String.valueOf(fileName)).concat(".jpg"));
        item.write(uploadedFile);
    }
}

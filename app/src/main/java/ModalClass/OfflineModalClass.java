package ModalClass;

public class OfflineModalClass {

  String filename,filepath,type;

   public OfflineModalClass(){


   }

    public OfflineModalClass(String filename, String filepath) {
        this.filename = filename;
        this.filepath = filepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}

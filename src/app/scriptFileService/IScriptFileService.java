package app.scriptFileService;

import java.util.List;

public interface IScriptFileService {
    String getFileName();
    void setFileName(String fileName);
    void executeScript();
}

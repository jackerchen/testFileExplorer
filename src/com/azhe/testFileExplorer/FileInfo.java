package com.azhe.testFileExplorer;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 6:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileInfo {

    public String fileName;

    public String filePath;

    public long fileSize;

    public boolean IsDir;

    public int Count;

    public long ModifiedDate;

    public boolean Selected;

    public boolean canRead;

    public boolean canWrite;

    public boolean isHidden;

    public long dbId; // id in the database, if is from database
}

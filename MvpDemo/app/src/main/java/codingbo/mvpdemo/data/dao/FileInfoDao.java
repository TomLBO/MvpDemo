package codingbo.mvpdemo.data.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import codingbo.mvpdemo.download.DownloadStatus;
import codingbo.mvpdemo.download.DownloadStatusConvert;

import codingbo.mvpdemo.data.FileInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FILE_INFO".
*/
public class FileInfoDao extends AbstractDao<FileInfo, String> {

    public static final String TABLENAME = "FILE_INFO";

    /**
     * Properties of entity FileInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property MFileId = new Property(0, String.class, "mFileId", true, "M_FILE_ID");
        public final static Property MFileName = new Property(1, String.class, "mFileName", false, "M_FILE_NAME");
        public final static Property MProgress = new Property(2, float.class, "mProgress", false, "M_PROGRESS");
        public final static Property MDownloadStatus = new Property(3, int.class, "mDownloadStatus", false, "M_DOWNLOAD_STATUS");
    };

    private final DownloadStatusConvert mDownloadStatusConverter = new DownloadStatusConvert();

    public FileInfoDao(DaoConfig config) {
        super(config);
    }
    
    public FileInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FILE_INFO\" (" + //
                "\"M_FILE_ID\" TEXT PRIMARY KEY NOT NULL UNIQUE ," + // 0: mFileId
                "\"M_FILE_NAME\" TEXT NOT NULL ," + // 1: mFileName
                "\"M_PROGRESS\" REAL NOT NULL ," + // 2: mProgress
                "\"M_DOWNLOAD_STATUS\" INTEGER NOT NULL );"); // 3: mDownloadStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FILE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FileInfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getMFileId());
        stmt.bindString(2, entity.getMFileName());
        stmt.bindDouble(3, entity.getMProgress());
        stmt.bindLong(4, mDownloadStatusConverter.convertToDatabaseValue(entity.getMDownloadStatus()));
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FileInfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getMFileId());
        stmt.bindString(2, entity.getMFileName());
        stmt.bindDouble(3, entity.getMProgress());
        stmt.bindLong(4, mDownloadStatusConverter.convertToDatabaseValue(entity.getMDownloadStatus()));
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    @Override
    public FileInfo readEntity(Cursor cursor, int offset) {
        FileInfo entity = new FileInfo( //
            cursor.getString(offset + 0), // mFileId
            cursor.getString(offset + 1), // mFileName
            cursor.getFloat(offset + 2), // mProgress
            mDownloadStatusConverter.convertToEntityProperty(cursor.getInt(offset + 3)) // mDownloadStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FileInfo entity, int offset) {
        entity.setMFileId(cursor.getString(offset + 0));
        entity.setMFileName(cursor.getString(offset + 1));
        entity.setMProgress(cursor.getFloat(offset + 2));
        entity.setMDownloadStatus(mDownloadStatusConverter.convertToEntityProperty(cursor.getInt(offset + 3)));
     }
    
    @Override
    protected final String updateKeyAfterInsert(FileInfo entity, long rowId) {
        return entity.getMFileId();
    }
    
    @Override
    public String getKey(FileInfo entity) {
        if(entity != null) {
            return entity.getMFileId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

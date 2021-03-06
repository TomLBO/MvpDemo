package codingbo.downloadui.downloadMvp;

import java.util.List;

import codingbo.downloadui.BaseView;
import codingbo.downloadui.download.DownloadInfo;

/**
 * Created by bob
 * on 17.2.13.
 */

public class DownloadPresenter implements DownloadContract.Presenter {

    private static DownloadPresenter INSTANCE;

    private DataRepository mRepository;
    private DownloadContract.View mView;

    public DownloadPresenter(DataRepository repository, DownloadContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode, DownloadInfo info) {
        addDownload(info);
    }

    @Override
    public void refreshData() {
        mRepository.getData(new DataSource.LoadCallback() {
            @Override
            public void success(List<DownloadInfo> info) {
                if (mView == null || !mView.isActive()) {
                    return;
                }
                mView.showDownload(info);
            }

            @Override
            public void failed(Exception e) {
                if (mView == null || !mView.isActive()) {
                    return;
                }
                mView.showFailed();
            }
        });
    }

    @Override
    public void addDownload() {
        mView.showAddDownload();
    }

    public void addDownload(DownloadInfo info) {
        mRepository.addData(info);
        mRepository.downloading(info, 200, new DataSource.DownloadCallback() {
            @Override
            public void success(DownloadInfo info) {
                refreshData();
            }

            @Override
            public void failed(DownloadInfo info, Exception e) {
                refreshData();
            }

            @Override
            public void downloading(DownloadInfo info, float progress) {
                refreshData();
            }
        });
    }

    @Override
    public void start() {
        refreshData();
    }
}

package codingbo.downloadui.downloadMvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import codingbo.downloadui.R;
import codingbo.downloadui.download.DownloadAdapter;
import codingbo.downloadui.download.DownloadInfo;

/**
 * Created by bob
 * on 17.2.13.
 */

public class DownloadFragment extends Fragment implements DownloadContract.View {

    private DownloadContract.Presenter mPresenter;

    private DownloadAdapter mAdapter;

    public DownloadFragment() {
    }

    public static DownloadFragment getInstance() {
        return new DownloadFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DownloadAdapter(new ArrayList<DownloadInfo>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.downlaod_frag, container, false);
        ListView listView = (ListView) root.findViewById(R.id.ll_list);
        listView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addDownload();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        DownloadInfo info = new DownloadInfo("add", 0);
        mPresenter.result(requestCode, resultCode, info);
    }

    @Override
    public void showDownload(final List<DownloadInfo> info) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setData(info);
            }
        });
    }

    @Override
    public void showAddDownload() {
        Intent intent = new Intent(getContext(), AddDownloadActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public void showFailed() {
        Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(DownloadContract.Presenter p) {
        mPresenter = p;
    }
}

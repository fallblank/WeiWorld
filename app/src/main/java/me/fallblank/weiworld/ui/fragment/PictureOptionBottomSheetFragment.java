package me.fallblank.weiworld.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.util.TimeFormatter;
import me.fallblank.weiworld.util.ToastUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PictureOptionBottomSheetFragment extends BottomSheetDialogFragment {
    
    private static final String ARG_POSITION = "position";
    private static final String ARG_WEIBO = "weibo";
    
    public static PictureOptionBottomSheetFragment newInstance(int position, Weibo weibo) {
        final PictureOptionBottomSheetFragment fragment = new PictureOptionBottomSheetFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putSerializable(ARG_WEIBO, weibo);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture_option_bottom_sheet, container, false);
    }
    
    private Weibo mWeibo;
    private int mPosition;
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mWeibo = (Weibo) getArguments().getSerializable(ARG_WEIBO);
        mPosition = getArguments().getInt(ARG_POSITION);
        recyclerView.setAdapter(new ItemAdapter(2));
    }
    
    
    private class ViewHolder extends RecyclerView.ViewHolder {
        
        final TextView text;
        
        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
            text = (TextView) itemView.findViewById(R.id.text);
        }
        
    }
    
    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
        
        private final int mItemCount;
        
        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }
        
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }
        
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == 0) {
                holder.text.setText("下载");
                holder.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PictureDownloader task = new PictureDownloader(v.getContext());
                        task.execute(mWeibo.getOrigin_urls().get(mPosition).getThumbnail_pic());
                        dismiss();
                    }
                });
            }
            if (position == 1) {
                holder.text.setText("取消");
                holder.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }
        
        @Override
        public int getItemCount() {
            return mItemCount;
        }
        
    }
    
    private class PictureDownloader extends AsyncTask<String, Void, Boolean> {
        
        OkHttpClient client = new OkHttpClient();
        Context mContext;
        
        public PictureDownloader(Context context) {
            mContext = context;
        }
        
        @Override
        protected Boolean doInBackground(String... params) {
            String url = params[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                int last = url.lastIndexOf(".");
                String type = url.substring(last, url.length());
                String fileName = TimeFormatter.getTimeStamp() + "." + type;
                File directory = new File(Environment.getExternalStorageDirectory(),
                        mContext.getResources().getString(R.string.app_name));
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(directory, fileName);
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(response.body().bytes());
                outputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        
        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                ToastUtil.show(mContext, "下载成功");
            } else {
                ToastUtil.show(mContext, "下载失败");
            }
        }
    }
    
}

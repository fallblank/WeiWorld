package me.fallblank.weiworld.network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by fallb on 2017/4/27.
 */

public class ProgressResponseBody extends ResponseBody {
    
    public interface ProgressListener {
        /**
         * @param bytesRead     已经读取的字节数
         * @param contentLength 响应总长度
         * @param done          是否读取完毕
         */
        void update(long bytesRead, long contentLength, boolean done);
    }
    
    private final ResponseBody mResponseBody;
    private final ProgressListener mListener;
    private BufferedSource bufferedSource;
    
    public ProgressResponseBody(ResponseBody responseBody, ProgressListener listener) {
        this.mResponseBody = responseBody;
        this.mListener = listener;
    }
    
    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }
    
    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }
    
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return bufferedSource;
    }
    
    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                mListener.update(totalBytesRead, mResponseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}


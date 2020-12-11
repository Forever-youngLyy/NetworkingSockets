package course.examples.networking.sockets;

import android.app.Fragment;
import android.content.Context;

import java.io.*;
import java.net.Socket;


public class RetainedFragment extends Fragment {
    static final String TAG = "RetainedFragment";
    private OnFragmentInteractionListener mListener;
    private static final String HOST = "192.168.2.169";
    private static final String FIRST_SEND = "{\"cmd\":11,\"deviceid\":\"121\"}";
    private static final String BIND = "{\"cmd\":11,\"userid\":\"121\",\"deviceid\":\"122\"}";
    private static Socket socket = null;
    private static volatile String data = "";

    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(socket == null || !socket.isConnected()){
                        socket = new Socket(HOST, 8888);
                    }
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    int size = 0;
                    char[] cbuf =  new char[1024];
                    while((size = br.read(cbuf, 0, cbuf.length))!=-1)
                    {
                        onDownloadFinished(new String(cbuf,0,size));
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public RetainedFragment() {
        // Required empty public constructor
    }

    void bind_pressed(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (socket == null || !socket.isConnected()){
                        socket = new Socket(HOST, 8888);
                    }
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                            socket.getOutputStream()), true);
                    pw.println("bind_pressed!!!");
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void onDownloadFinished(String result) {
        if (null != mListener) {
            mListener.onDownloadFinished(result);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onDownloadFinished(String result);
    }
}

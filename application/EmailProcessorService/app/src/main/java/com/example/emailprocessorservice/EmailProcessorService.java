package com.example.emailprocessorservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import com.example.emailprocessorservice.models.LinkedList;

import java.util.AbstractQueue;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class EmailProcessorService extends Service {
    private static final String TAG = EmailProcessorService.class.getName();
    private static final String THREAD_NAME = "EmailServiceProcessor Handler Thread";

    static final int MSG_PROCESS_REQUEST_FROM_CLIENT = 1;
    static final int MSG_PROCESSED_REQUEST_MESSAGE = 2;


    private final BlockingQueue<Message> mRequests = new LinkedBlockingDeque<>();
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private Messenger mMessenger;
    private Handler mHandler;

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {

        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        mHandler = new Handler();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Just did onCreate");
        return START_STICKY;
    }


    /**
     * Handler of incoming messages from clients.
     */
    private static class IncomingHandler extends Handler {

        EmailProcessorService service;

        IncomingHandler(EmailProcessorService service){
            this.service = service;
        }

        @Override
        public void handleMessage(Message msg) {
            String logMessage = String.format(Locale.getDefault(), "Received message what= %d", msg.what);
            Log.d(TAG, logMessage);

            switch (msg.what) {
                case MSG_PROCESS_REQUEST_FROM_CLIENT:
                    service.handleRequest(msg);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private void handleRequest(Message msg) {
        LinkedList messagesList = msg.getData().getParcelable("messagesList");
        if (messagesList!=null) {
            messagesList.removeDuplicates();
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("messagesList", messagesList);
        msg.setData(bundle);

        Message responseMsg = Message.obtain(null, MSG_PROCESSED_REQUEST_MESSAGE, 0, 0);
        responseMsg.setData(bundle);
    }
}